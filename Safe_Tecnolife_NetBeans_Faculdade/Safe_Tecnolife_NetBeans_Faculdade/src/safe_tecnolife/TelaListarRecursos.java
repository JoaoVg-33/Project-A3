package safe_tecnolife;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TelaListarRecursos extends JFrame {
    private int userId;
    private DefaultTableModel model;

    public TelaListarRecursos(int userId) {
        this.userId = userId;
        setTitle("Meus Recursos");
        setSize(600,400);
        setLocationRelativeTo(null);

        Color bg = new Color(250,250,250);
        model = new DefaultTableModel(new Object[]{"ID","Título","Autor","Categoria"},0) {
            public boolean isCellEditable(int r,int c){return false;}
        };
        JTable table = new JTable(model);
        add(new JScrollPane(table));
        loadResources();
    }

    private void loadResources() {
        model.setRowCount(0);
        try (Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT id,titulo,autor,categoria FROM recursos WHERE usuario_id = ? ORDER BY titulo ASC");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{ rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getString("categoria") });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Erro: " + ex.getMessage());
        }
    }
}