package org.example.DAO;

import org.example.Model.Pedido;
import org.example.Util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    public void cadastrarPedido(Pedido pedido) throws SQLException {
        String query = """
                INSERT INTO Pedido(cliente_id
                ,volume_m3
                ,peso_kg
                ,status)
                VALUES (?,?,?,?) 
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,pedido.getCliente_id());
            stmt.setDouble(2,pedido.getVolume());
            stmt.setDouble(3, pedido.getPeso());
            stmt.setString(4,pedido.getStatus());
            stmt.executeUpdate();
        }
    }

    public void atualizarPedido (Pedido pedido) throws SQLException {
        String query = """
                UPDATE Pedido 
                SET status = ?
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,pedido.getStatus());
            stmt.setInt(2, pedido.getId());
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Nenhum pedido encontrado com o ID informado.");
            }
        }
    }

    public void cancelarPedido (Pedido pedido) throws SQLException{
        String query = """
                UPDATE Pedido
                SET status = 'CANCELADO'
                WHERE id = ?;
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,pedido.getId());
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Nenhum pedido encontrado com o ID informado.");
            }
        }
    }

    public  List<Pedido> buscarPedidoID (int id) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String query = """
                SELECT id 
                ,cliente_id
                ,data_pedido
                ,volume_m3
                ,peso_kg
                ,status
                FROM Pedido
                WHERE cliente_id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getTimestamp("data_pedido").toLocalDateTime(),
                        rs.getDouble("volume_m3"),
                        rs.getDouble("peso_kg"),
                        rs.getString("status")
                );
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    public  List<Pedido> buscarPedidoCpfCnpj(String cpf_cnpj) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String query = """
                SELECT Pedido.id 
                ,Pedido.cliente_id
                ,Pedido.data_pedido
                ,Pedido.volume_m3
                ,Pedido.peso_kg
                ,Pedido.status
                FROM Pedido
                JOIN Cliente ON Pedido.cliente_id = Cliente.id
                WHERE Cliente.cpf_cnpj = ? 
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1,cpf_cnpj);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getTimestamp("data_pedido").toLocalDateTime(),
                        rs.getDouble("volume_m3"),
                        rs.getDouble("peso_kg"),
                        rs.getString("status")
                );
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    public List<Pedido> ListaPedidosPedentesEstado (String estado) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        String query = """
                SELECT Pedido.id
                ,Pedido.cliente_id
                ,Pedido.data_pedido
                ,Pedido.volume_m3
                ,Pedido.peso_kg
                ,Pedido.status
                FROM Pedido
                JOIN Cliente ON Pedido.cliente_id = Cliente.id
                WHERE Cliente.estado = ?
                AND Pedido.status = 'PENDENTE'
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

                stmt.setString(1, estado);
                ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getTimestamp("data_pedido").toLocalDateTime(),
                        rs.getDouble("volume_m3"),
                        rs.getDouble("peso_kg"),
                        rs.getString("status")
                );
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }
}
