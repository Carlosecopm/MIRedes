package controller;

import util.Servidor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        servidor.iniciarServidor();

    }
}
