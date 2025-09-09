package org.example.Model;

import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private int cliente_id;
    private LocalDateTime data_pedido;
    private double volume;
    private double peso;
    private String status;

    public Pedido(int id, int cliente_id, LocalDateTime data_pedido, double volume, double peso, String status) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.data_pedido = data_pedido;
        this.volume = volume;
        this.peso = peso;
        this.status = status;
    }

    public Pedido(int cliente_id, LocalDateTime data_pedido, double volume, double peso, String status) {

        this.cliente_id = cliente_id;
        this.data_pedido = data_pedido;
        this.volume = volume;
        this.peso = peso;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public LocalDateTime getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(LocalDateTime data_pedido) {
        this.data_pedido = data_pedido;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
