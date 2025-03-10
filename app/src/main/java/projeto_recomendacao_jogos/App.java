package projeto_recomendacao_jogos;

import javax.swing.SwingUtilities;

import projeto_recomendacao_jogos.telas.TelaLogin;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
