package org.example.Model;

import java.time.LocalDateTime;

public class HistoricoEntrega {
    private int id;
    private int entreda_id;
    private LocalDateTime data_evento;
    private String descricao;

    public HistoricoEntrega(int id, int entreda_id, LocalDateTime data_evento, String descricao) {
        this.id = id;
        this.entreda_id = entreda_id;
        this.data_evento = data_evento;
        this.descricao = descricao;
    }

    public HistoricoEntrega(int entreda_id, LocalDateTime data_evento, String descricao) {
        this.entreda_id = entreda_id;
        this.data_evento = data_evento;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEntreda_id() {
        return entreda_id;
    }

    public void setEntreda_id(int entreda_id) {
        this.entreda_id = entreda_id;
    }

    public LocalDateTime getData_evento() {
        return data_evento;
    }

    public void setData_evento(LocalDateTime data_evento) {
        this.data_evento = data_evento;
    }
}
