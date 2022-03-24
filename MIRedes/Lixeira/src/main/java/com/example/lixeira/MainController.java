package com.example.lixeira;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.PrintStream;

public class MainController {
    @FXML
    private Label mensageText;

    @FXML
    protected void onHelloButtonClick() {

        mensageText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    public void abrirLixeira(ActionEvent actionEvent) {

        mensageText.setText("Welcome to JavaFX Abrir Lixeira!");
    }
    @FXML
    public void travarLixeira(ActionEvent actionEvent) {

        mensageText.setText("Welcome to JavaFX travar Lixeira!");
    }
    @FXML
    public void esvaziarLixeira(ActionEvent actionEvent) {

        mensageText.setText("Welcome to JavaFX Esvaziar Lixeira!");
    }
    @FXML
    public void colocarLixo(ActionEvent actionEvent) {

        mensageText.setText("Welcome to JavaFX Colocar Lixo!");
    }
    @FXML
    public void enviarMensagem() {
        MainLixeira lixeira = new MainLixeira();
       // String mensagem = "";
        lixeira.enviarMensagem(mensageText.getText());
       // mensageText.setText(mensagem);
    }

}