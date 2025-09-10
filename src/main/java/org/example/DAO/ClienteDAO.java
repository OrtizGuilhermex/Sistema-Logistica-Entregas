package org.example.DAO;

import org.example.Model.Cliente;
import org.example.Util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            stmt.setString(2, cliente.getCpf_cnpj());stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());
            stmt.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");
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

    public int buscarClientePorID(int id) throws SQLException {
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

            if (rs.next()){
                rs.getInt("id");
                rs.getString("cpf_cnpj");
                rs.getString("endereco");
                rs.getString("cidade");
                rs.getString("estado");
            }
        }
        return id;
    }

}
