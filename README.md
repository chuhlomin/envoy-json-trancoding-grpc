# GRPC, Envoy and JSON-transcoding

This is an example of Java GRPC, Envoy proxy with JSON-transoding filter enabled.

1. Build descriptor set for service (optional, already in repo):

```bash
cd proto; sh ./build_ds.sh; cd ..
```

2. Build Java classes (optional, already in repo):

```bash
cd proto; sh ./build_java.sh; cd ..
```

3. Build app jar:

```bash
cd server; sh ./build.sh; cd ..
```

4. Build app Docker image:

```bash
docker-compose build server
```

5. Run app

```bash
docker-compose up -d server
```

6. Run envoy

```bash
docker-compose up -d envoy
```

7. Enable debug logging for envoy:

```bash
curl --request GET \
  --url 'http://localhost:8081/logging?level=debug'
```

8. Send POST request to envoy:

```bash
curl --request POST \
  --url http://localhost:8080/helloworld.Greeter/SayHello \
  --header 'content-type: application/json' \
  --data '{"name":"John"}'
 
curl: (18) transfer closed with outstanding read data remaining
```

```bash
curl --request POST \
  --url http://localhost:8080/bonjour \
  --header 'content-type: application/json' \
  --data '{"name":"John"}'
 
{"message":"Bonjour John"}
```

```bash
curl --request POST \
  --url http://localhost:8080/helloworld.Greeter/SayHi \
  --header 'content-type: application/json' \
  --data '{"name":"John"}'
 
{"message":"Hi John"}
```

9. Build GRPC client and run it:
```bash
cd client
sh build.sh
cd ../
docker-compose build client
docker-compose up client
```

##### Envoy responses summary:

| | CURL response | GRPC response |
|-|--------------------------|-----------|
| *SayHello*<br>(no options) | curl: (18) transfer closed with outstanding read data remaining | `Hello John` |
| *SayBonjour*<br>`post: "/bonjour"` | `{"message":"Bonjour John"}` | `Bonjour John` |
| *SayHi*<br>`post: "/helloworld.Greeter/SayHi"` | `{"message":"Hi John"}` | io.grpc.StatusRuntimeException: INTERNAL: HTTP status code 400<br>invalid content-type: text/plain<br>headers: Metadata(:status=400,content-length=31,content-type=text/plain,date=Wed, 13 Jun 2018 19:15:32 GMT,server=envoy)<br>DATA-----------------------------<br>Expected a value.<br><br>John<br>^<bt>&#9;at io.grpc.stub.ClientCalls.toStatusRuntimeException(ClientCalls.java:221)<br>&#9;at io.grpc.stub.ClientCalls.getUnchecked(ClientCalls.java:202)<br>&#9;at io.grpc.stub.ClientCalls.blockingUnaryCall(ClientCalls.java:131)<br>&#9;at io.grpc.examples.helloworld.GreeterGrpc$GreeterBlockingStub.sayHi(GreeterGrpc.java:220)<br>&#9;at com.company.app.HelloWorldClient.main(HelloWorldClient.java:29) |

