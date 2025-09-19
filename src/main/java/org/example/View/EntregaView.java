package org.example.View;

import org.example.DTO.EntregaDetalhadaDTO;
import org.example.Model.Entrega;
import org.example.Model.Motorista;
import org.example.Model.Pedido;
import org.example.Service.EntregaService;
import org.example.Util.DateUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EntregaView {

    public final EntregaService entregaService;
    private final Scanner sc;


    public EntregaView(EntregaService entregaService, Scanner sc) {
        this.entregaService = entregaService;
        this.sc = sc;
    }

    public void InserirEntrega() {
        System.out.print("ID do pedido ");
        int pedidoID = sc.nextInt();
        sc.nextLine();

        System.out.print("ID do motorista: ");
        int motoristaID = sc.nextInt();
        sc.nextLine();

        LocalDate data_saida = LocalDate.now();
        LocalDate data_entrega = null;

        try {
            System.out.print("Data de entrega (dd/MM/yyyy): ");
            String entrada = sc.nextLine();
            data_entrega = DateUtils.toLocalDate(entrada);
        } catch (Exception e) {
            System.out.println("Formato de data inválido! Use dd/MM/yyyy");
            return;
        }

        System.out.print("Status: ");
        String status = sc.nextLine();

        Entrega entrega = new Entrega(0, pedidoID,motoristaID, data_saida, data_entrega, status);
        entregaService.cadastrarEntrega(entrega);
        System.out.println("Entrega cadastrada com sucesso!");
    }

    public void atualizarStatusEntrega() {
        System.out.print("ID da entrega: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Novo status: ");
        String status = sc.nextLine();

        entregaService.atualizarStatusEntrega(id, status);
    }

    public void excluirEntrega() {
        System.out.print("ID da entrega a excluir: ");
        int id = sc.nextInt();
        sc.nextLine();

        entregaService.excluirEntrega(id);
    }


    public void buscarEntregaPorID(){
        try{
            System.out.println("Digite o ID da Entrega");
            int id = sc.nextInt();
            sc.nextLine();

          Entrega entrega =  entregaService.buscarEntregaPorID(id);

            if(entrega != null){
                System.out.println("--- Cliente Encontrado ---");
                System.out.println("ID da entrega: " + entrega.getId());
                System.out.println("ID do pedido: " + entrega.getPedido_id());
                System.out.println("ID do motorista: " + entrega.getMotorista_id());
                System.out.println("Data de saída: " + entrega.getData_saida());
                System.out.println("Data de Entrega: " + entrega.getData_entrega());
                System.out.println("Status: " + entrega.getStatus());
                System.out.println("---------------------------------");
            }else{
                System.out.println("Nenhuma entrega encontrada com o ID informado");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ListarTodasEntregas(){
        try{
            List<EntregaDetalhadaDTO> entregas = entregaService.listarTodasEntregas();

            if(entregas.isEmpty()){
                System.out.println("Nenhuma Entrega Cadastrado");
            } else {
                System.out.println("---- Lista de Motoristas ----");
                for (EntregaDetalhadaDTO entrega : entregas){
                    System.out.println("ID da entrega: " + entrega.getEntregaId());
                    System.out.println("ID do pedido: " + entrega.getPedidoId());
                    System.out.println("Cliente: " + entrega.getClienteNome());
                    System.out.println("CPF/CNPJ(Cliente): " + entrega.getClienteCpfCnpj());
                    System.out.println("Motorista: " + entrega.getMotoristaNome());
                    System.out.println("CNH(Motorista): " + entrega.getMotoristaCnh());
                    System.out.println("Status: " + entrega.getStatus());
                    System.out.println("---------------------------------");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listarEntregasPorCidade() {
        System.out.print("Digite a cidade: ");
        String cidade = sc.nextLine();

        List<Entrega> entregas = entregaService.listarEntregasPorCidade(cidade);

        if (entregas.isEmpty()) {
            System.out.println("Nenhum pedido encontrado!");
        } else {
            System.out.println("--- Pedidos Pendentes na Cidade: " + cidade + " ---");
            for (Entrega entrega : entregas) {
                System.out.println("ID da entrega: " + entrega.getId());
                System.out.println("ID do pedido: " + entrega.getPedido_id());
                System.out.println("ID do motorista: " + entrega.getMotorista_id());
                System.out.println("Data de saída: " + entrega.getData_saida());
                System.out.println("Data de Entrega: " + entrega.getData_entrega());
                System.out.println("Status: " + entrega.getStatus());
                System.out.println("---------------------------------");
            }
        }
    }

}
