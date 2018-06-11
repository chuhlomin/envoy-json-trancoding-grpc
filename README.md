# GRPC, Envoy and JSON-transcoding

This is an example of Java GRPC, Envoy proxy with JSON-transoding filter enabled.

1. (Optional) Build descriptor set for service:

```bash
cd proto; sh ./build_ds.sh; cd ..
```

2. (Optional) Build Java classes:

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

5. ...to be continued
