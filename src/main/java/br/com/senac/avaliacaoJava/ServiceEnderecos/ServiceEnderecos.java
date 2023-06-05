package br.com.senac.avaliacaoJava.ServiceEnderecos;

import br.com.senac.avaliacaoJava.db.ConexaoDataBase;
import br.com.senac.avaliacaoJava.Model.Enderecos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEnderecos {

        private static ConexaoDataBase conexao = new ConexaoDataBase();

        public static List<Enderecos> carregarEnderecos() {
            List<Enderecos> out = new ArrayList<>();

            try {
                Connection conn = conexao.getConexao();

                Statement sta = conn.createStatement();
                ResultSet rs = sta.executeQuery("select * from enderecos;");

                while (rs.next()) {
                    Enderecos end = new Enderecos(rs.getInt("id"), rs.getString("cep"), rs.getString("rua"), rs.getString("numero"), rs.getString("bairro"), rs.getString("cidade"), rs.getString("estado"));

                    out.add(end);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return out;
        }

        public static void inserirEnderecos(Enderecos cliente) {
            try {
                Connection conn = conexao.getConexao();

                String sqlInsert = "insert into enderecos (cep, rua, numero, bairro, cidade, estado) values (?, ?, ?, ?, ?, ?)";

                PreparedStatement pre = conn.prepareStatement(sqlInsert);
                pre.setString(1, cliente.getCepCliente());
                pre.setString(2, cliente.getRuaCliente());
                pre.setString(3, cliente.getNumCasaCliente());
                pre.setString(4, cliente.getBairroCliente());
                pre.setString(5, cliente.getCidadeCliente());
                pre.setString(6, cliente.getEstadoCliente());


                pre.execute();

                pre.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static boolean deletarEnderecos(int idEndCliente) {
            try {
                Connection conn = conexao.getConexao();

                String deleteSql = "delete from enderecos where id = ?";

                PreparedStatement ps = conn.prepareStatement(deleteSql);
                ps.setInt(1, idEndCliente);

                return ps.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        public static boolean atualizarEnderecos(int idCliente, Enderecos end) {
            try {
                Connection conn = conexao.getConexao();

                String updateSql = "update clientes set cep = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, where id = ?";

                PreparedStatement ps = conn.prepareStatement(updateSql);
                ps.setString(1, end.getCepCliente());
                ps.setString(2, end.getRuaCliente());
                ps.setString(3, end.getNumCasaCliente());
                ps.setString(4, end.getBairroCliente());
                ps.setString(5, end.getCidadeCliente());
                ps.setString(6, end.getEstadoCliente());

                ps.setInt(3, idCliente);

                return ps.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        public static boolean buscarEnderecoByCep(String cep) {
            try {
                // rever!!!!

                Connection conn = conexao.getConexao();

                String selectSql = "select id from endereços where cep = '" + cep + "'";

                Statement sta = conn.createStatement();
                ResultSet rs = sta.executeQuery(selectSql);

                return rs.next();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
}