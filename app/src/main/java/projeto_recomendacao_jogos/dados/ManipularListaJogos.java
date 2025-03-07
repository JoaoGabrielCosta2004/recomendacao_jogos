package projeto_recomendacao_jogos.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManipularListaJogos extends BancoDeDados {

    public void adicionarJogoListaDesejos(String emailUsuario, int idJogo) {
        String sql = "INSERT INTO listadesejos (emailusuario, idjogo) VALUES (?, ?)";

        try (Connection conexao = acessarConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, emailUsuario);
            stmt.setInt(2, idJogo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarJogoPossuido(String emailUsuario, int idJogo) {
        String sql = "INSERT INTO usuariojogos (emailusuario, idjogo) VALUES (?, ?)";

        try (Connection conexao = acessarConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, emailUsuario);
            stmt.setInt(2, idJogo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object ler(Object obj) {
        throw new UnsupportedOperationException("Unimplemented method 'ler'");
    }

    @Override
    public void criar(Object obj) {
        throw new UnsupportedOperationException("Unimplemented method 'criar'");
    }

    @Override
    public void atualizar(Object obj) {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(Object obj) {
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }
}
