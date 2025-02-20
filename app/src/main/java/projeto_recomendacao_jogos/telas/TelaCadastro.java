package projeto_recomendacao_jogos.telas;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro extends JFrame {
    private JTextField nomeField, emailField;
    private JPasswordField senhaField;
    private JButton cadastrarButton, voltarButton;

    public TelaCadastro() {
        setTitle("Tela de Cadastro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(30, 30, 30));

        JLabel nomeLabel = new JLabel("Nickname:");
        nomeLabel.setBounds(50, 50, 100, 30);
        nomeLabel.setForeground(Color.WHITE);
        add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(150, 50, 200, 30);
        nomeField.setBackground(new Color(50, 50, 50));
        nomeField.setForeground(Color.WHITE);
        nomeField.setCaretColor(Color.WHITE);
        add(nomeField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 100, 100, 30);
        emailLabel.setForeground(Color.WHITE);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 100, 200, 30);
        emailField.setBackground(new Color(50, 50, 50));
        emailField.setForeground(Color.WHITE);
        emailField.setCaretColor(Color.WHITE);
        add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(50, 150, 100, 30);
        senhaLabel.setForeground(Color.WHITE);
        add(senhaLabel);

        senhaField = new JPasswordField();
        senhaField.setBounds(150, 150, 200, 30);
        senhaField.setBackground(new Color(50, 50, 50));
        senhaField.setForeground(Color.WHITE);
        senhaField.setCaretColor(Color.WHITE);
        add(senhaField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(150, 200, 100, 30);
        cadastrarButton.setBackground(new Color(0x00FFFF));
        cadastrarButton.setForeground(Color.black);
        add(cadastrarButton);

        voltarButton = new JButton("Voltar");
        voltarButton.setBounds(250, 200, 100, 30);
        voltarButton.setBackground(new Color(255, 69, 0));
        voltarButton.setForeground(Color.black);
        add(voltarButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastro().setVisible(true));
    }
}