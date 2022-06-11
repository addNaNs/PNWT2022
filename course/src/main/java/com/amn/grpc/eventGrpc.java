package com.amn.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: event.proto")
public final class eventGrpc {

  private eventGrpc() {}

  public static final String SERVICE_NAME = "com.amn.grpc.event";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.amn.grpc.LogRequest,
      com.amn.grpc.APIResponse> METHOD_LOG =
      io.grpc.MethodDescriptor.<com.amn.grpc.LogRequest, com.amn.grpc.APIResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.amn.grpc.event", "log"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.amn.grpc.LogRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.amn.grpc.APIResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static eventStub newStub(io.grpc.Channel channel) {
    return new eventStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static eventBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new eventBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static eventFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new eventFutureStub(channel);
  }

  /**
   */
  public static abstract class eventImplBase implements io.grpc.BindableService {

    /**
     */
    public void log(com.amn.grpc.LogRequest request,
        io.grpc.stub.StreamObserver<com.amn.grpc.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LOG, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_LOG,
            asyncUnaryCall(
              new MethodHandlers<
                com.amn.grpc.LogRequest,
                com.amn.grpc.APIResponse>(
                  this, METHODID_LOG)))
          .build();
    }
  }

  /**
   */
  public static final class eventStub extends io.grpc.stub.AbstractStub<eventStub> {
    private eventStub(io.grpc.Channel channel) {
      super(channel);
    }

    private eventStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected eventStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new eventStub(channel, callOptions);
    }

    /**
     */
    public void log(com.amn.grpc.LogRequest request,
        io.grpc.stub.StreamObserver<com.amn.grpc.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LOG, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class eventBlockingStub extends io.grpc.stub.AbstractStub<eventBlockingStub> {
    private eventBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private eventBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected eventBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new eventBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.amn.grpc.APIResponse log(com.amn.grpc.LogRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LOG, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class eventFutureStub extends io.grpc.stub.AbstractStub<eventFutureStub> {
    private eventFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private eventFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected eventFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new eventFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.amn.grpc.APIResponse> log(
        com.amn.grpc.LogRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LOG, getCallOptions()), request);
    }
  }

  private static final int METHODID_LOG = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final eventImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(eventImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOG:
          serviceImpl.log((com.amn.grpc.LogRequest) request,
              (io.grpc.stub.StreamObserver<com.amn.grpc.APIResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class eventDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.amn.grpc.Event.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (eventGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new eventDescriptorSupplier())
              .addMethod(METHOD_LOG)
              .build();
        }
      }
    }
    return result;
  }
}
