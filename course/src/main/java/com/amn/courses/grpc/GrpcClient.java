package com.amn.courses.grpc;

import com.amn.grpc.APIResponse;
import com.amn.grpc.LogRequest;
import com.amn.grpc.eventGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.text.SimpleDateFormat;
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        LogRequest logRequest = LogRequest.newBuilder()
                .setTimestamp(sdf.toPattern())
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
