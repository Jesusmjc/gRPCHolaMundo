package grpcholamundo.cliente;

import com.proto.saludo.saludoServiceGrpc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import com.proto.saludo.Holamundo.OperacionRequest;
import com.proto.saludo.Holamundo.OperacionResponse;
import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";

        int puerto = 8080;

        ManagedChannel ch = ManagedChannelBuilder
                .forAddress(host, puerto)
                .usePlaintext()
                .build();

        saludoServiceGrpc.saludoServiceBlockingStub stub = saludoServiceGrpc.newBlockingStub(ch);

        /*
        SaludoRequest peticion = SaludoRequest.newBuilder().setNombre("Jesus").build();
        SaludoResponse respuesta = stub.saludo(peticion);

        System.out.println("Respuesta RPC: " + respuesta.getResultado());
        */

        //try {
            while(true) {
                String opt = JOptionPane.showInputDialog(
                    "Calcular\n" +
                            "Suma..............(1)\n" +
                            "Resta.............(2)\n" +
                            "Multip............(3)\n" +
                            "Division..........(4)\n" +
                            "Cancelar para salir");


                if (opt == null) {
                    break;
                }

                int a = Integer.parseInt(JOptionPane.showInputDialog("Ingrese a"));
                int b = Integer.parseInt(JOptionPane.showInputDialog("Ingrese b"));

                OperacionRequest peticion = OperacionRequest.newBuilder().setEntrada1(a).setEntrada2(b).build();
    
                switch (opt) {
                    case "1": {
                        OperacionResponse respuesta = stub.suma(peticion);
                        JOptionPane.showMessageDialog(null, a + "+" + b + " = " + respuesta.getResultado());
                        break;
                    }

                    case "2": {
                        OperacionResponse respuesta = stub.resta(peticion);
                        JOptionPane.showMessageDialog(null, a + " - " + b + " = " + respuesta.getResultado());
                        break;
                    }

                    case "3": {
                        OperacionResponse respuesta = stub.mult(peticion);
                        JOptionPane.showMessageDialog(null, a + " * " + b + " = " + respuesta.getResultado());
                        break;
                    }

                    case "4": {
                        OperacionResponse respuesta = stub.div(peticion);
                        JOptionPane.showMessageDialog(null, a + "/" + b + " = " + respuesta.getResultado());
                        break;
                    }
                }
            }

            
        //} catch ( IOException | InterruptedException ex) {
        //    JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor:\n" + ex);
        //}

        System.out.println("Apagando...");
        ch.shutdown();
    }
}
