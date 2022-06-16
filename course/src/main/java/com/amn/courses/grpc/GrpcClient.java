package com.amn.courses.grpc;

import com.amn.grpc.APIResponse;
import com.amn.grpc.LogRequest;
import com.amn.grpc.eventGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.tomcat.jni.Time;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class GrpcClient {

    private static ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8800)
            .usePlaintext()
            .build();;
    private static GrpcClient grpcClient = null;

    private GrpcClient(){
        channel = channel = ManagedChannelBuilder.forAddress("localhost", 8800)
                .usePlaintext()
                .build();
    }

    public static GrpcClient get(){
        if(grpcClient == null){
            grpcClient = new GrpcClient();
        }
        return grpcClient;
    }

    public static void log(String resource, String action, String status){
        eventGrpc.eventBlockingStub eventStub = eventGrpc.newBlockingStub(channel);
        LogRequest logRequest = LogRequest.newBuilder()
                .setTimestamp(String.valueOf(LocalDateTime.now()))
                .setResource(resource)
                .setAction(action)
                .setStatus(status)
                .build();
        try {
            eventStub.log(logRequest);

        } catch (Exception e){

        }
        /*
        APIResponse response =
        System.out.println(response.getMessage());
        System.out.println(response.getResponseCode());
         */
    }
}
