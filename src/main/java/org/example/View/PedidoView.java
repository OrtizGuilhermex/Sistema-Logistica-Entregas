package org.example.View;

import org.example.DAO.PedidoDAO;
import org.example.Model.Pedido;
import org.example.Service.PedidoService;

import java.util.List;
import java.util.Scanner;

public class PedidoView {

    private final PedidoService pedidoService;
    private final Scanner sc;

    public PedidoView(PedidoService pedidoService, Scanner sc) {
        this.pedidoService = pedidoService;
        this.sc = sc;
    }

    public void cadastrarPedido() {
        System.out.print("ID do Cliente: ");
        int clienteId = sc.nextInt();
        sc.nextLine();

        System.out.print("Volume (m³): ");
        double volume = sc.nextDouble();

        System.out.print("Peso (kg): ");
        double peso = sc.nextDouble();
        sc.nextLine();

        System.out.print("Status: ");
        String status = sc.nextLine();

        Pedido pedido = new Pedido(0, clienteId, null, volume, peso, status);
        pedidoService.cadastrarPedido(pedido);
        System.out.println("Pedido cadastrado com sucesso!");
    }

    public void atualizarPedido() {
        System.out.print("ID do Pedido: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Novo Status (PENDENTE,ENTREGUE,CANCELADO): ");
        String status = sc.nextLine();

        Pedido pedido = new Pedido(id, 0, null, 0, 0, status);
        pedidoService.atualizarPedido(pedido);
        System.out.println("Pedido Atualizado com sucesso!");
    }

    public void cancelarPedido() {
        System.out.print("ID do Pedido: ");
        int id = sc.nextInt();
        sc.nextLine();

        Pedido pedido = new Pedido(id, 0, null, 0, 0, "CANCELADO");
        pedidoService.cancelarPedido(pedido);
        System.out.println("Pedido Cancelado com sucesso!");
    }

    public void buscarPedidoPorID() {
        System.out.print("ID do Pedido: ");
        int id = sc.nextInt();
        sc.nextLine();

        List<Pedido> pedidos = pedidoService.buscarPedidoPorId(id);

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado!");
        } else {
            System.out.println("--- Pedido Encontrado ---");
            for (Pedido pedido : pedidos) {
                System.out.println("ID: " + pedido.getId());
                System.out.println("ID do Cliente: " + pedido.getCliente_id());
                System.out.println("Data do Pedido: " + pedido.getData_pedido());
                System.out.println("Volume (m³): " + pedido.getVolume());
                System.out.println("Peso (kg): " + pedido.getPeso());
                System.out.println("Status: " + pedido.getStatus());
                System.out.println("-----------------------------");
            }
        }
    }

    public void buscarPedidoPorCpfCnpj() {
        System.out.print("Digite o CPF/CNPJ: ");
        String cpfCnpj = sc.nextLine();

        List<Pedido> pedidos = pedidoService.buscarPedidoCpfCnpj(cpfCnpj);
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado!");
        } else {
            System.out.println("--- Pedido Encontrado ---");
            for (Pedido pedido : pedidos) {
                System.out.println("ID: " + pedido.getId());
                System.out.println("ID do Cliente: " + pedido.getCliente_id());
                System.out.println("Data do Pedido: " + pedido.getData_pedido());
                System.out.println("Volume (m³): " + pedido.getVolume());
                System.out.println("Peso (kg): " + pedido.getPeso());
                System.out.println("Status: " + pedido.getStatus());
                System.out.println("-----------------------------");
            }
        }
    }

    public void listarPendentesPorEstado() {
        System.out.print("Digite o Estado: ");
        String estado = sc.nextLine();

        List<Pedido> pedidos = pedidoService.ListarPedidosPendenteEstado(estado);

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado!");
        } else {
            System.out.println("--- Pedidos Pendentes no Estado: " + estado + " ---");
            for (Pedido pedido : pedidos) {
                System.out.println("ID do Pedido: " + pedido.getId());
                System.out.println("ID do Cliente: " + pedido.getCliente_id());
                System.out.println("Data do Pedido: " + pedido.getData_pedido());
                System.out.println("Volume (m³): " + pedido.getVolume());
                System.out.println("Peso (kg): " + pedido.getPeso());
                System.out.println("Status: " + pedido.getStatus());
                System.out.println("-----------------------------");
            }
        }
    }
}
