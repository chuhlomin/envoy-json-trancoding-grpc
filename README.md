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
cd app; sh ./build.sh; cd ..
```

4. Build app Docker image:

```bash
docker-compose build app
```

5. Run app

```bash
docker-compose up -d app
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
```
