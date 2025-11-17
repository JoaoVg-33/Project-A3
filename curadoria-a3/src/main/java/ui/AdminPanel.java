package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public AdminPanel() {
        setLayout(new BorderLayout(5,5));
        model = new DefaultTableModel(new Object[]{"ID","Nome","Idade","Username","Role","Ativo"},0) {
            public boolean isCellEditable(int r,int c){return false;}
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
        JButton btnLoad = new JButton("Carregar usuários"); btnLoad.addActionListener(e -> loadUsers());
        JButton btnInativar = new JButton("Inativar selecionado"); btnInativar.addActionListener(e -> inactivateSelected());
        JPanel p = new JPanel(); p.add(btnLoad); p.add(btnInativar);
        add(p, BorderLayout.SOUTH);
    }

    private void loadUsers() {
        try {
            UserDAO dao = new UserDAO();
            List<User> list = dao.listAll();
            model.setRowCount(0);
            for (User u : list) model.addRow(new Object[]{u.getId(), u.getName(), u.getAge(), u.getUsername(), u.getRole(), u.isActive()});
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void inactivateSelected() {
        int row = table.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione um usuário."); return; }
        int id = (int) model.getValueAt(row,0);
        try {
            UserDAO dao = new UserDAO();
            dao.setActive(id, false);
            loadUsers();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
