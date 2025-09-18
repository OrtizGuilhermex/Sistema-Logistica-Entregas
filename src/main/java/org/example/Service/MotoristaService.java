package org.example.Service;

import org.example.DAO.MotoristaDAO;
import org.example.Model.Motorista;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotoristaService {

    MotoristaDAO motoristaDAO = new MotoristaDAO();

    public void cadastrarMotorista(Motorista motorista){
        try{
            motoristaDAO.cadastraMotorista(motorista);
        } catch (SQLException e){
            System.out.println("Erro ao cadastrar Motorista!");
            e.printStackTrace();
        }
    }

    public void atualizarMotorista(Motorista motorista){
        try{
            motoristaDAO.atualizarMotorista(motorista);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Motorista buscarMotoristaPorID(int id){
        try{
            return motoristaDAO.burcarMotoristaPorID(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Motorista> listarTodosMotoristas(){
        try{
            return motoristaDAO.listarTodosMotoristas();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public String excluirMotorista(int motoristaID){
        try{
            int entregas = motoristaDAO.totalEntregasPorMotorista(motoristaID);
            if (entregas > 0){
                return  "Não é possível excluir: Motorista possui entregas associadas.";
            }
            motoristaDAO.deletarMotorista(motoristaID);
            return "Motorista Excluido com sucesso!";
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
