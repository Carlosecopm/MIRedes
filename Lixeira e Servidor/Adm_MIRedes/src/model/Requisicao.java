package model;

/**
 *
 * @author Aloisio Kleyner
 */
public class Requisicao {
    private String tipoCliente;
    private String idD;
    private String idR;
    private String tipoRequest;
    private String operacao;
    private String dadoEnviado;
    
    public Requisicao(String tipoCliente, String idD, String idR, String tipoRequest, String operacao, String dadoEnviado){
        this.tipoCliente = tipoCliente;
        this.idD = idD;
        this.idR = idR;
        this.tipoRequest = tipoRequest;
        this.operacao = operacao;
        this.dadoEnviado = dadoEnviado;
    }

    /**
     * @return the tipoRequest
     */
    public String getTipoRequest() {
        return tipoRequest;
    }

    /**
     * @param tipoRequest the tipoRequest to set
     */
    public void setTipoRequest(String tipoRequest) {
        this.tipoRequest = tipoRequest;
    }

    /**
     * @return the dadoEnviado
     */
    public String getOperacao() {
        return operacao;
    }

    /**
     * @param operacao the operacao to set
     */
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    /**
     * @return the idD
     */
    public String getIdD() {
        return idD;
    }

    /**
     * @param idD the idD to set
     */
    public void setIdD(String idD) {
        this.idD = idD;
    }

    /**
     * @return the tipoCliente
     */
    public String getTipoCliente() {
        return tipoCliente;
    }

    /**
     * @param tipoCliente the tipoCliente to set
     */
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    /**
     * @return the dadoEnviado
     */
    public String getDadoEnviado() {
        return dadoEnviado;
    }

    /**
     * @param dadoEnviado the dadoEnviado to set
     */
    public void setDadoEnviado(String dadoEnviado) {
        this.dadoEnviado = dadoEnviado;
    }
    
    /**
     * @return the idR
     */
    public String getIdR() {
        return idR;
    }

    /**
     * @param idR the idR to set
     */
    public void setIdR(String idR) {
        this.idR = idR;
    }
}
