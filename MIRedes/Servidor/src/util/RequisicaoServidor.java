package util;

public class RequisicaoServidor {
    private String tipoRequest;
    private String idC;
    private String dadoEnviado;

    public RequisicaoServidor(){

    }
    public RequisicaoServidor(String id, String tipoRequest, String dadoEnviado){
        this.idC = id;
        this.tipoRequest = tipoRequest;
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
}
