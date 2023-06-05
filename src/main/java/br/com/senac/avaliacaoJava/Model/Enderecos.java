package br.com.senac.avaliacaoJava.Model;

public class Enderecos {

    private Integer idEndereco;

    private String cepCliente;

    private String ruaCliente;

    private String numCasaCliente;

    private String bairroCliente;

    private String cidadeCliente;

    private String estadoCliente;

    public Enderecos() {

    }

    public Enderecos(String cep, String Rua, String numeroCasa, String bairro, String cidade, String estado) {
        this.cepCliente = cep;
        this.ruaCliente = Rua;
        this.numCasaCliente = numeroCasa;
        this.bairroCliente = bairro;
        this.cidadeCliente = cidade;
        this.estadoCliente = estado;

    }

    public String getCepCliente() {
        return cepCliente;
    }

    public void setCepCliente(String cepCliente) {
        this.cepCliente = cepCliente;
    }

    public String getRuaCliente() {
        return ruaCliente;
    }

    public void setRuaCliente(String ruaCliente) {
        this.ruaCliente = ruaCliente;
    }

    public String getNumCasaCliente() {
        return numCasaCliente;
    }

    public void setNumCasaCliente(String numCasaCliente) {
        this.numCasaCliente = numCasaCliente;
    }

    public String getBairroCliente() {
        return bairroCliente;
    }

    public void setBairroCliente(String bairroCliente) {
        this.bairroCliente = bairroCliente;
    }

    public String getCidadeCliente() {
        return cidadeCliente;
    }

    public void setCidadeCliente(String cidadeCliente) {
        this.cidadeCliente = cidadeCliente;
    }

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }
}

