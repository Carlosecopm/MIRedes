package com.example.lixeira;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class MainLixeira extends Application {
    private static Socket lixeira;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainLixeira.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Lixeira");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
        iniciaLixeira();
    }

    private static void iniciaLixeira() {
        try {
            lixeira = new Socket("127.0.0.1", 3334);
            System.out.println("Conectado com o servidor");
        } catch (IOException e) {
            System.out.println("Erro, não foi possível conectar com o servidor");
        }
    }

    public  void enviarMensagem(String mensagem) {
        PrintStream saida = null;
        try {
            saida = new PrintStream(lixeira.getOutputStream());
            saida.println(mensagem);
        } catch (IOException e) {
            System.out.println("Erro ao enviar mensagem");
        }

    }
}