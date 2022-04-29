package controller;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import model.Requisicao;
import model.Resposta;

public class Lixeira implements Runnable {

    private Socket con;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private int p; // porta a ser conectado.
    private String idC = "";
    private float capacidade = 0;
    private float quantidade = 0;
    private boolean disponivel = true;
    private boolean controle = true;

    // o construtor cria e abre uma conexao com o servidor.
    public Lixeira(String endereco, int porta) // recebe o endereco e porta como parametros.
    {
        System.out.println("SISTEMA DE COLETA DE LIXO - LIXEIRA");
        System.out.println("Digite o ID da lixeira...");
        Scanner entradaT = new Scanner(System.in);
        String id = entradaT.next();
        System.out.println("Digite a capacidade da lixeira...");
        int capacidadeAux = entradaT.nextInt();

        this.capacidade = capacidadeAux;
        this.idC = id;

        try {
            con = new Socket(InetAddress.getByName(endereco), porta);
            output = new ObjectOutputStream(con.getOutputStream());
            InputStream s = con.getInputStream();
            input = new ObjectInputStream(s);
            System.out.println("Lixeira cadastrada com sucesso!");
        } catch (java.io.IOException er1) {
            //System.out.println(er1.getMessage());
            System.out.println("Ocorreu um erro de conexão. Encerrando o serviço...\n");
            System.exit(0);
        }

    }

// iniciada - metodo start().
    @Override
    public void run() {
        Requisicao request = null;
        PegaTeclado pegaT = new PegaTeclado(this);
        PegaTeclado.run();
        while (true) {
            try {
                // Verifica se existe uma requisicao
                Object obj = input.readObject();
                Class c = obj.getClass();
                String tipoInput = c.getName();
                if (tipoInput.contains("Resposta")) {
                    Resposta resposta = null;
                    resposta = (Resposta) obj;
                    if (resposta != null) {
                        if (resposta.getCodigoR().equals("203_" + idC)) {
                            System.out.println("Lixo adicionado com sucesso.");
                        } else if(resposta.getCodigoR().equals("205_" + idC)){
                            System.out.println("Lixo encerrada com sucesso.");
                            System.exit(0);
                        }
                    }
                } else if (tipoInput.contains("Requisicao")) {
                    if (request != null) {
                        if (request.getTipoCliente().equals("Adm") && request.getIdD().equals(idC) && request.getTipoRequest().equals("PUT")) {
                            if (request.getDadoEnviado().equals("_bloqueaL#@_")) {
                                setDisponivel(false);
                                System.out.println("A Lixeira foi bloqueada!\n");
                                output.writeObject(new Resposta("200_" + idC));
                                output.flush();
                                menu();
                            } else if (request.getDadoEnviado().equals("_desbloqueaL#@_")) {
                                setDisponivel(false);
                                System.out.println("A Lixeira foi desbloqueada!\n");
                                output.flush();
                                output.writeObject(new Resposta("201_" + idC));
                                menu();
                            }
                        } else if (request.getTipoCliente().equals("Caminhao") && request.getIdD().equals(idC) && request.getTipoRequest().equals("PUT")) {
                            if (request.getDadoEnviado().equals("_coletaL#@_")) {
                                coletaLixo();
                                System.out.println("A Lixeira foi coletada!\n");
                                output.flush();
                                output.writeObject(new Resposta("204_" + idC));
                                menu();
                            }
                        }

                    }
                }

            } catch (java.io.OptionalDataException e) {
                System.out.println("Erro no desconectar.\n" + e.getMessage());
            } catch (java.lang.ClassNotFoundException e) {
                System.out.println("Class not found. \n" + e.getMessage());
            } catch (java.io.IOException e) {
                System.out.println("Erro de IO. \n" + e.getMessage());
            }
            if (!controle) {
                menu();
                setControle(true);
            }
        }
    }

    public void menu() {
        System.out.println("Escolha uma OPÇÂO...");
        System.out.println("1 - Adicionar lixo");
        System.out.println("2 - Encerrar lixeira");
    }

    public void encerraLixeira() throws IOException {
        String recebido = JOptionPane.showInputDialog("Deseja mesmo encerrar a lixeira? Digite 1 para confirmar...");
        if (recebido.equals("1")) {
            Requisicao request = new Requisicao("Lixeira", "adm", idC, "PUT", "bye", "");
            output.writeObject(request);
            output.flush();
        }
    }

    public void adicionaLixo() throws IOException {
        if (disponivel) {
            System.out.println("Quanto de lixo será adicionado (em litros)?");
            Scanner opcao = new Scanner(System.in);
            float num1 = opcao.nextFloat();
            float quantAux = getQuantidade() + num1;
            if (quantAux < getCapacidade()) {
                setQuantidade(quantAux);
                Requisicao request = new Requisicao("Lixeira", "adm", idC, "PUT", "_alteraL#@_", Float.toString(quantAux));
                output.flush();
                output.writeObject(request);
                setControle(false);
            } else {
                System.out.println("Lixo adicionado ultrapassa a capacidade da lixeira...");
                setControle(false);
            }
        } else {
            System.out.println("Lixeira bloqueada! Não é possível adicionar lixo no momento...");
            setControle(false);
        }
    }

    public void coletaLixo() {
        setQuantidade(0);
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return idC;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.idC = nome;
    }

    /**
     * @return the capacidade
     */
    public float getCapacidade() {
        return capacidade;
    }

    /**
     * @param capacidade the capacidade to set
     */
    public void setCapacidade(float capacidade) {
        this.capacidade = capacidade;
    }

    /**
     * @return the quantidade
     */
    public float getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the disponivel
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * @param disponivel the disponivel to set
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * @return the controle
     */
    public boolean isControle() {
        return controle;
    }

    /**
     * @param controle the controle to set
     */
    public void setControle(boolean controle) {
        this.controle = controle;
    }
}
