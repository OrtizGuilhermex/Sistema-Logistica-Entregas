package org.example.DAO;

import org.example.Model.Entrega;
import org.example.Util.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntregaDAO {
    public void inserirEntrega (Entrega entrega) throws SQLException{
        String query = """
                INSERT INTO (pedido_id,motorista_id,data_saida,status) VALUES (?,?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,entrega.getPedido_id());
            stmt.setInt(2, entrega.getMotorista_id());
            stmt.setDate(3, Date.valueOf(entrega.getData_saida()));
            stmt.setString(4, entrega.getStatus());
            stmt.executeUpdate();
        }
    }


}
