package org.example;

import org.example.Service.ClienteService;
import org.example.Service.EntregaService;
import org.example.Service.MotoristaService;
import org.example.Service.PedidoService;
import org.example.Util.Conexao;
import org.example.View.ClienteView;
import org.example.View.EntregaView;
import org.example.View.MotoristaView;
import org.example.View.PedidoView;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntregaService entregaService = new EntregaService();
        EntregaView entregaView = new EntregaView(entregaService,sc);

        int opcao;

        do {
            System.out.println("\n==== MENU PEDIDOS ====");
            System.out.println("1 - Inserir Entrega");
            System.out.println("2 - Atualizar Entrega (status)");
            System.out.println("3 - Excluir Entrega");
            System.out.println("4 - Buscar Entrega por ID");
            System.out.println("5 - Listar Todas entregas");
            System.out.println("6 - Listar Entregas Pendentes por cidade");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> entregaView.InserirEntrega();
                case 2 -> entregaView.atualizarStatusEntrega();
                case 3 -> entregaView.excluirEntrega();
                case 4 -> entregaView.buscarEntregaPorID();
                case 5 -> entregaView.ListarTodasEntregas();
                case 6 -> entregaView.listarEntregasPorCidade();
                case 0 -> System.out.println("👋 Saindo do sistema...");
                default -> System.out.println("⚠ Opção inválida, tente novamente.");
            }
        } while (opcao != 0);

        sc.close();
    }
}