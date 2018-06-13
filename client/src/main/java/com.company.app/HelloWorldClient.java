package com.company.app;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;

import java.io.IOException;

public class HelloWorldClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Channel channel = ManagedChannelBuilder.forAddress("envoy", 80).usePlaintext().build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

        HelloRequest request = HelloRequest.newBuilder().setName("John").build();
        HelloReply reply;

        try {
            System.out.println("Saying Hello");
            reply = stub.sayHello(request);
            System.out.println(reply.getMessage());
        } catch (io.grpc.StatusRuntimeException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Saying Bonjour");
            reply = stub.sayBonjour(request);
            System.out.println(reply.getMessage());
        } catch (io.grpc.StatusRuntimeException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Saying Hi");
            reply = stub.sayHi(request);
            System.out.println(reply.getMessage());
        } catch (io.grpc.StatusRuntimeException e) {
            e.printStackTrace();
        }
    }
}
