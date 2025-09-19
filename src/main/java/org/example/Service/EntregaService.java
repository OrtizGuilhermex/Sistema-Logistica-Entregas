package org.example.Service;

import org.example.DAO.EntregaDAO;
import org.example.DAO.HistoricoEntregaDAO;
import org.example.DTO.EntregaDetalhadaDTO;
import org.example.Model.Entrega;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntregaService {

    private final EntregaDAO entregaDAO = new EntregaDAO();
    private final HistoricoEntregaDAO historicoEntregaDAO = new HistoricoEntregaDAO();

    public void cadastrarEntrega(Entrega entrega){
        try{
            entregaDAO.inserirEntrega(entrega);
        } catch (SQLException e){
            System.out.println("erro ao Inserir uma entrega");
            e.printStackTrace();
        }
    }

    public void atualizarStatusEntrega(int id, String novoStatus){
        try{
            entregaDAO.atualizarStatusEntrega(id, novoStatus);
            System.out.println("Status atualizado com sucesso!");
        } catch (SQLException e){
            System.out.println("Erro ao atualizar o Status!");
            e.printStackTrace();
        }
    }

    public void excluirEntrega(int entregaId){
        try{
            if (historicoEntregaDAO.possuiHistoricoEntrega(entregaId)) {
                System.out.println("Não é possível excluir: entrega possui histórico associado.");
                return;
            }
            entregaDAO.excluirEntrega(entregaId);
            System.out.println("Entrega excluída com sucesso!");
        } catch (SQLException e){
            System.out.println("Erro ao excluir Entrega!");
            e.printStackTrace();
        }
    }

    public Entrega buscarEntregaPorID(int id){
        try{
            return  entregaDAO.buscarEntregaPorID(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<EntregaDetalhadaDTO> listarTodasEntregas(){
        try{
            return entregaDAO.listarTodasEntregas();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Entrega> listarEntregasPorCidade(String cidade){
        try{
            return entregaDAO.entregasAtrasadasPorCidade(cidade);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
