package controller;

/**
 *
 * @author Aloisio Kleyner
 */
import java.io.*;
import java.net.*;
import java.util.*;
import model.Lixeira;
import model.Requisicao;
import model.Resposta;

public class Administrador implements Runnable {

    private Socket con;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private int p; // porta a ser conectado.
    private String idC = "";
    private ArrayList<Lixeira> listaLixeiras = new ArrayList();
    private ArrayList<String> ordemColetaL = new ArrayList();
    private boolean controle = true;

    public Administrador(String endereco, int porta) {
        System.out.println("SISTEMA DE COLETA DE LIXO - ADMINISTRADOR");

        this.idC = "adm";

        try {
            con = new Socket(InetAddress.getByName(endereco), porta);
            output = new ObjectOutputStream(con.getOutputStream());
            InputStream s = con.getInputStream();
            input = new ObjectInputStream(s);
            System.out.println("Administrador conectado com sucesso!\n");
        } catch (java.io.IOException er1) {
            //System.out.println(er1.getMessage());
            System.out.println("Ocorreu um erro de conexão. Encerrando o serviço...");
            System.exit(0);
        }
    }

    @Override
    public void run() {
        Requisicao request = null;
        Resposta resposta = null;
        PegaTeclado pegaT = new PegaTeclado(this);
        PegaTeclado.run();
        while (true) {
            try {
                Object obj = input.readObject();
                Class c = obj.getClass();
                String tipoInput = c.getName();
                if (tipoInput.contains("Resposta")) {
                    
                } else if (tipoInput.contains("Requisicao")) {
                    request = (Requisicao) obj;
                    if (request != null) {
                        if (request.getTipoCliente().equals("Lixeira") && request.getIdD().equals(idC) && request.getTipoRequest().equals("PUT")) {
                            if (request.getOperacao().equals("_alteraL#@_")) {
                                Iterator<Lixeira> iteradorL = getListaLixeiras().iterator();
                                Lixeira lixeiraAux = null;
                                boolean control = false;
                                while (!control) {
                                    if (iteradorL.hasNext()) {
                                        lixeiraAux = iteradorL.next();
                                        if (lixeiraAux.getIdC().equals(request.getIdR())) {
                                            control = true;
                                        }
                                    }
                                }
                                if (!(lixeiraAux == null)) {
                                    lixeiraAux.setQuantidade(Float.parseFloat(request.getDadoEnviado()));
                                    setControle(false);
                                    System.out.println("Adicionado lixo a Lixeira '"+request.getIdR()+"'...\n");
                                    output.flush();
                                    output.writeObject(new Resposta("203_" + request.getIdR()));
                                    
                                }

                            }
                        } else if (request.getTipoCliente().equals("Caminhao") && request.getIdD().equals(idC) && request.getTipoRequest().equals("PUT")) {
                            if (request.getOperacao().equals("_coletaL#@_")) {
                                coletaLixeira(request.getDadoEnviado());
                                output.flush();
                                output.writeObject(new Resposta("301_" + request.getDadoEnviado()));
                                setControle(false);
                            }
                        }

                    }
                }
                // Verifica se existe uma requisicao

            } catch (java.io.OptionalDataException e) {
                System.out.println("Erro no desconectar.\n" + e.getMessage());
            } catch (java.lang.ClassNotFoundException e) {
                System.out.println("Class not found. \n" + e.getMessage());
            } catch (java.io.IOException e) {
                System.out.println("Erro de IO. \n" + e.getMessage());
            }
            if (!isControle()) {
                menu();
                setControle(true);
            }
        }
    }
    
    public void coletaLixeira(String idLixeira){
        Iterator <String> iteradorC = ordemColetaL.iterator();
        boolean controle = false;
        String aux = null;
        while (!controle && iteradorC.hasNext()){
            aux = iteradorC.next();
            if (aux.equals(idLixeira)){
                System.out.println("A Lixeira '"+aux+"' foi coletada.\n");
                ordemColetaL.remove(aux);  
            }
        }
    }

    public void menu() {
        System.out.println("Escolha uma OPÇÂO...");
        System.out.println("1 - Informações das Lixeiras");
        System.out.println("2 - Bloquear ou liberar lixeiras");
        System.out.println("3 - Alterar ordem de coleta");
        System.out.println("4 - Coleta em tempo real");
    }

    public void infoLixeiras() {

    }

    public void blockOrNotLixeiras() {

    }

    public void alterarColeta() {

    }

    public void infoColeta() {

    }

    /**
     * @return the listaLixeiras
     */
    public ArrayList<Lixeira> getListaLixeiras() {
        return listaLixeiras;
    }

    /**
     * @param listaLixeiras the listaLixeiras to set
     */
    public void setListaLixeiras(ArrayList<Lixeira> listaLixeiras) {
        this.listaLixeiras = listaLixeiras;
    }

    /**
     * @return the ordemColetaL
     */
    public ArrayList<String> getOrdemColetaL() {
        return ordemColetaL;
    }

    /**
     * @param ordemColetaL the ordemColetaL to set
     */
    public void setOrdemColetaL(ArrayList<String> ordemColetaL) {
        this.ordemColetaL = ordemColetaL;
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
