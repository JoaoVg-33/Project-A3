package ui;

import dao.ResourceDAO;
import model.Resource;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserPanel extends JPanel {
    private User user;
    private JTextField tfTitle, tfAuthor;
    private JComboBox<String> cbCategory;
    private DefaultTableModel model;

    public UserPanel(User user) {
        this.user = user;
        setLayout(new BorderLayout(5,5));
        JPanel form = new JPanel(new GridLayout(4,2,5,5));
        form.setBorder(BorderFactory.createTitledBorder("Novo recurso")); 
        form.add(new JLabel("Título:")); tfTitle = new JTextField(); form.add(tfTitle);
        form.add(new JLabel("Autor:")); tfAuthor = new JTextField(); form.add(tfAuthor);
        form.add(new JLabel("Categoria:")); cbCategory = new JComboBox<>(new String[]{"IA_RESPONSAVEL","CIBERSEGURANCA","PRIVACIDADE_ETICA"}); form.add(cbCategory);
        JButton btnSave = new JButton("Salvar"); btnSave.addActionListener(e -> saveResource());
        form.add(new JLabel()); form.add(btnSave);
        add(form, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"ID","Título","Autor","Categoria","Criado"},0) {
            public boolean isCellEditable(int r,int c){return false;}
        };
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnLoad = new JButton("Carregar meus recursos"); btnLoad.addActionListener(e -> loadResources());
        add(btnLoad, BorderLayout.SOUTH);
    }

    private void saveResource() {
        String title = tfTitle.getText().trim();
        if (title.isEmpty()) { JOptionPane.showMessageDialog(this, "Título é obrigatório."); return; }
        try {
            Resource r = new Resource();
            r.setUserId(user.getId());
            r.setTitle(title);
            r.setAuthor(tfAuthor.getText().trim());
            r.setCategory((String) cbCategory.getSelectedItem());
            ResourceDAO dao = new ResourceDAO();
            dao.insert(r);
            JOptionPane.showMessageDialog(this, "Recurso salvo.");
            loadResources();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void loadResources() {
        try {
            ResourceDAO dao = new ResourceDAO();
            List<Resource> list = dao.listByUserOrderedByTitle(user.getId());
            model.setRowCount(0);
            for (Resource r : list) model.addRow(new Object[]{r.getId(), r.getTitle(), r.getAuthor(), r.getCategory(), r.getCreatedAt()});
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
