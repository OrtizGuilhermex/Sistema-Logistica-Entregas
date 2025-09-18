package org.example.View;

import org.example.Service.RelatorioService;

import java.util.List;
import java.util.Scanner;

public class RelatorioView {

    private final RelatorioService relatorioService;
    private final Scanner sc;

    public RelatorioView(RelatorioService relatorioService, Scanner sc) {
        this.relatorioService = relatorioService;
        this.sc = sc;
    }


    public void exibirTotalEntregasMotoristas() {
        try {
            System.out.println("--- Relatório: Total de Entregas por Motorista ---");

            List<String> entregas = relatorioService.totalEntregasMotorista();

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
