package org.example.DAO;

import org.example.DTO.EntregaDetalhadaDTO;
import org.example.Model.Entrega;
import org.example.Util.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {
    public void inserirEntrega (Entrega entrega) throws SQLException{
        String query = """
                INSERT INTO (pedido_id,motorista_id,data_saida,status) VALUES (?,?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,entrega.getPedido_id());
            stmt.setInt(2, entrega.getMotorista_id());
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.setString(4, entrega.getStatus());
            stmt.executeUpdate();
        }
    }

    public void atualizarStatusEntrega(int id, String novoStatus) throws SQLException {
        String query = """
               UPDATE entrega
               SET status = ?
               WHERE id = ?
               """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1,novoStatus);
                stmt.setInt(2,id);

            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas == 0){
                    System.out.println("Nenhuma entrega encontrada com o ID informado");
            }
        }
    }

    public void excluirEntrega (int entregaID) throws SQLException{
        String query = """
                DELETE FROM Entrega WHERE id = ?; 
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, entregaID);
            stmt.executeUpdate();
        }
    }

    public List<Entrega> buscarEntregaPorID(int entregaID) throws SQLException {
        List<Entrega> entregas = new ArrayList<>();
        String query = """
                SELECT id
                ,pedido_id
                ,motorista_id
                ,data_saida
                ,data_entrega
                ,status
                FROM Entrega
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,entregaID);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Entrega entrega = new Entrega(
                        rs.getInt("id"),
                        rs.getInt("pedido_id"),
                        rs.getInt("motorista_id"),
                        rs.getDate("data_saida").toLocalDate(),
                        rs.getDate("data_entrega").toLocalDate(),
                        rs.getString("status")
                );
                entregas.add(entrega);
            }
        }
        return entregas;
    }

    public List<EntregaDetalhadaDTO> listarTodasEntregas() throws SQLException {
        List<EntregaDetalhadaDTO> entregas = new ArrayList<>();
        String query = """
                SELECT Entrega.id AS entrega_id
                ,Entrega.data_saida
                ,Entrega.status
                ,Pedido.is AS pedido_id
                ,Cliente.nome AS cliente_nome
                ,Cliente.cpf_cnpj AS cliente_cpf_cnpj
                ,Mototista.nome AS motorista_nome
                ,Motorista.cnh AS motorista_cnh
                FROM Entrega
                JOIN Pedido ON Entrega.pedido_id = Pedido.id
                JOIN Cliente ON Pedido.cliente_id = Cleinte.id
                JOIN Motorista ON Entrega.motorista_id = Motorista.id
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                EntregaDetalhadaDTO entregaDetalhadaDTO = new EntregaDetalhadaDTO(
                        rs.getInt("entreda_id"),
                        rs.getInt("pedido_id"),
                        rs.getDate("data_saida").toLocalDate(),
                        rs.getString("status"),
                        rs.getString("cliente_nome"),
                        rs.getString("cliente_cpf_cnpj"),
                        rs.getString("motorista_nome"),
                        rs.getString("motorista_cnh")
                );
                entregas.add(entregaDetalhadaDTO);
            }
        }
        return entregas;
    }

    public List<Entrega> entregasAtrasadasPorCidade (String cidade) throws SQLException{
        List<Entrega> entregas = new ArrayList<>();
        String query = """
                SELECT Entrega.id
                ,Entrega.pedido_id
                ,Entrega.motorista_id
                ,Entrega.data_saida
                ,Entrega.data_entrega
                ,Entrega.status
                FROM Entrega
                JOIN Pedido ON Entrega.pedido_id = Pedido_id
                JOIN Cliente ON Pedido.cliente_id = Cliente_id
                WHERE Cliente.cidade = ?
                AND Entrega.status = 'ATRASADA'
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,cidade);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Entrega entrega = new Entrega(
                        rs.getInt("id"),
                        rs.getInt("pedido_id"),
                        rs.getInt("motorista_id"),
                        rs.getDate("data_saida").toLocalDate(),
                        rs.getDate("data_entrega").toLocalDate(),
                        rs.getString("status")
                );
                entregas.add(entrega);
            }
        }
        return entregas;
    }





}
