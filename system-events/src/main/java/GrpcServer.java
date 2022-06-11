import Implementation.EventImpl;
import com.amn.grpc.eventGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) {
        Server server = ServerBuilder
                .forPort(8800)
                .addService(new EventImpl()).build();

        try {
            server.start();
            System.out.println("Server started");
            server.awaitTermination();
        } catch (IOException e) {
            System.out.println("All good");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("No worries");
            e.printStackTrace();
        }

    }
}