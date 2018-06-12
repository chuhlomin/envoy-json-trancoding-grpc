#!/usr/bin/env bash

docker run --rm \
  -v $(pwd):$(pwd) \
  -w $(pwd) znly/protoc \
  --descriptor_set_out=descriptor_set.pb \
  --include_imports \
  --include_source_info \
  -I. \
  -I /protobuf/google/protobuf/ \
  helloworld.proto
