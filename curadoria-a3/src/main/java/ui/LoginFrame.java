package ui;

import dao.UserDAO;
import model.User;
import util.PasswordUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private JTextField tfUsername;
    private JPasswordField pfPassword;

    public LoginFrame() {
        setTitle("Login - Curadoria A3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350,180);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel p = new JPanel(new GridLayout(3,2,5,5));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        p.add(new JLabel("Username:")); tfUsername = new JTextField(); p.add(tfUsername);
        p.add(new JLabel("Password:")); pfPassword = new JPasswordField(); p.add(pfPassword);
        JButton btn = new JButton("Entrar"); btn.addActionListener(this::login);
        p.add(new JLabel()); p.add(btn);
        add(p);
    }

    private void login(ActionEvent e) {
        String username = tfUsername.getText().trim();
        String password = new String(pfPassword.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha usuário e senha.");
            return;
        }
        try {
            UserDAO dao = new UserDAO();
            User u = dao.findByUsername(username);
            if (u == null || !u.isActive()) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado ou inativo.");
                return;
            }
            if (util.PasswordUtil.verify(password, u.getPasswordHash())) {
                // open main frame
                MainFrame mf = new MainFrame(u);
                mf.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
