package projeto_recomendacao_jogos.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {
    private final JTextField pesquisaField;
    private final JButton novaRecomendacaoButton;
    private final JButton logoutButton;
    private final JButton alternarListaButton;
    private final JList<String> listaJogosUsuario;
    private final DefaultListModel<String> modeloLista;
    private final JList<String> listaDesejos;
    private final DefaultListModel<String> modeloListaDesejos;
    private final JPanel painelCentral;
    private boolean mostrandoListaJogos = true;

    public TelaPrincipal() {
        setTitle("Biblioteca de Jogos");
        setSize(500, 500);
        setMinimumSize(new Dimension(500, 400)); // Define um tamanho mínimo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.DARK_GRAY);

        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelSuperior.setBackground(Color.DARK_GRAY);

        JLabel labelPesquisa = new JLabel("Pesquisar Jogos:");
        labelPesquisa.setForeground(Color.WHITE);
        painelSuperior.add(labelPesquisa, BorderLayout.WEST);

        pesquisaField = new JTextField();
        painelSuperior.add(pesquisaField, BorderLayout.CENTER);

        add(painelSuperior, BorderLayout.NORTH);

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

        alternarListaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painelCentral.removeAll();
                if (mostrandoListaJogos) {
                    painelCentral.add(scrollDesejos, BorderLayout.CENTER);
                } else {
                    painelCentral.add(scrollJogos, BorderLayout.CENTER);
                }
                mostrandoListaJogos = !mostrandoListaJogos;
                painelCentral.revalidate();
                painelCentral.repaint();
            }
        });


        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                ajustarLayout();
            }
        });
    }

    private void ajustarLayout() {
        painelCentral.removeAll();
        if (getWidth() > 600) {
            JPanel painelDuasListas = new JPanel(new GridLayout(1, 2, 10, 10));
            painelDuasListas.setBackground(Color.DARK_GRAY);
            painelDuasListas.add(criarPainelComTitulo("Seus Jogos", listaJogosUsuario));
            painelDuasListas.add(criarPainelComTitulo("Lista de Desejos", listaDesejos));
            painelCentral.add(painelDuasListas, BorderLayout.CENTER);
            alternarListaButton.setVisible(false);
        } else {
            painelCentral.add(mostrandoListaJogos ? criarPainelComTitulo("Seus Jogos", listaJogosUsuario) : criarPainelComTitulo("Lista de Desejos", listaDesejos), BorderLayout.CENTER);
            alternarListaButton.setVisible(true);
        }
        painelCentral.revalidate();
        painelCentral.repaint();
    }

    private JPanel criarPainelComTitulo(String titulo, JList<String> lista) {
        JPanel painelLista = new JPanel(new BorderLayout());
        painelLista.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), titulo));
        painelLista.add(new JScrollPane(lista), BorderLayout.CENTER);
        return painelLista;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}