package org.example.DAO;

import org.example.Model.Cliente;
import org.example.Util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public void cadastrarCliente (Cliente cliente) throws SQLException {
        String query = """
                INSERT INTO Cliente (nome
                , cpf_cnpj
                , endereco
                , cidade
                , estado)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf_cnpj());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());
            stmt.executeUpdate();
        }
    }

    public void deletarCliente(int clienteID) throws SQLException {
        String query = """
                DELETE FROM Cliente WHERE id = ?;
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, clienteID);
            stmt.executeUpdate();
        }
    }

    public int contarPedidosCliente(int clienteID) throws SQLException {
        String query = """
                SELECT COUNT(*) FROM Pedido WHERE cliente_id = ?;
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, clienteID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public Cliente buscarClientePorID(int id) throws SQLException {
        String query = """
                SELECT nome
                ,cpf_cnpj
                ,endereco
                ,cidade
                ,estado
                FROM Cliente
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente(id,
                        rs.getString("nome"),
                        rs.getString("cpf_cnpj"),
                        rs.getString("endereco"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );
                return cliente;
            }
        }
        return null;
    }

    public Cliente buscarClientePorCnpjCpf(String cpf_cnpj) throws SQLException {
        String query = """
                SELECT id
                ,nome
                ,cpf_cnpj
                ,endereco
                ,cidade
                ,estado
                FROM Cliente
                WHERE cpf_cnpj = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cpf_cnpj);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf_cnpj"),
                        rs.getString("endereco"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );
                return cliente;
            }
        }
        return null;
    }

    public List<Cliente> listarTodosClientes() throws SQLException{
        List<Cliente> clientes = new ArrayList<>();

        String query = """
                SELECT id
                ,nome
                ,cpf_cnpj
                ,endereco
                ,cidade
                ,estado
                FROM Cliente
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf_cnpj = rs.getString("cpf_cnpj");
                String endereco = rs.getString("endereco");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");

                Cliente cliente = new Cliente(id, nome, cpf_cnpj, endereco, cidade, estado);
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public List<String> consultarCLienteComMaiorVolume() throws SQLException {
        List<String> resultado = new ArrayList<>();

        String query = """
                SELECT Cliente.id
                ,Cliente.nome
                ,Cliente.cpf_cnpj
                ,SUM(Pedido.volume_m3) AS totalVolume
                FROM Cliente
                JOIN Pedido ON Cliente.id = Pedido.cliente_id
                JOIN Entrega On Pedido.id = Entrega.pedido_id
                GROUP BY Cliente.id, Cliente.nome, Cliente.cpf_cnpj
                ORDER BY totalVolume DESC
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String linha = "Cliente: " + rs.getString("nome") +
                        " | CPF/CNPJ: " + rs.getString("cpf_cnpj") +
                        " | Volume Total: " + rs.getDouble("totalVolume");
                resultado.add(linha);
            }
        }
        return resultado;
    }

}
