package org.example;

import org.example.Service.ClienteService;
import org.example.Service.MotoristaService;
import org.example.Service.PedidoService;
import org.example.Util.Conexao;
import org.example.View.ClienteView;
import org.example.View.MotoristaView;
import org.example.View.PedidoView;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PedidoService pedidoService = new PedidoService();
        PedidoView pedidoView = new PedidoView(pedidoService,sc);

        int opcao;

        do {
            System.out.println("\n==== MENU PEDIDOS ====");
            System.out.println("1 - Cadastrar Pedido");
            System.out.println("2 - Atualizar Pedido (status)");
            System.out.println("3 - Cancelar Pedido");
            System.out.println("4 - Buscar Pedido por ID");
            System.out.println("5 - Buscar Pedido por CPF/CNPJ do Cliente");
            System.out.println("6 - Listar Pedidos Pendentes por Estado");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> pedidoView.cadastrarPedido();
                case 2 -> pedidoView.atualizarPedido();
                case 3 -> pedidoView.cancelarPedido();
                case 4 -> pedidoView.buscarPedidoPorID();
                case 5 -> pedidoView.buscarPedidoPorCpfCnpj();
                case 6 -> pedidoView.listarPendentesPorEstado();
                case 0 -> System.out.println("👋 Saindo do sistema...");
                default -> System.out.println("⚠ Opção inválida, tente novamente.");
            }
        } while (opcao != 0);

        sc.close();
    }
}