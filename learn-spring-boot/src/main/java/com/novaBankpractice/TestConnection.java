package com.novaBankpractice;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {

    public static void main(String[] args) {
        // Sustituye "TU_CONTRASEÑA" por la que usas en pgAdmin/PostgreSQL
        String url = "jdbc:postgresql://localhost:5432/novabank";
        String user = "postgres";
        String password = "655057621";

        System.out.println("Intentando conectar a PostgreSQL...");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("¡ÉXITO! Conexión OK a la base de datos: " + conn.getCatalog());
        } catch (Exception e) {
            System.err.println("¡ERROR! No se pudo conectar a la base de datos.");
            System.err.println("Motivo exacto del fallo:");
            e.printStackTrace();
        }
    }
}