package org.example.DAO;

import org.example.Model.HistoricoEntrega;
import org.example.Util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HistoricoEntregaDAO {
    public void cadastrarHistoricoEntraga(HistoricoEntrega historicoEntrega) throws SQLException{
        String query = """
                INSERT INTO HistoricoEntrega (entrega_id,descricao) VALUES (?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,historicoEntrega.getEntrega_id());
            stmt.setString(2,historicoEntrega.getDescricao());
            stmt.executeUpdate();
        }
    }

    public List<HistoricoEntrega> listarEventosEntregaEspecifica(int  entrega_id) throws SQLException {
        List<HistoricoEntrega> historico = new ArrayList<>();
        String query = """
                SELECT id
                ,entrega_id
                ,data_evento
                ,descricao
                FROM HistoricoEntraga
                WHERE entrega_id = ?
                ORDER BY data_evento ASC
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, entrega_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                HistoricoEntrega historicoEntrega = new HistoricoEntrega(
                        rs.getInt("id"),
                        rs.getInt("entrega_id"),
                        rs.getTimestamp("data_evento").toLocalDateTime(),
                        rs.getString("descricao")
                );
                historico.add(historicoEntrega);
            }
        }
        return historico;
    }
}
