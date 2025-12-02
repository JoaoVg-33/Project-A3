package safe_tecnolife;

import java.sql.Connection;
import java.sql.DriverManager;

public class Config {
    
    public static final String DB_URL = "jdbc:mysql://localhost:3306/safe_tecnolife";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "Racing-F1";

    public static Connection getConnection() {
        try {
            // 👇 ESTA LINHA é obrigatória
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        } catch (Exception e) {
            System.out.println("❌ Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
}