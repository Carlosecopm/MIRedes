package model;

/**
 *
 * @author Aloisio Junior
 */
public class Lixeira {
    private String idC = "";
    private float capacidade = 0;
    private float quantidade = 0;
    private boolean disponivel = true;
    
    

    /**
     * @return the idC
     */
    public String getIdC() {
        return idC;
    }

    /**
     * @param idC the idC to set
     */
    public void setIdC(String idC) {
        this.idC = idC;
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
}
