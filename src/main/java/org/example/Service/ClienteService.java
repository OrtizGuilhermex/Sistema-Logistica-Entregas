package org.example.Service;

import org.example.DAO.ClienteDAO;
import org.example.Model.Cliente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void cadastrarCliente(Cliente cliente) {
        try {
            clienteDAO.cadastrarCliente(cliente);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente.");
            e.printStackTrace();
        }
    }

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

    public Cliente buscarClientePorCpfCnpj(String cpf_cnpj) {
        try {
            return clienteDAO.buscarClientePorCnpjCpf(cpf_cnpj);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cliente buscarClientePorID(int id) throws SQLException{
        try {
            return clienteDAO.buscarClientePorID(id);
            } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Cliente> listarTodosClientes(){
        try {
            return clienteDAO.listarTodosClientes();
        }catch (SQLException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<String> consultarClientesMaiorVolume(){
        try{
            return clienteDAO.consultarCLienteComMaiorVolume();
        } catch (SQLException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
