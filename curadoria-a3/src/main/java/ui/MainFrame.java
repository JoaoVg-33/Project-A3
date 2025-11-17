package ui;

import model.User;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cards = new CardLayout();
    private JPanel cardPane = new JPanel(cards);
    private User loggedUser;

    public MainFrame(User u) {
        this.loggedUser = u;
        setTitle("Curadoria A3 - " + u.getUsername());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("Menu");
        JMenuItem miRec = new JMenuItem("Recursos"); miRec.addActionListener(ev -> cards.show(cardPane, "user"));
        m.add(miRec);
        if ("ADMIN".equals(loggedUser.getRole())) {
            JMenuItem miUsers = new JMenuItem("Usuários"); miUsers.addActionListener(ev -> cards.show(cardPane, "admin"));
            m.add(miUsers);
        }
        JMenuItem miSair = new JMenuItem("Sair"); miSair.addActionListener(ev -> { dispose(); new ui.LoginFrame().setVisible(true); });
        m.add(miSair);
        mb.add(m);
        setJMenuBar(mb);

        cardPane.add(new UserPanel(loggedUser), "user");
        if ("ADMIN".equals(loggedUser.getRole())) cardPane.add(new AdminPanel(), "admin");
        add(cardPane);
    }
}
