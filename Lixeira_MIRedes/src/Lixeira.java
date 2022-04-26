
import com.github.kwhat.jnativehook.GlobalScreen;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

class Lixeira implements Runnable {

    private Socket con;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private int p; // porta a ser conectado.
    private String idC = "";
    private float capacidade = 0;
    private float quantidade = 0;
    private boolean disponivel = true;
    private boolean controle = true;

    // o construtor cria e abre uma conexao com o
// servidor.
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
        } catch (java.io.IOException er1) {
            System.out.println(er1.getMessage());
        }

        System.out.println("Lixeira cadastrada com sucesso!");
    }

// iniciada - metodo start().
    @Override
    public void run() {
        Requisicao request = null;
        PegaTeclado pegaT = new PegaTeclado(this);
        pegaT.run();
        while (true) {
            try {
                // Verifica se existe uma requisicao
                request = (Requisicao) input.readObject();
                if (request != null) {
                    if (request.getTipoRequest().equals("PUT")) {
                        if (request.getDadoEnviado().equals("_bloqueaL#@_")) {
                            setDisponivel(false);
                            System.out.println("A Lixeira foi bloqueada!");
                            System.out.println("");
                            output.writeObject(new Resposta("200"));
                            output.flush();
                            menu();
                        } else if (request.getDadoEnviado().equals("_desbloqueaL#@_")){
                            setDisponivel(false);
                            System.out.println("A Lixeira foi desbloqueada!");
                            System.out.println("");
                            output.flush();
                            output.writeObject(new Resposta("200"));
                            menu();
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
            if (!controle){
                menu();
                setControle(false);
            }
        }
    }

    public void menu(){
        System.out.println("Escolha uma OPÇÂO...");
        System.out.println("1 - Adicionar lixo");
        System.out.println("2 - Encerrar lixeira");
    }

    public void encerraLixeira() throws IOException {
        String recebido = JOptionPane.showInputDialog("Deseja mesmo encerrar a lixeira? Digite 1 para confirmar...");
        if (recebido.equals("1")) {
            Requisicao request = new Requisicao(idC, "PUT", "bye");
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
                Requisicao request = new Requisicao(idC, "PUT", Float.toString(quantAux));
                output.writeObject(request);
                output.flush();
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
