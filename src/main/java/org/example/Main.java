package org.example;

import org.example.Service.ClienteService;
import org.example.Util.Conexao;
import org.example.View.ClienteView;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClienteService clienteService = new ClienteService();
        ClienteView clienteView = new ClienteView(clienteService, sc);

        int opcao;

        do {
            System.out.println("\n===== Sistema de Logística - Menu Clientes =====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Buscar Cliente por ID");
            System.out.println("3 - Buscar Cliente por CPF/CNPJ");
            System.out.println("4 - Listar Todos os Clientes");
            System.out.println("5 - Relatório: Clientes com Maior Volume Entregue");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1 -> clienteView.cadastrarCliente();
                case 2 -> clienteView.buscarClientePorID();
                case 3 -> clienteView.buscarClientePorCpfCnpj();
                case 4 -> clienteView.listarTodosClientes();
                case 5 -> clienteView.consultarClientesMaiorVolume();
                case 0 -> System.out.println("👋 Saindo do sistema...");
                default -> System.out.println("⚠ Opção inválida, tente novamente.");
            }
        } while (opcao != 0);

        sc.close();
    }
}