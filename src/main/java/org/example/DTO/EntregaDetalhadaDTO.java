package org.example.DTO;

import java.time.LocalDate;


public class EntregaDetalhadaDTO {
    private int entregaId;
    private int pedidoId;
    private LocalDate data_saida;
    private String status;
    private String clienteNome;
    private String clienteCpfCnpj;
    private String motoristaNome;
    private String motoristaCnh;

    public EntregaDetalhadaDTO(int entregaId, int pedidoId, LocalDate dataSaida,
                            String status, String clienteNome, String clienteCpfCnpj,
                            String motoristaNome, String motoristaCnh) {
        this.entregaId = entregaId;
        this.pedidoId = pedidoId;
        this.data_saida = dataSaida;
        this.status = status;
        this.clienteNome = clienteNome;
        this.clienteCpfCnpj = clienteCpfCnpj;
        this.motoristaNome = motoristaNome;
        this.motoristaCnh = motoristaCnh;
    }

    @Override
    public String toString() {
        return "Entrega #" + entregaId +
                " | Pedido: " + pedidoId +
                " | Cliente: " + clienteNome + " (" + clienteCpfCnpj + ")" +
                " | Motorista: " + motoristaNome + " [CNH: " + motoristaCnh + "]" +
                " | Saída: " + data_saida +
                " | Status: " + status;
    }
}
