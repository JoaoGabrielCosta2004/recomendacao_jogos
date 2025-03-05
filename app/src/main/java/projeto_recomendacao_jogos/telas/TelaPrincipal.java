package projeto_recomendacao_jogos.telas;

import projeto_recomendacao_jogos.dados.AcessoDados;
import projeto_recomendacao_jogos.dados.ManipularJogos;
import projeto_recomendacao_jogos.dados.ManipularListaDesejos;
import projeto_recomendacao_jogos.dados.ManipularMeusJogos;
import projeto_recomendacao_jogos.objetos.Jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TelaPrincipal extends JFrame {
    private final JTextField pesquisaField;
    private final JList<String> listaPesquisa;
    private final DefaultListModel<String> modeloPesquisa;
    private final JScrollPane scrollPesquisa;
    private final JButton novaRecomendacaoButton;
    private final JButton logoutButton;
    private final JButton alternarListaButton;
    private final JList<String> listaJogosUsuario;
    private final DefaultListModel<String> modeloLista;
    private final JList<String> listaDesejos;
    private final DefaultListModel<String> modeloListaDesejos;
    private final JPanel painelCentral;
    private boolean mostrandoListaJogos = true;
    private ManipularJogos manipuladorJogos;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private String email;

    public TelaPrincipal(String email) {
        this.email = email;
        setTitle("Biblioteca de Jogos");
        setSize(600, 500);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.DARK_GRAY);

        manipuladorJogos = new ManipularJogos();

        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelSuperior.setBackground(Color.DARK_GRAY);

        pesquisaField = new JTextField();
        painelSuperior.add(pesquisaField, BorderLayout.CENTER);

        modeloPesquisa = new DefaultListModel<>();
        listaPesquisa = new JList<>(modeloPesquisa);
        listaPesquisa.setBackground(Color.LIGHT_GRAY);
        listaPesquisa.setVisibleRowCount(5);

        scrollPesquisa = new JScrollPane(listaPesquisa);
        scrollPesquisa.setVisible(false);
        add(scrollPesquisa, BorderLayout.NORTH);

        add(painelSuperior, BorderLayout.NORTH);

        // Painel central (Listas de jogos)
        painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(Color.DARK_GRAY);

        modeloLista = new DefaultListModel<>();
        for (Object jogo : preencherPainelMeusJogos()) {
            modeloLista.addElement(jogo.toString());
        }
        listaJogosUsuario = new JList<>(modeloLista);
        JScrollPane scrollJogos = new JScrollPane(listaJogosUsuario);
        scrollJogos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Seus Jogos"));
        scrollJogos.getViewport().setBackground(Color.DARK_GRAY);

        modeloListaDesejos = new DefaultListModel<>();
        for (Object jogo : preencherPainelDesejos()) {
            modeloListaDesejos.addElement(jogo.toString());
        }
        listaDesejos = new JList<>(modeloListaDesejos);
        JScrollPane scrollDesejos = new JScrollPane(listaDesejos);
        scrollDesejos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Lista de Desejos"));
        scrollDesejos.getViewport().setBackground(Color.DARK_GRAY);

        painelCentral.add(scrollJogos, BorderLayout.CENTER);
        add(painelCentral, BorderLayout.CENTER);

        // Painel inferior (botões)
        JPanel painelInferior = new JPanel(new FlowLayout());
        painelInferior.setBackground(Color.DARK_GRAY);

        novaRecomendacaoButton = new JButton("Nova Recomendação");
        novaRecomendacaoButton.setBackground(Color.GRAY);
        novaRecomendacaoButton.setForeground(Color.WHITE);
        novaRecomendacaoButton.setPreferredSize(new Dimension(150, 40));
        painelInferior.add(novaRecomendacaoButton);

        alternarListaButton = new JButton("Alternar Lista");
        alternarListaButton.setBackground(Color.GRAY);
        alternarListaButton.setForeground(Color.WHITE);
        alternarListaButton.setPreferredSize(new Dimension(150, 40));
        painelInferior.add(alternarListaButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setPreferredSize(new Dimension(100, 40));
        painelInferior.add(logoutButton);

        add(painelInferior, BorderLayout.SOUTH);

        alternarListaButton.addActionListener(e -> alternarLista());

        pesquisaField.addKeyListener(new KeyAdapter() {
            private Future<?> future;

            @Override
            public void keyReleased(KeyEvent e) {
                if (future != null) {
                    future.cancel(false);
                }
                String textoPesquisa = pesquisaField.getText().trim();
                future = executor.schedule(() -> SwingUtilities.invokeLater(() -> pesquisarJogos(textoPesquisa)), 500, TimeUnit.MILLISECONDS);
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                ajustarLayout();
            }
        });

        atualizarExibicaoListas();
    }

    private void ajustarLayout() {
        int larguraTela = getWidth();

        scrollPesquisa.setBounds(10, pesquisaField.getHeight() + 10, larguraTela - 20, 100);

        atualizarExibicaoListas();
    }

    private void pesquisarJogos(String termo) {
        modeloPesquisa.clear();
        if (!termo.isEmpty()) {
            List<String> jogosEncontrados = manipuladorJogos.buscarJogosPorNome(termo);
            for (String jogo : jogosEncontrados) {
                modeloPesquisa.addElement(jogo);
            }
            scrollPesquisa.setVisible(!jogosEncontrados.isEmpty());
        } else {
            scrollPesquisa.setVisible(false);
        }
    }

    private void alternarLista() {
        mostrandoListaJogos = !mostrandoListaJogos;
        atualizarExibicaoListas();
    }

    private void atualizarExibicaoListas() {
        painelCentral.removeAll();
        int larguraTela = getWidth();

        if (larguraTela > 700) {

            painelCentral.setLayout(new GridLayout(1, 2));

            JScrollPane scrollJogos = new JScrollPane(listaJogosUsuario);
            scrollJogos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Seus Jogos"));

            JScrollPane scrollDesejos = new JScrollPane(listaDesejos);
            scrollDesejos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Lista de Desejos"));

            painelCentral.add(scrollJogos);
            painelCentral.add(scrollDesejos);
        } else {

            painelCentral.setLayout(new BorderLayout());

            JScrollPane scrollPane = mostrandoListaJogos ? new JScrollPane(listaJogosUsuario) : new JScrollPane(listaDesejos);
            scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE),
                    mostrandoListaJogos ? "Seus Jogos" : "Lista de Desejos"));

            painelCentral.add(scrollPane, BorderLayout.CENTER);
        }
        painelCentral.revalidate();
        painelCentral.repaint();
    }

    
    private List preencherPainelDesejos(){
        ManipularListaDesejos manipuladorDesejos = new ManipularListaDesejos();
        ManipularJogos manipuladorJogos = new ManipularJogos();
        ArrayList<Integer> listaIDs = (ArrayList)manipuladorDesejos.ler(email);
        ArrayList listaDesejos = new ArrayList<>();
        
        for (Integer e : listaIDs) {
            listaDesejos.add(manipuladorJogos.ler(e));
        }

        return listaDesejos;
    }

    private List preencherPainelMeusJogos(){
        ManipularMeusJogos manipularMeusJogos = new ManipularMeusJogos();
        ManipularJogos manipuladorJogos = new ManipularJogos();
        ArrayList<Integer> listaIDs = (ArrayList)manipularMeusJogos.ler(email);
        ArrayList<Jogo> listaMeusJogos = new ArrayList<>();
        
        for (Integer e : listaIDs) {
            listaMeusJogos.add((Jogo) manipuladorJogos.ler(e));
        }

        return listaMeusJogos;
    }

}

