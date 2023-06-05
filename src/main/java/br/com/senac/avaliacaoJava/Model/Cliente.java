package br.com.senac.avaliacaoJava.Model;

public class Cliente {

    private int idCliente;

    private String nomeCliente;

    private String docCliente;

    private String rgCliente;

    private String emailCliente;

    private String telCliente;

    public Cliente(){

    }

    public Cliente(int id, String nome, String documento, String rg, String email, String telefone) {
        this.idCliente = id;
        this.nomeCliente = nome;
        this.docCliente = documento;
        this.rgCliente = rg;
        this.emailCliente = email;
        this.telCliente = telefone;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDocCliente() {
        return docCliente;
    }

    public void setDocCliente(String docCliente) {
        this.docCliente = docCliente;
    }

    public String getRgCliente() {
        return rgCliente;
    }

    public void setRgCliente(String rgCliente) {
        this.rgCliente = rgCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelCliente() {
        return telCliente;
    }

    public void setTelCliente(String telCliente) {
        this.telCliente = telCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }


}