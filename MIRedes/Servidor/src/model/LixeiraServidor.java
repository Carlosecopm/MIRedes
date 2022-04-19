package model;

public class LixeiraServidor {
    private String nome;
    private float capacidade = 0;
    private float quantidade = 0;
    private boolean disponivel = true;
    private boolean controle = true;

    public LixeiraServidor(String nome, float capacidade, float quantidade, boolean disponivel, boolean controle) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.quantidade = quantidade;
        this.disponivel = disponivel;
        this.controle = controle;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(float capacidade) {
        this.capacidade = capacidade;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean isControle() {
        return controle;
    }

    public void setControle(boolean controle) {
        this.controle = controle;
    }
}
