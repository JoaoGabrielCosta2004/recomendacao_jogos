package projeto_recomendacao_jogos.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import projeto_recomendacao_jogos.interfaces.IAvaliacao;

public class ManipularAvaliacao  extends BancoDeDados{

    @Override
    public Object ler(Object obj) {
        String sql = "SELECT id, nome, genero, anolancamento, produtora FROM jogo WHERE id = ?";
        try (Connection conexao = acessarConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
                //arrumar para montar o objeto visualização *URGENTE*
                
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return null;
    }
    public Map<String, Map<Integer, Boolean>> lerListaAvaliacao(){
        Map<String, Map<Integer, Boolean>> avaliacoes = new HashMap<>();

        String sql = "SELECT id, nome, genero, anolancamento, produtora FROM jogo WHERE id = ?";
        try (Connection conexao = acessarConexao(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery("SELECT idjogo, emailavaliador, gostei FROM avaliacao")) {
                while (rs.next()) {
                    int idjogo = rs.getInt("idjogo");
                    String emailAvaliador = rs.getString("emailavaliador");
                    boolean gostei = rs.getBoolean("gostei");

                    avaliacoes.computeIfAbsent(emailAvaliador, k -> new HashMap<>()).put(idjogo, gostei);
                }
                
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return avaliacoes;
    }

    @Override
    public void criar(Object obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criar'");
    }

    @Override
    public void atualizar(Object obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(Object obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    

}
