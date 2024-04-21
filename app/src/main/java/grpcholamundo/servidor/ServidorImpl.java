package grpcholamundo.servidor;

import com.proto.saludo.Holamundo.OperacionRequest;
import com.proto.saludo.Holamundo.OperacionResponse;
import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;
import com.proto.saludo.saludoServiceGrpc;

import io.grpc.stub.StreamObserver;

public class ServidorImpl extends saludoServiceGrpc.saludoServiceImplBase {
    
    @Override
    public void saludo(SaludoRequest request, StreamObserver<SaludoResponse> responseObserver) {
        SaludoResponse respuesta = SaludoResponse.newBuilder().setResultado("Hola " + request.getNombre()).build();

        responseObserver.onNext(respuesta);

        responseObserver.onCompleted();
    }

    @Override
    public void suma(OperacionRequest request, StreamObserver<OperacionResponse> responseObserver) {
        OperacionResponse respuesta = OperacionResponse.newBuilder().setResultado(request.getEntrada1() + request.getEntrada2()).build();

        responseObserver.onNext(respuesta);
        responseObserver.onCompleted();
    }

    @Override
    public void resta(OperacionRequest request, StreamObserver<OperacionResponse> responseObserver) {
        OperacionResponse respuesta = OperacionResponse.newBuilder().setResultado(request.getEntrada1() - request.getEntrada2()).build();

        responseObserver.onNext(respuesta);
        responseObserver.onCompleted();
    }

    @Override
    public void mult(OperacionRequest request, StreamObserver<OperacionResponse> responseObserver) {
        OperacionResponse respuesta = OperacionResponse.newBuilder().setResultado(request.getEntrada1() * request.getEntrada2()).build();

        responseObserver.onNext(respuesta);
        responseObserver.onCompleted();
    }

    @Override
    public void div(OperacionRequest request, StreamObserver<OperacionResponse> responseObserver) {
        OperacionResponse respuesta = OperacionResponse.newBuilder().setResultado(request.getEntrada1() / request.getEntrada2()).build();

        responseObserver.onNext(respuesta);
        responseObserver.onCompleted();
    }
}
