package org.example.DTO;

import java.time.LocalDate;


public class EntregaDetalhadaDTO {
    private int entregaId;
    private int pedidoId;
    private String data_saida;
    private String status;
    private String clienteNome;
    private String clienteCpfCnpj;
    private String motoristaNome;
    private String motoristaCnh;

    public EntregaDetalhadaDTO(int entregaId, int pedidoId, String dataSaida,
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

    public int getEntregaId() {
        return entregaId;
    }

    public void setEntregaId(int entregaId) {
        this.entregaId = entregaId;
    }

    public String getMotoristaCnh() {
        return motoristaCnh;
    }

    public void setMotoristaCnh(String motoristaCnh) {
        this.motoristaCnh = motoristaCnh;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getMotoristaNome() {
        return motoristaNome;
    }

    public void setMotoristaNome(String motoristaNome) {
        this.motoristaNome = motoristaNome;
    }

    public String getClienteCpfCnpj() {
        return clienteCpfCnpj;
    }

    public void setClienteCpfCnpj(String clienteCpfCnpj) {
        this.clienteCpfCnpj = clienteCpfCnpj;
    }

    public String getData_saida() {
        return data_saida;
    }

    public void setData_saida(String data_saida) {
        this.data_saida = data_saida;
    }

}
