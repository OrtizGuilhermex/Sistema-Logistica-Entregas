package org.example.Service;

import org.example.DAO.EntregaDAO;
import org.example.DAO.MotoristaDAO;
import org.example.Model.Entrega;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioService {

    EntregaDAO entregaDAO = new EntregaDAO();
    MotoristaDAO motoristaDAO = new MotoristaDAO();

    public List<String> totalEntregasMotorista(){
        try{
            return motoristaDAO.totalEntregasMotorista();
        } catch (SQLException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
