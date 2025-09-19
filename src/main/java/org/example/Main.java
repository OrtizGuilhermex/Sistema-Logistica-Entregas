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
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Motorista");
            System.out.println("3 - Criar Pedido");
            System.out.println("4 - Atribuir Pedido a Motorista (Gerar Entrega)");
            System.out.println("5 - Registrar Evento de Entrega (Histórico)");
            System.out.println("6 - Atualizar Status da Entrega");
            System.out.println("7 - Listar Todas as Entregas com Cliente e Motorista");
            System.out.println("8 - Relatório: Total de Entregas por Motorista");
            System.out.println("9 - Relatório: Clientes com Maior Volume Entregue");
            System.out.println("10 - Relatório: Pedidos Pendentes por Estado");
            System.out.println("11 - Relatório: Entregas Atrasadas por Cidade");
            System.out.println("12 - Buscar Pedido por CPF/CNPJ do Cliente");
            System.out.println("13 - Cancelar Pedido");
            System.out.println("14 - Excluir Entrega (com validação)");
            System.out.println("15 - Excluir Cliente (com verificação de dependência)");
            System.out.println("16 - Excluir Motorista (com verificação de dependência)");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> ;
                case 2 -> entregaView.atualizarStatusEntrega();
                case 3 -> entregaView.excluirEntrega();
                case 4 -> entregaView.buscarEntregaPorID();
                case 5 -> entregaView.ListarTodasEntregas();
                case 6 -> entregaView.listarEntregasPorCidade();
                case 7 -> entregaView.listarEntregasPorCidade();
                case 8 -> entregaView.listarEntregasPorCidade();
                case 9 -> entregaView.listarEntregasPorCidade();
                case 10 -> entregaView.listarEntregasPorCidade();
                case 11 -> entregaView.listarEntregasPorCidade();
                case 12 -> entregaView.listarEntregasPorCidade();
                case 13 -> entregaView.listarEntregasPorCidade();
                case 14 -> entregaView.listarEntregasPorCidade();
                case 15 -> entregaView.listarEntregasPorCidade();
                case 16 -> entregaView.listarEntregasPorCidade();
                case 0 -> System.out.println("👋 Saindo do sistema...");
                default -> System.out.println("⚠ Opção inválida, tente novamente.");
            }
        } while (opcao != 0);

        sc.close();
    }
}