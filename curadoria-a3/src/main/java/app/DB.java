package app;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class DB {
    private static Connection conn;
    public static Connection getConnection() throws SQLException {
        try {
            if (conn == null || conn.isClosed()) {
                Properties props = new Properties();
                try (InputStream in = DB.class.getResourceAsStream("/config.properties")) {
                    props.load(in);
                }
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String pass = props.getProperty("db.password");
                conn = DriverManager.getConnection(url, user, pass);
            }
            return conn;
        } catch (Exception e) {
            throw new SQLException("Erro ao conectar: " + e.getMessage(), e);
        }
    }
}
