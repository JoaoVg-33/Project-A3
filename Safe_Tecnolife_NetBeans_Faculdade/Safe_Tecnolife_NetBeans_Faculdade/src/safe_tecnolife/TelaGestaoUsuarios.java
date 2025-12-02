package safe_tecnolife;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelaGestaoUsuarios extends JFrame {
    private DefaultTableModel model;

    public TelaGestaoUsuarios() {
        setTitle("Gestão de Usuários - Admin");
        setSize(700,450);
        setLocationRelativeTo(null);

        Color bg = new Color(250,250,255);
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg);

        model = new DefaultTableModel(new Object[]{"ID","Nome","Idade","Tipo","Interesse1","Interesse2","Ativo"},0) {
            public boolean isCellEditable(int r,int c){return false;}
        };
        JTable table = new JTable(model);
        loadUsers();

        root.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton btnCreate = new JButton("Criar");
        JButton btnEdit = new JButton("Editar");
        JButton btnToggle = new JButton("Inativar/Ativar");
        buttons.add(btnCreate); buttons.add(btnEdit); buttons.add(btnToggle);

        root.add(buttons, BorderLayout.SOUTH);

        add(root);

        btnCreate.addActionListener(e -> new TelaCadastroUsuario(null).setVisible(true));
        btnToggle.addActionListener(e -> {
            int sel = table.getSelectedRow();
            if (sel==-1){ JOptionPane.showMessageDialog(this,"Selecione um usuário."); return; }
            int uid = (int) model.getValueAt(sel,0);
            toggleActive(uid);
            loadUsers();
        });
        btnEdit.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,"Editar implementado apenas parcialmente.");
        });
    }

    private void loadUsers() {
        model.setRowCount(0);
        try (Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT id,nome,idade,tipo,interest1,interest2,ativo FROM usuarios ORDER BY nome");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("tipo"),
                    rs.getString("interest1"),
                    rs.getString("interest2"),
                    rs.getBoolean("ativo")? "Sim":"Não"
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Erro: " + ex.getMessage());
        }
    }

    private void toggleActive(int uid) {
        try (Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT ativo FROM usuarios WHERE id = ?");
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean ativo = rs.getBoolean("ativo");
                PreparedStatement up = conn.prepareStatement("UPDATE usuarios SET ativo = ? WHERE id = ?");
                up.setBoolean(1, !ativo);
                up.setInt(2, uid);
                up.executeUpdate();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Erro: " + ex.getMessage());
        }
    }
}