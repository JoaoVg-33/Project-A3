package safe_tecnolife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelaPrincipal extends JFrame {
    private int userId;
    private String nome;
    private String tipo;

    public TelaPrincipal(int userId, String nome, String tipo) {
        this.userId = userId; this.nome = nome; this.tipo = tipo;
        setTitle("Safe Tecnolife - Principal (" + nome + " - " + tipo + ")");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color bg = new Color(245,248,250);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg);

        JLabel lbl = new JLabel("Bem-vindo(a), " + nome, SwingConstants.CENTER);
        lbl.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        root.add(lbl, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(bg);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        // Admin controls
        if ("ADMIN".equalsIgnoreCase(tipo)) {
            JButton btnGestao = new JButton("Gestão de Usuários (Admin)");
            btnGestao.setAlignmentX(Component.CENTER_ALIGNMENT);
            center.add(Box.createRigidArea(new Dimension(0,20)));
            center.add(btnGestao);
            btnGestao.addActionListener(e -> new TelaGestaoUsuarios().setVisible(true));
        }

        // User controls (also available to admin if desired)
        JButton btnCadRes = new JButton("Cadastrar Recurso");
        btnCadRes.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnListRes = new JButton("Listar Meus Recursos");
        btnListRes.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(Box.createRigidArea(new Dimension(0,20)));
        center.add(btnCadRes);
        center.add(Box.createRigidArea(new Dimension(0,10)));
        center.add(btnListRes);

        root.add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setBackground(bg);
        JButton btnLogout = new JButton("Sair");
        bottom.add(btnLogout);
        root.add(bottom, BorderLayout.SOUTH);

        add(root);

        btnLogout.addActionListener(e -> {
            dispose();
            new TelaLogin().setVisible(true);
        });

        btnCadRes.addActionListener(e -> new TelaCadastroRecurso(userId).setVisible(true));
        btnListRes.addActionListener(e -> new TelaListarRecursos(userId).setVisible(true));
    }
}