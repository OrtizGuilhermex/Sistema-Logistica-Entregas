package org.example.Service;

import org.example.DAO.ClienteDAO;

import java.sql.SQLException;

public class ClienteService {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public String excluirClientes(int clienteID){
        try {
            int pedidos = clienteDAO.contarPedidosCliente(clienteID);
            if (pedidos > 0){
                return  "Não é possível excluir: Cliente possui pedidos associados.";
            }
            clienteDAO.deletarCliente(clienteID);
            return "Cliente excluído com sucesso.";
        } catch (SQLException e){
            e.printStackTrace();
            return  "Erro ao excluir cliente: " + e.getMessage();
        }
    }
}
