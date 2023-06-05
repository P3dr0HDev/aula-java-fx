package br.com.senac.avaliacaoJava.ServiceCliente;

import br.com.senac.avaliacaoJava.db.ConexaoDataBase;
import br.com.senac.avaliacaoJava.Model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCliente {

    private static ConexaoDataBase conexao = new ConexaoDataBase();

    public static List<Cliente> carregarClientes(){
        List<Cliente> out = new ArrayList<>();

        try {
            Connection conn = conexao.getConexao();

            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("select * from cliente;");


            while (rs.next()){
                Cliente cli = new Cliente(rs.getInt("id"), rs.getString("cliente"), rs.getString("documento"), rs.getString("rg"), rs.getString("email"), rs.getString("telefone"));

                out.add(cli);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return out;
    }

    public static void inserirCliente(Cliente cliente){
        try {
            Connection conn = conexao.getConexao();

            String sqlInsert = "insert into cliente (cliente, documento, rg, email, telefone) values (?, ?, ?, ?, ?)";

            PreparedStatement pre = conn.prepareStatement(sqlInsert);
            pre.setString(1, cliente.getNomeCliente());
            pre.setString(2, cliente.getDocCliente());
            pre.setString(3, cliente.getRgCliente());
            pre.setString(4, cliente.getEmailCliente());
            pre.setString(5, cliente.getTelCliente());



            pre.execute();

            pre.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deletarCliente(int idCliente){
        try {
            Connection conn = conexao.getConexao();

            String deleteSql = "delete from cliente where id = ?";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, idCliente);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean atualizarCliente(int idCliente, Cliente cli){
        try {
            Connection conn = conexao.getConexao();

            String updateSql = "update cliente set cliente = ?, documento = ?, rg = ?, email = ?, telefone = ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(updateSql);
            ps.setString(1, cli.getNomeCliente());
            ps.setString(2, cli.getDocCliente());
            ps.setString(3, cli.getRgCliente());
            ps.setString(4, cli.getEmailCliente());
            ps.setString(5, cli.getTelCliente());
            ps.setInt(6, idCliente);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
//Alteração commit Git pt1
    public static boolean buscarClienteByDocumento(String documento){
        try{
            Connection conn = conexao.getConexao();

            String selectSql = "select id from cliente where documento = '" + documento + "'";

            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery(selectSql);

            return rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }//Alteração Git Teste pt2

}