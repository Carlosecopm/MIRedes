package com.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class MainFrame extends javax.swing.JFrame {
    private static Socket lixeira;
    private JPanel mainPanel;
    private JTextField tfMensagem;
    private JButton btnTravar;
    private JButton btnEncher;
    private JButton btnAbrir;
    private JButton btnEsvaziar;

    public static void main(String[] args) {

        MainFrame myFrame = new MainFrame();
        iniciaLixeira();
    }
    public MainFrame() {
        setContentPane(mainPanel);
        setTitle("Lixeira");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
       // iniciaLixeira();
        btnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //  enviarMensagem(tfMensagem.getText());
                abrirLixeira();
            }
        });
        btnEsvaziar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                esvaziarLixeira();
            }
        });
        btnEncher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                colocarLixo();
            }
        });
        btnTravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                travarLixeira();
            }
        });
    }

    private void travarLixeira() {
        try {
            String msg = "Lixeira Bloquada";
            PrintStream saida = new PrintStream(lixeira.getOutputStream());
            saida.println(msg);
        } catch (IOException e) {
            System.out.println("Erro ao enviar mensagem");
        }
    }

    private void colocarLixo() {
        try {
            String msg = "Lixeira recebeu lixo";
            PrintStream saida = new PrintStream(lixeira.getOutputStream());
            saida.println(msg);
        } catch (IOException e) {
            System.out.println("Erro ao enviar mensagem");
        }
    }

    private void esvaziarLixeira() {
        try {
            String msg = "Lixeira vazia";
            PrintStream saida = new PrintStream(lixeira.getOutputStream());
            saida.println(msg);
        } catch (IOException e) {
            System.out.println("Erro ao enviar mensagem");
        }

    }


    private static void iniciaLixeira() {
        try {
            lixeira = new Socket("127.0.0.1", 3334);
            System.out.println("Conectado com servidor");
        } catch (IOException e) {
            System.out.println("Não foi possível conectar com servidor");
        }
    }

    private static void enviarMensagem(String msg) {
        try {
            PrintStream saida = new PrintStream(lixeira.getOutputStream());
            saida.println(msg);
        } catch (IOException e) {
            System.out.println("Erro ao enviar mensagem");
        }
    }
    private static void abrirLixeira() {
        try {
            String msg = "Lixeira Aberta";
            PrintStream saida = new PrintStream(lixeira.getOutputStream());
            saida.println(msg);
        } catch (IOException e) {
            System.out.println("Erro ao enviar mensagem");
        }
    }

}
