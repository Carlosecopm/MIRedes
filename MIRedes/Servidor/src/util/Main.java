package util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(3334);
            System.out.println("Seridor iniciado na porta 3334");

            Socket cliente = servidor.accept();
            System.out.println("Cliente do ip: " + cliente.getInetAddress().getHostAddress());

            Scanner entrada = new Scanner(cliente.getInputStream());
            while (entrada.hasNextLine()) {
                System.out.println(entrada.nextLine());
            }

            entrada.close();
            servidor.close();

        } catch (IOException e) {
            System.out.println("Erro ao criar Servidor");
        }
    }
}
