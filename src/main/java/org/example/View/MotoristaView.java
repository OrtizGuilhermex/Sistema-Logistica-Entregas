package org.example.View;

import org.example.Model.Motorista;
import org.example.Service.MotoristaService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MotoristaView {

    private final MotoristaService motoristaService;
    private final Scanner sc;

    public MotoristaView(MotoristaService motoristaService, Scanner sc) {
        this.motoristaService = motoristaService;
        this.sc = sc;
    }

    public void cadastrarMotorista(){
        try{
            System.out.println("----Cadastro de Motorista----");

            System.out.println("Nome: ");
            String nome = sc.nextLine();

            System.out.println("CNH: ");
            String cnh = sc.nextLine();

            System.out.println("Veiculo: ");
            String veiculo = sc.nextLine();

            System.out.println("Cidade Base: ");
            String cidadeBase = sc.nextLine();

            Motorista motorista = new Motorista(0, nome, cnh, veiculo, cidadeBase);
            motoristaService.cadastrarMotorista(motorista);
            System.out.println("Motorista Cadastrado com sucesso!");
        } catch (Exception e){
            System.out.println("Ocorreu um erro ao cadastrar Motorista");
            e.printStackTrace();
        }
    }

    public void atualizarMotorista() {
        try {
            System.out.println("--- Atualizar Motorista ---");

            System.out.print("Digite o ID do motorista: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Novo nome: ");
            String nome = sc.nextLine();

            System.out.print("Nova CNH: ");
            String cnh = sc.nextLine();

            System.out.print("Novo veículo: ");
            String veiculo = sc.nextLine();

            System.out.print("Nova cidade base: ");
            String cidadeBase = sc.nextLine();

            Motorista motorista = new Motorista(id, nome, cnh, veiculo, cidadeBase);

            motoristaService.atualizarMotorista(motorista);

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao atualizar o motorista.");
            e.printStackTrace();
        }
    }

    public void deletarMotorista(){
        try {
            System.out.println("--- Excluir Motorista ---");

            System.out.print("Digite o ID do motorista a ser excluído: ");
            int id = sc.nextInt();
            sc.nextLine();

            String resultado = motoristaService.excluirMotorista(id);
            System.out.println(resultado);

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar excluir o motorista.");
            e.printStackTrace();
        }
    }

    public void buscarMotoristaPorID(){
        try{
            System.out.println("Digite o ID do Motorista");
            int id = sc.nextInt();
            sc.nextLine();

            Motorista motorista =  motoristaService.buscarMotoristaPorID(id);

            if(motorista != null){
                System.out.println("--- Cliente Encontrado ---");
                System.out.println("ID: " + motorista.getId());
                System.out.println("Nome: " + motorista.getNome());
                System.out.println("CNH: " + motorista.getCnh());
                System.out.println("Veiculo: " + motorista.getVeiculo());
                System.out.println("Cidade Base: " + motorista.getCidade_base());
            }else{
                System.out.println("Nenhum motorista encontrado com o ID informado");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ListarTodosMotoristas(){
        try{
            List<Motorista> motoristas = motoristaService.listarTodosMotoristas();

            if(motoristas.isEmpty()){
                System.out.println("Nenhum Motorista Cadastrado");
            } else {
                System.out.println("---- Lista de Motoristas ----");
                for (Motorista motorista : motoristas){
                    System.out.println("ID: " + motorista.getId());
                    System.out.println("Nome: " + motorista.getNome());
                    System.out.println("CNH: " + motorista.getCnh());
                    System.out.println("Veiculo: " + motorista.getVeiculo());
                    System.out.println("Cidade Base: " + motorista.getCidade_base());
                    System.out.println("-----------------------------");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void exibirTotalEntregasMotoristas() {
        try {
            System.out.println("--- Relatório: Total de Entregas por Motorista ---");

            List<String> entregas = motoristaService.totalEntregasMotorista();

            if (entregas.isEmpty()) {
                System.out.println("Nenhuma entrega registrada.");
            } else {
                for (String linha : entregas) {
                    System.out.println(linha);
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao buscar o relatório.");
            e.printStackTrace();
        }
    }




}
