package projeto_recomendacao_jogos.telas;

import projeto_recomendacao_jogos.dados.ManipularJogos;
import projeto_recomendacao_jogos.dados.ManipularListaJogos;
import projeto_recomendacao_jogos.dados.ManipularUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.concurrent.*;

public class TelaPrincipal extends JFrame {
    private final JTextField pesquisaField;
    private final JList<String> listaPesquisa; //
    private final DefaultListModel<String> modeloPesquisa;
    private final JScrollPane scrollPesquisa;
    private final JButton novaRecomendacaoButton;
    private final JButton logoutButton;
    private final JButton alternarListaButton;
    private final JList<String> listaJogosUsuario; //
    private final DefaultListModel<String> modeloLista;
    private final JList<String> listaDesejos; //
    private JLabel nicknameLabel;
    private final DefaultListModel<String> modeloListaDesejos;
    private final JPanel painelCentral;
    private boolean mostrandoListaJogos = true;
    private final ManipularJogos manipuladorJogos;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private String email;
    private ManipularListaJogos manipularListaJogos;

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
        ManipularUsuarios manipuladorUsuarios = new ManipularUsuarios();
        manipularListaJogos = new ManipularListaJogos();

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

        nicknameLabel = new JLabel();
        nicknameLabel.setForeground(Color.WHITE);


        String nickname = manipuladorUsuarios.buscarNicknamePorEmail(email);
        if (nickname != null) {
            nicknameLabel.setText("Usuário: " + nickname);
        } else {
            nicknameLabel.setText("Usuário não encontrado");
        }

        painelSuperior.add(nicknameLabel, BorderLayout.EAST);

        painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(Color.DARK_GRAY);

        modeloLista = new DefaultListModel<>();
        listaJogosUsuario = new JList<>(modeloLista);
        JScrollPane scrollJogos = new JScrollPane(listaJogosUsuario);
        scrollJogos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Seus Jogos"));
        scrollJogos.getViewport().setBackground(Color.DARK_GRAY);

        modeloListaDesejos = new DefaultListModel<>();
        listaDesejos = new JList<>(modeloListaDesejos);
        JScrollPane scrollDesejos = new JScrollPane(listaDesejos);
        scrollDesejos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Lista de Desejos"));
        scrollDesejos.getViewport().setBackground(Color.DARK_GRAY);

        painelCentral.add(scrollJogos, BorderLayout.CENTER);
        add(painelCentral, BorderLayout.CENTER);

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

        adicionarEventoCliqueDireito(listaJogosUsuario);
        adicionarEventoCliqueDireito(listaDesejos);
        adicionarEventoCliqueDireito(listaPesquisa);

        atualizarExibicaoListas();

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

        listaPesquisa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) adicionarJogo(listaPesquisa.getSelectedValue());
            }
        });

        listaPesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) adicionarJogo(listaPesquisa.getSelectedValue());
            }
        });

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                ajustarLayout();
            }
        });

        atualizarExibicaoListas();

        logoutButton.addActionListener(e -> abrirTelaLogin());
    }

    private void abrirTelaLogin() {
        this.dispose();
        new TelaLogin().setVisible(true);
    }

    private void adicionarEventoCliqueDireito(JList<String> lista) {
        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int index = lista.locationToIndex(e.getPoint());
                    if (index != -1) {
                        lista.setSelectedIndex(index);
                        String nomeJogo = lista.getSelectedValue();
                        exibirInformacoesJogo(nomeJogo);
                    }
                }
            }
        });
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

        modeloLista.clear();
        modeloListaDesejos.clear();

        List<String> jogosUsuario = manipuladorJogos.buscarJogosUsuario(email);
        for (String jogo : jogosUsuario) {
            modeloLista.addElement(jogo);
        }

        List<String> desejosUsuario = manipuladorJogos.buscarListaDesejos(email);
        for (String desejo : desejosUsuario) {
            modeloListaDesejos.addElement(desejo);
        }

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

    private void exibirInformacoesJogo(String nomeJogo) {
        ManipularJogos manipulador = new ManipularJogos();
        String info = manipulador.buscarInformacoesJogo(nomeJogo);
        JOptionPane.showMessageDialog(null, info, "Informações do Jogo", JOptionPane.INFORMATION_MESSAGE);
    }


    private void adicionarJogo(String nomeJogo) {
        if (nomeJogo != null) {
            int idJogo = manipuladorJogos.obterIdJogoPorNome(nomeJogo);
            if (idJogo != -1) {
                int opcao = JOptionPane.showOptionDialog(
                        this,
                        "Deseja adicionar '" + nomeJogo + "' à lista de jogos possuídos ou à lista de desejos?",
                        "Adicionar Jogo",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Jogos Possuídos", "Lista de Desejos"},
                        "Jogos Possuídos"
                );

                if (opcao == JOptionPane.YES_OPTION) {
                    manipularListaJogos.adicionarJogoPossuido(email, idJogo);
                } else if (opcao == JOptionPane.NO_OPTION) {
                    manipularListaJogos.adicionarJogoListaDesejos(email, idJogo);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao encontrar o jogo no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

