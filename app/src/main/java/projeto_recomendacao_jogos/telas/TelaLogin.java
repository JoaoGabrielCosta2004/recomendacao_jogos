package projeto_recomendacao_jogos.telas;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import projeto_recomendacao_jogos.validacoes.VerificacaoLogin;

public class TelaLogin extends JFrame {
    private final JTextField emailField;
    private final JPasswordField senhaField;
    private final JButton entrarButton;
    private final JButton cadastrarButton;
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
        cadastrarButton.addActionListener(e -> abrirTelaCadastro());
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
            this.dispose();
            abrirProximaTela(email);
        } else {
            JOptionPane.showMessageDialog(this, "E-mail ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void abrirProximaTela(String email) {
        JOptionPane.showMessageDialog(this, "Bem-vindo ao sistema!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        new TelaPrincipal(email).setVisible(true);
    }

    private void abrirTelaCadastro(){
        this.dispose();
        new TelaCadastro().setVisible(true);
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
