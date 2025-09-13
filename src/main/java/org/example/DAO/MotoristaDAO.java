package org.example.DAO;

import org.example.Model.Entrega;
import org.example.Model.Motorista;
import org.example.Util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO {
    public void cadastraMotorista (Motorista motorista) throws SQLException {
        String query = """
                INSERT INTO Motorista(
                 nome
                ,cnh
                ,veiculo
                ,cidade_base)
                VALUES (?,?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCnh());
            stmt.setString(3,motorista.getVeiculo());
            stmt.setString(4,motorista.getCidade_base());
            stmt.executeUpdate();
        }
    }

    public void atualizarMotorista(Motorista motorista) throws SQLException {
        String query = """
                UPDATE motorista
                SET nome = ?, cnh = ?, veiculo = ?, cidade_base = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCnh());
            stmt.setString(3, motorista.getVeiculo());
            stmt.setString(4, motorista.getCidade_base());
            stmt.setInt(5, motorista.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("⚠ Nenhum motorista encontrado com o ID informado.");
            }
        }
    }

    public void deletarMotorista(int motoristaID) throws SQLException{
        String query = """
                DELETE FROM Motorista WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1,motoristaID);
            stmt.executeUpdate();
        }
    }

    public Motorista burcarMotoristaPorID(int id) throws SQLException{
        String query = """
                SELECT nome
                ,cnh
                ,veiculo
                ,cidade_base
                FROM Motorista
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Motorista motorista = new Motorista(id,
                        rs.getString("nome"),
                        rs.getString("cnh"),
                        rs.getString("veiculo"),
                        rs.getString("cidade_base")
                );
                return motorista;
            }
        }
        return null;
    }

    public List<Motorista> listarTodosMotoristas() throws SQLException{
        List<Motorista> motoristas = new ArrayList<>();

        String query = """
                SELECT id
                ,nome
                ,cnh
                ,veiculo
                ,cidade_base
                FROM Motorista
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cnh = rs.getString("cnh");
                String veiculo = rs.getString("veiculo");
                String cidade_base = rs.getString("cidade_base");

                Motorista motorista = new Motorista(id, nome, cnh, veiculo, cidade_base);
                motoristas.add(motorista);
            }
        }
        return motoristas;
    }

    public int totalEntregasPorMotorista(int motoristaID) throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM entrega WHERE motorista_id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, motoristaID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public List<String> totalEntregasMotorista() throws SQLException {
        List<String> entregas = new ArrayList<>();

            String query = """
                SELECT Motorista.nome,
                    Motorista.id,
                    COUNT(Entrega.id) AS TotalEntregas
                    FROM Entrega
                    JOIN Motorista ON Entrega.motorista_id = Motorista.id
                    GROUP BY Motorista.id, Motorista.nome
                    ORDER BY TotalEntregas DESC
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String linha = "Motorista: " + rs.getString("nome") +
                        " | Total de Entregas: " + rs.getDouble("TotalEntregas");
                entregas.add(linha);
            }
            return entregas;
        }
    }
}
