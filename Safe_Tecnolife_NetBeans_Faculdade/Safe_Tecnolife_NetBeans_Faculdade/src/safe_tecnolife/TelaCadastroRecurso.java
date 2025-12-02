package safe_tecnolife;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TelaCadastroRecurso extends JFrame {
    private JTextField tfTitulo;
    private JTextField tfAutor;
    private JComboBox<String> cbCat;
    private int userId;

    public TelaCadastroRecurso(int userId) {
        this.userId = userId;
        setTitle("Cadastrar Recurso");
        setSize(420,260);
        setLocationRelativeTo(null);

        Color bg = new Color(248,250,252);
        JPanel p = new JPanel(new GridLayout(4,2,8,8));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        p.setBackground(bg);

        p.add(new JLabel("Título:")); tfTitulo = new JTextField(); p.add(tfTitulo);
        p.add(new JLabel("Autor:")); tfAutor = new JTextField(); p.add(tfAutor);
        p.add(new JLabel("Categoria:")); cbCat = new JComboBox<>(new String[] {"IA RESPONSAVEL","CIBERSEGURANCA","PRIVACIDADE & ETICA DIGITAL"}); p.add(cbCat);
        JButton btnSave = new JButton("Salvar"); p.add(new JLabel("")); p.add(btnSave);

        add(p);

        btnSave.addActionListener(e -> {
            String titulo = tfTitulo.getText().trim();
            String autor = tfAutor.getText().trim();
            String cat = (String) cbCat.getSelectedItem();
            if (titulo.isEmpty()) { JOptionPane.showMessageDialog(this,"Título obrigatório."); return; }
            try (Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASS)) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO recursos (titulo,autor,categoria,usuario_id) VALUES (?,?,?,?)");
                ps.setString(1,titulo);
                ps.setString(2,autor);
                ps.setString(3,cat);
                ps.setInt(4,userId);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this,"Recurso salvo.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Erro: " + ex.getMessage());
            }
        });
    }
}