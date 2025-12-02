package safe_tecnolife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelaLogin extends JFrame {
    private JTextField tfId;
    private JTextField tfNome;
    private JPasswordField pfSenha;

    public TelaLogin() {
        setTitle("Safe Tecnolife - Login");
        setSize(420,260);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Light color scheme
        Color bg = new Color(245, 247, 250);
        Color panel = new Color(230, 240, 250);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg);
        JPanel center = new JPanel(new GridLayout(4,2,8,8));
        center.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        center.setBackground(bg);

        center.add(new JLabel("ID:"));
        tfId = new JTextField();
        center.add(tfId);

        center.add(new JLabel("Nome:"));
        tfNome = new JTextField();
        center.add(tfNome);

        center.add(new JLabel("Senha:"));
        pfSenha = new JPasswordField();
        center.add(pfSenha);

        root.add(center, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setBackground(bg);
        JButton btnLogin = new JButton("Entrar");
        JButton btnRegister = new JButton("Cadastrar (Usuário)");
        JButton btnExit = new JButton("Sair");
        buttons.add(btnLogin);
        buttons.add(btnRegister);
        buttons.add(btnExit);
        root.add(buttons, BorderLayout.SOUTH);

        add(root);

        btnLogin.addActionListener(e -> doLogin());
        btnRegister.addActionListener(e -> {
            new TelaCadastroUsuario(null).setVisible(true);
        });
        btnExit.addActionListener(e -> System.exit(0));
    }

    private void doLogin() {
        String idText = tfId.getText().trim();
        String nome = tfNome.getText().trim();
        String senha = new String(pfSenha.getPassword());
        if (idText.isEmpty() || nome.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha ID, nome e senha.");
            return;
        }
        int id = -1;
        try { id = Integer.parseInt(idText); } catch(Exception ex){ JOptionPane.showMessageDialog(this,"ID deve ser numérico."); return; }

        try (Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT id,nome,tipo,ativo FROM usuarios WHERE id = ? AND nome = ? AND senha = ?");
            ps.setInt(1, id);
            ps.setString(2, nome);
            ps.setString(3, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean ativo = rs.getBoolean("ativo");
                if (!ativo) { JOptionPane.showMessageDialog(this,"Conta inativa. Contate o administrador."); return; }
                String tipo = rs.getString("tipo");
                int uid = rs.getInt("id");
                dispose();
                TelaPrincipal tp = new TelaPrincipal(uid, nome, tipo);
                tp.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,"Credenciais inválidas.");
            }
            rs.close(); ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Erro ao conectar: " + ex.getMessage());
        }
    }
}