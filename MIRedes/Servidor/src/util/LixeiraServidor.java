import java.io.PrintStream;
import java.util.concurrent.ThreadLocalRandom;

public class LixeiraServidor {

    private float porcentagem;
    private int setor;
    private String nome;

    private PrintStream object;

    public LixeiraServidor() {

    }
    public LixeiraServidor(String nome, int setor, float porcentagem, PrintStream object) {
        this.nome = nome;
        this.porcentagem = porcentagem;
        this.setor = setor;
        this.object = object;
    }
    public int getSetor() {
        return setor;
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }



    public float getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(float porcentagem) {
        this.porcentagem = porcentagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PrintStream getObject() {
        return object;
    }

    public void setObject(PrintStream object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "LixeiraServidor{" +
                "porcentagem=" + porcentagem +
                ", setor=" + setor +
                ", nome='" + nome + '\'' +
                ", object=" + object +
                '}';
    }
}