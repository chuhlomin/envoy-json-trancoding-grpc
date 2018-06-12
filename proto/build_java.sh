#!/usr/bin/env bash

mkdir java

docker run --rm \
  -v $(pwd):$(pwd) \
  -w $(pwd) znly/protoc \
  --java_out=java \
  --grpc-java_out=java \
  -I. \
  helloworld.proto

rm -r ../app/generated-sources/src/main/java
mv java ../app/generated-sources/src/main/java