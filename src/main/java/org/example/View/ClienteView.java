package org.example.View;

import org.example.Model.Cliente;
import org.example.Service.ClienteService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClienteView {

    private final ClienteService clienteService;
    private final Scanner sc;

    public ClienteView(ClienteService clienteService, Scanner sc) {
        this.clienteService = clienteService;
        this.sc = sc;
    }

    public void cadastrarCliente(){
        try{
            System.out.println("---Cadastro de Cliente----");

            System.out.println("Nome: ");
            String nome = sc.nextLine();

            System.out.println("CPF/CNPJ: ");
            String cpf_cnpj = sc.nextLine();

            System.out.print("Endereço: ");
            String endereco = sc.nextLine();

            System.out.print("Cidade: ");
            String cidade = sc.nextLine();

            System.out.print("Estado: ");
            String estado = sc.nextLine();

            Cliente cliente = new Cliente(0, nome, cpf_cnpj, endereco, cidade, estado);
            clienteService.cadastrarCliente(cliente);
            System.out.println("Cliente Cadastrado com sucesso!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void buscarClientePorID() {
        try {
            System.out.println("Digite o ID do Cliente: ");
            int idCliente = sc.nextInt();
            sc.nextLine();

            Cliente cliente = clienteService.buscarClientePorID(idCliente);

            if (cliente != null){
                System.out.println("--- Cliente Encontrado ---");
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("CPF/CNPJ: " + cliente.getCpf_cnpj());
                System.out.println("Endereço: " + cliente.getEndereco());
                System.out.println("Cidade: " + cliente.getCidade());
                System.out.println("Estado: " + cliente.getEstado());
            } else {
                System.out.println("Nenhum cliente encontrado com o ID informado.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void buscarClientePorCpfCnpj(){
        try {
            System.out.println("Digite o CPF/CNPJ do cliente: ");
            String cpf_cnpj = sc.nextLine();

            Cliente cliente = clienteService.buscarClientePorCpfCnpj(cpf_cnpj);

            if (cliente != null){
                System.out.println("--- Cliente Encontrado ---");
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("CPF/CNPJ: " + cliente.getCpf_cnpj());
                System.out.println("Endereço: " + cliente.getEndereco());
                System.out.println("Cidade: " + cliente.getCidade());
                System.out.println("Estado: " + cliente.getEstado());
            } else {
                System.out.println("Nenhum cliente encontrado com o CPF/CNPJ informado.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listarTodosClientes(){
        try {
            List<Cliente> clientes = clienteService.listarTodosClientes();

            if(clientes.isEmpty()){
                System.out.println("Nenhum Cliente cadastrado!");
            } else {
                System.out.println("--- Lista de Clientes ---");
                for (Cliente cliente : clientes) {
                    System.out.println("ID: " + cliente.getId());
                    System.out.println("Nome: " + cliente.getNome());
                    System.out.println("CPF/CNPJ: " + cliente.getCpf_cnpj());
                    System.out.println("Endereço: " + cliente.getEndereco());
                    System.out.println("Cidade: " + cliente.getCidade());
                    System.out.println("Estado: " + cliente.getEstado());
                    System.out.println("-----------------------------");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void consultarClientesMaiorVolume() {
        try {
            List<String> relatorio = clienteService.consultarClientesMaiorVolume();

            if (relatorio.isEmpty()) {
                System.out.println("Nenhum cliente com entregas registradas.");
            } else {
                System.out.println("--- Clientes com Maior Volume Entregue ---");
                for (String linha : relatorio) {
                    System.out.println(linha);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório de clientes.");
            e.printStackTrace();
        }
    }
}
