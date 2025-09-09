package org.example;

import org.example.Util.Conexao;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = Conexao.conectar()) {
            if (conn != null){
                System.out.println("Conexão realizada com sucesso");
            } else {
                System.out.println("Falha na Conexão");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}