package org.example;

import org.example.Service.ClienteService;
import org.example.Service.MotoristaService;
import org.example.Util.Conexao;
import org.example.View.ClienteView;
import org.example.View.MotoristaView;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MotoristaService motoristaService = new MotoristaService();
        MotoristaView motoristaView = new MotoristaView(motoristaService, sc);

        int opcao;

        do {
            System.out.println("\n===== Sistema de Logística - Menu Motorista =====");
            System.out.println("1 - Cadastrar Motorista");
            System.out.println("2 - Buscar Motorista por ID");
            System.out.println("3 - Listar Todos os Motorista");
            System.out.println("4 - Relatório: Total de Entregas Por Motorista");
            System.out.println("5 - Atualizar Motorista");
            System.out.println("6 - Excluir Cliente por ID");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1 -> motoristaView.cadastrarMotorista();
                case 2 -> motoristaView.buscarMotoristaPorID();
                case 3 -> motoristaView.ListarTodosMotoristas();
                case 4 -> motoristaView.exibirTotalEntregasMotoristas();
                case 5 -> motoristaView.atualizarMotorista();
                case 6 -> motoristaView.deletarMotorista();
                case 0 -> System.out.println("👋 Saindo do sistema...");
                default -> System.out.println("⚠ Opção inválida, tente novamente.");
            }
        } while (opcao != 0);

        sc.close();
    }
}