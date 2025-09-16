package org.example.Service;

import org.example.DAO.PedidoDAO;
import org.example.Model.Motorista;
import org.example.Model.Pedido;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    PedidoDAO pedidoDAO = new PedidoDAO();

    public void cadastrarPedido(Pedido pedido){
        try{
            pedidoDAO.cadastrarPedido(pedido);
        } catch (SQLException e){
            System.out.println("Erro ao cadastrar Pedido!");
            e.printStackTrace();
        }
    }

    public void atualizarPedido(Pedido pedido) {
        try{
            pedidoDAO.atualizarPedido(pedido);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void cancelarPedido(Pedido pedido){
        try{
            pedidoDAO.cancelarPedido(pedido);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Pedido> buscarPedidoPorId(int id){
        try {
            return pedidoDAO.buscarPedidoID(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Pedido> buscarPedidoCpfCnpj(String cpf_cnpj){
        try {
            return pedidoDAO.buscarPedidoCpfCnpj(cpf_cnpj);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Pedido> ListarPedidosPendenteEstado(String estado){
        try {
            return pedidoDAO.ListaPedidosPedentesEstado(estado);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
