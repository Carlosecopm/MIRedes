package util;

import model.LixeiraServidor;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class Servidor extends Thread{

    private static ArrayList<BufferedWriter>clientes;
    private static ServerSocket server;
    private String nome;
    private Socket con;
    private InputStream in;
    private InputStreamReader leitorDados;
    private BufferedReader bufferedReader;
    private ObjectInputStream input;
    LixeiraServidor lixeiraServidor = new LixeiraServidor();
    Scanner scanner = new Scanner(System.in);


    public Servidor(){

    }
    public Servidor(Socket con){
        this.con = con;
        try {
            in  = con.getInputStream();
            leitorDados = new InputStreamReader(in);
            bufferedReader = new BufferedReader(leitorDados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

        try{
            String msg;
            OutputStream output =  this.con.getOutputStream();
            Writer writer= new OutputStreamWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            clientes.add(bufferedWriter);
            nome = msg = bufferedReader.readLine();
            Map<String, String> map = new HashMap<>();

            while(!"Sair".equalsIgnoreCase(msg) && msg != null)
            {
                if (msg.equalsIgnoreCase("GET")){
                    String jsonString = getLixeiraData(nome);

                } else if (msg.equalsIgnoreCase("PUT")) {
                    String nome = msg = bufferedReader.readLine();
                    String capacidade = msg = bufferedReader.readLine();
                    String disponivel = msg = bufferedReader.readLine();

                    setLixeiraData(nome, capacidade, disponivel);
                }

                msg = bufferedReader.readLine();
                sendToAll(bufferedWriter, msg);
                System.out.println(msg);
            }

        }catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void sendToAll(BufferedWriter bwSaida, String msg) throws  IOException
    {
        BufferedWriter bwS;

        for(BufferedWriter bw : clientes){
            bwS = (BufferedWriter)bw;
            if(!(bwSaida == bwS)){
                if (!(bw.equals(this))) {
                    bw.write(nome + " -> " + msg + "\r\n");
                    bw.flush();
                }
            }
        }
    }
    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        return sb.toString();
    }
    public static String getLixeiraData(String name) throws IOException{
        HttpURLConnection connection = (HttpURLConnection) new URL("127.0.0.1" + name).openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if(responseCode == 200){
            String json = "{\n";
            json += "\"nome\": " + JSONObject.quote(lixeiraServidor.getName()) + ",\n";
            json += "\"porcentagem\": " + JSONObject.quote(lixeiraServidor.getPorcentagem()) + ",\n";
            json += "}";
            response.getOutputStream().println(json);
            while(scanner.hasNextLine()){
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();

            return response;
        }

        // an error happened
        return null;
    }
    public static void setLixeiraData(String nome, String birthYear, String about) throws IOException{
        HttpURLConnection connection = (HttpURLConnection) new URL("127.0.0.1" + nome).openConnection();

        connection.setRequestMethod("POST");

        String postData = "nome=" + URLEncoder.encode(nome);
        postData += "&capacidade=" + URLEncoder.encode(about);
        postData += "&disponivel=" + birthYear;

        connection.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write(postData);
        wr.flush();

        int responseCode = connection.getResponseCode();
        if(responseCode == 200){
            System.out.println("POST was successful.");
        }
        else if(responseCode == 401){
            System.out.println("Wrong password.");
        }
    }
    public String gerenciarLixeira() {

        System.out.println("Bem vindo Admin.\n Escolha a opcao desejada");
        System.out.println("Limpar \n Bloquear");
        String limparBloquear = scanner.nextLine();
        return limparBloquear;
    }

    public static void iniciarServidor() {

        try{
            //Cria os objetos necessário para instânciar o servidor
            JLabel lblMessage = new JLabel("Porta do Servidor:");
            JTextField txtPorta = new JTextField("12345");
            Object[] texts = {lblMessage, txtPorta };
            JOptionPane.showMessageDialog(null, texts);
            server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
            clientes = new ArrayList<BufferedWriter>();
            JOptionPane.showMessageDialog(null,"Servidor ativo na porta: "+
                    txtPorta.getText());

            while(true){
                System.out.println("Aguardando conexão...");
                Socket con = server.accept();
                System.out.println("Cliente conectado...");
                Thread t = new Servidor(con);
                t.start();
            }

        }catch (Exception e) {

            e.printStackTrace();
        }
    }
}