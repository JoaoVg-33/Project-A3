package safe_tecnolife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelaCadastroUsuario extends JFrame {
    private JTextField tfNome;
    private JTextField tfIdade;
    private JPasswordField pfSenha;
    private JComboBox<String> cbInt1;
    private JComboBox<String> cbInt2;

    public TelaCadastroUsuario(Integer editingUserId) {
        setTitle(editingUserId==null? "Cadastro de Usuário" : "Editar Usuário");
        setSize(460,360);
        setLocationRelativeTo(null);

        Color bg = new Color(250,250,255);

        JPanel p = new JPanel(new GridLayout(7,2,8,8));
        p.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        p.setBackground(bg);

        p.add(new JLabel("Nome:")); tfNome = new JTextField(); p.add(tfNome);
        p.add(new JLabel("Idade:")); tfIdade = new JTextField(); p.add(tfIdade);
        p.add(new JLabel("Senha:")); pfSenha = new JPasswordField(); p.add(pfSenha);
        p.add(new JLabel("Interesse 1:")); cbInt1 = new JComboBox<>(new String[] {"IA RESPONSAVEL","CIBERSEGURANCA","PRIVACIDADE & ETICA DIGITAL"}); p.add(cbInt1);
        p.add(new JLabel("Interesse 2:")); cbInt2 = new JComboBox<>(new String[] {"IA RESPONSAVEL","CIBERSEGURANCA","PRIVACIDADE & ETICA DIGITAL"}); p.add(cbInt2);

        JButton btnSave = new JButton("Salvar"); JButton btnCancel = new JButton("Cancelar");
        p.add(btnSave); p.add(btnCancel);

        add(p);

        btnSave.addActionListener(e -> saveUser(editingUserId));
        btnCancel.addActionListener(e -> dispose());

        if (editingUserId != null) {
            // load user data (not implemented for brevity)
        }
    }

    private void saveUser(Integer editingUserId) {
        String nome = tfNome.getText().trim();
        String senha = new String(pfSenha.getPassword());
        int idade = 0;
        try { idade = Integer.parseInt(tfIdade.getText().trim()); } catch(Exception ex){}
        String i1 = (String) cbInt1.getSelectedItem();
        String i2 = (String) cbInt2.getSelectedItem();

        if (nome.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Nome e senha obrigatórios.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASS)) {
            if (editingUserId == null) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios (nome,idade,senha,tipo,interest1,interest2,ativo) VALUES (?,?,?,?,?,?,1)");
                ps.setString(1, nome);
                ps.setInt(2, idade);
                ps.setString(3, senha);
                ps.setString(4, "USUARIO");
                ps.setString(5, i1);
                ps.setString(6, i2);
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(this,"Cadastro realizado. Agora faça login.");
                dispose();
            } else {
                // update flow (not required)
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Erro ao salvar: " + ex.getMessage());
        }
    }
}