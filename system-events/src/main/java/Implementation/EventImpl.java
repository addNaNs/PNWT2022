package Implementation;

import com.amn.grpc.APIResponse;
import com.amn.grpc.eventGrpc;

public class EventImpl extends eventGrpc.eventImplBase {

    @Override
    public void log(com.amn.grpc.LogRequest request,
                 io.grpc.stub.StreamObserver<com.amn.grpc.APIResponse> responseObserver){
        String mess = new StringBuilder()
                .append("Event time : ")
                .append(request.getTimestamp())
                .append(";\n").append("Resource name : ")
                .append(request.getResource())
                .append(";\n").append("Action taken : ")
                .append(request.getAction())
                .append(";\n").append("Status : ")
                .append(request.getStatus())
                .append(";\n").toString();

        System.out.println(mess);

        APIResponse response = APIResponse.newBuilder()
                .setMessage("123")
                .setResponseCode(200)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

        System.out.println("Request sent");
    }
}
