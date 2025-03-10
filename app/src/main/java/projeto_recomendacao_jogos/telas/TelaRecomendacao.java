package projeto_recomendacao_jogos.telas;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import projeto_recomendacao_jogos.interfaces.IConteudo;
import projeto_recomendacao_jogos.recomendacao.Recomendador;

public class TelaRecomendacao extends JDialog {
    private final JTextField campoGeneros;  
    private final JTextField campoQuantidade;
    private final Recomendador recomendar;
    private final String email;

    public TelaRecomendacao(JFrame parent, String email) {
        super(parent, "Nova Recomendação", true);
        this.email = email;
        recomendar = new Recomendador(email);

        setSize(400, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2));

        campoGeneros = new JTextField();
        campoQuantidade = new JTextField();

        JLabel labelGeneros = new JLabel("Gêneros (separados por vírgula):");
        JLabel labelQuantidade = new JLabel("Quantidade de Jogos:");

        JButton botaoConfirmar = new JButton("Confirmar");

        add(labelGeneros);
        add(campoGeneros);
        add(labelQuantidade);
        add(campoQuantidade);
        add(botaoConfirmar);

        botaoConfirmar.addActionListener(e -> {
            String generosText = campoGeneros.getText().trim();
            String quantidadeText = campoQuantidade.getText().trim();
        
            if (!generosText.isEmpty() && !quantidadeText.isEmpty()) {
                try {
                    int quantidade = Integer.parseInt(quantidadeText);
        
                    List<String> generos = Arrays.asList(generosText.split("\\s*,\\s*"));
                    List<IConteudo> jogosRecomendados = recomendarJogos(generos, quantidade);
                    mostrarJogosRecomendados(jogosRecomendados);
                    dispose(); 
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, insira um número válido para a quantidade.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                ArrayList<IConteudo> jogosRecomendados = new ArrayList<>();
                jogosRecomendados.add(recomendar.getRecomendacao());
                mostrarJogosRecomendados(jogosRecomendados);
                dispose(); 
            }
        });
    }

    private List<IConteudo> recomendarJogos(List<String> generos, int quantidade) {
        return recomendar.getRecomendacaoFiltrada(generos, quantidade);
    }

    private void mostrarJogosRecomendados(List<IConteudo> jogosRecomendados) {
        JFrame telaJogosRecomendados = new JFrame();
        telaJogosRecomendados.setSize(400, 300);
        telaJogosRecomendados.setLocationRelativeTo(this);
        telaJogosRecomendados.setLayout(new BorderLayout());
        
        DefaultListModel<IConteudo> modeloJogos = new DefaultListModel<>();
        for (IConteudo jogo : jogosRecomendados) {
            modeloJogos.addElement(jogo);
        }

        JList<IConteudo> listaJogos = new JList<>(modeloJogos);
        JScrollPane scrollPane = new JScrollPane(listaJogos);

        telaJogosRecomendados.add(scrollPane, BorderLayout.CENTER);

        telaJogosRecomendados.setVisible(true);
        this.dispose();
    }
}