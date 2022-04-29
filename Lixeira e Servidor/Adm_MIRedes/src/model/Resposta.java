package model;


/**
 *
 * @author Aloisio Kleyner
 */
public class Resposta {
    private String codigoR;
    
    public Resposta(String codigoResposta){
        this.codigoR = codigoResposta;
    }

    /**
     * @return the codigoR
     */
    public String getCodigoR() {
        return codigoR;
    }

    /**
     * @param codigoR the codigoR to set
     */
    public void setCodigoR(String codigoR) {
        this.codigoR = codigoR;
    }
}
