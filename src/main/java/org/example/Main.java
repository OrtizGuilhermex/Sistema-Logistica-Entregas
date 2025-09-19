package org.example;

import org.example.Service.*;
import org.example.Util.Conexao;
import org.example.View.*;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ClienteService clienteService = new ClienteService();
        ClienteView clienteView = new ClienteView(clienteService,sc);
        MotoristaService motoristaService = new MotoristaService();
        MotoristaView motoristaView = new MotoristaView(motoristaService,sc);
        PedidoService pedidoService = new PedidoService();
        PedidoView pedidoView = new PedidoView(pedidoService,sc);
        RelatorioService relatorioService = new RelatorioService();
        RelatorioView relatorioView = new RelatorioView(relatorioService,sc);
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
                case 1 -> clienteView.cadastrarCliente();
                case 2 -> motoristaView.cadastrarMotorista();
                case 3 -> pedidoView.cadastrarPedido();
                case 4 -> entregaView.InserirEntrega();
                case 5 -> entregaView.ListarTodasEntregas();
                case 6 -> entregaView.atualizarStatusEntrega();
                case 7 -> entregaView.ListarTodasEntregas();
                case 8 -> relatorioView.exibirTotalEntregasMotoristas();
                case 9 -> clienteView.consultarClientesMaiorVolume();
                case 10 -> pedidoView.listarPendentesPorEstado();
                case 11 -> entregaView.listarEntregasPorCidade();
                case 12 -> pedidoView.buscarPedidoPorCpfCnpj();
                case 13 -> pedidoView.cancelarPedido();
                case 14 -> entregaView.excluirEntrega();
                case 15 -> clienteView.deletarCliente();
                case 16 -> motoristaView.deletarMotorista();
                case 0 -> System.out.println("👋 Saindo do sistema...");
                default -> System.out.println("⚠ Opção inválida, tente novamente.");
            }
        } while (opcao != 0);

        sc.close();
    }
}