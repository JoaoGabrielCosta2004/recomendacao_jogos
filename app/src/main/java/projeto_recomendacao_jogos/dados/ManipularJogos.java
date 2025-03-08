package projeto_recomendacao_jogos.dados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto_recomendacao_jogos.interfaces.Conteudo;
import projeto_recomendacao_jogos.objetos.Jogo;



public class ManipularJogos extends BancoDeDados{

    @Override
    public Object ler(Object obj) {
        String nome;
        String genero;
        Date anoLancamento;
        String produtora;
            
        if (obj instanceof Integer id) {
            String sql = "SELECT id, nome, genero, anolancamento, produtora WHERE id = ?";
            try (Connection conexao = acessarConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet res = stmt.executeQuery()) {
                    nome = res.getString("nome");
                    genero = res.getString("genero");
                    anoLancamento = res.getDate("anolancamento");
                    produtora = res.getString("produtora");

                    Jogo dadoJogo = new Jogo(nome, genero, anoLancamento, produtora);
                    dadoJogo.setID(id);
                    return dadoJogo;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null; //arrumar isso aqui depois, adicionar alguma excessão
        
    }

    public List<String> buscarJogosPorNome(String termo) {
        List<String> jogos = new ArrayList<>();
        String sql = "SELECT nome FROM jogo WHERE LOWER(nome) LIKE LOWER(?)";

        try (Connection conexao = acessarConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, termo + "%");
            try (ResultSet res = stmt.executeQuery()) {
                while (res.next()) {
                    jogos.add(res.getString("nome"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jogos;
    }


    @Override
    public void criar(Object obj) {
        if (obj instanceof Conteudo cont) {
            String sql = "INSERT INTO jogo(nome, genero, anolancamento, produtora) VALUES (?, ?, ?, ?)";
            try (Connection conexao = acessarConexao()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, cont.getNome());
                stmt.setString(2, cont.getGenero());
                stmt.setDate(3, cont.getAnoLancamento());
                stmt.setString(4, cont.getProdutora());
                stmt.executeUpdate();

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cont.setID(generatedKeys.getInt(1));
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(Object obj) {
        if (obj instanceof Conteudo cont) {
            String sql = "UPDATE jogo SET nome = ?, genero = ?, anolancamento = ?, produtora = ? WHERE id = ?";
            try (Connection conexao = acessarConexao()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, cont.getNome());
                stmt.setString(2, cont.getGenero());
                stmt.setDate(3, cont.getAnoLancamento());
                stmt.setString(4, cont.getProdutora());
                stmt.setInt(5, cont.getID());
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deletar(Object obj) {
        if (obj instanceof Conteudo cont) {
            String sql = "DELETE FROM jogo WHERE id = ?";
            try (Connection conexao = acessarConexao()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, cont.getID());
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int obterIdJogoPorNome(String nomeJogo) {
        int idJogo = -1;
        String sql = "SELECT id FROM jogo WHERE nome = ?";

        try (Connection conexao = acessarConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nomeJogo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idJogo = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idJogo;
    }

    public List<String> buscarJogosUsuario(String email) {
        List<String> jogos = new ArrayList<>();
        String sql = "SELECT j.nome FROM usuariojogos uj JOIN jogo j ON uj.idjogo = j.id WHERE uj.emailusuario = ?";

        try (Connection conexao = acessarConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                jogos.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jogos;
    }

    public List<String> buscarListaDesejos(String email) {
        List<String> desejos = new ArrayList<>();
        String sql = "SELECT j.nome FROM listadesejos ld JOIN jogo j ON ld.idjogo = j.id WHERE ld.emailusuario = ?";

        try (Connection conexao = acessarConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                desejos.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return desejos;
    }

}