package projeto_recomendacao_jogos.telas;

import projeto_recomendacao_jogos.validacoes.VerificacaoLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton entrarButton, cadastrarButton;
    private JLabel logoLabel;
    private VerificacaoLogin verificacaoLogin;

    public TelaLogin() {
        setTitle("Tela de Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);

        verificacaoLogin = new VerificacaoLogin();

        logoLabel = new JLabel();
        logoLabel.setBounds(100, 10, 200, 100);
        add(logoLabel);
        logoLabel.setIcon(carregarImagem("/images/logoEscura.jpg", 200, 100));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 120, 100, 30);
        emailLabel.setForeground(Color.WHITE);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 120, 200, 30);
        add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(50, 170, 100, 30);
        senhaLabel.setForeground(Color.WHITE);
        add(senhaLabel);

        senhaField = new JPasswordField();
        senhaField.setBounds(150, 170, 200, 30);
        add(senhaField);

        entrarButton = new JButton("Entrar");
        entrarButton.setBounds(150, 220, 100, 30);
        entrarButton.setBackground(Color.GREEN);
        entrarButton.setForeground(Color.black);
        add(entrarButton);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(250, 220, 100, 30);
        cadastrarButton.setBackground(new Color(0x00FFFF));
        cadastrarButton.setForeground(Color.black);
        add(cadastrarButton);

        entrarButton.addActionListener(e -> verificarLogin());
    }

    private void verificarLogin() {
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (verificacaoLogin.verificarCredenciais(email, senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            abrirTelaPrincipal(email);
        } else {
            JOptionPane.showMessageDialog(this, "E-mail ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirTelaPrincipal(String emailUsuario) {
        this.dispose();
        new TelaPrincipal(emailUsuario).setVisible(true);
    }

    private ImageIcon carregarImagem(String caminho, int largura, int altura) {
        java.net.URL imgURL = getClass().getResource(caminho);
        if (imgURL == null) {
            System.out.println("⚠ Imagem não encontrada: " + caminho);
            return null;
        }
        ImageIcon icon = new ImageIcon(imgURL);
        Image imagem = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(imagem);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
