package projeto_recomendacao_jogos.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import projeto_recomendacao_jogos.objetos.Usuario;

public class ManipularUsuarios extends BancoDeDados {

    @Override
    public Connection chamar(Object obj) {
        try {
            return acessarConexao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void criar(Object obj) {
        if (!(obj instanceof Usuario)) {
            System.out.println("Erro: O objeto não é um usuário.");
            return;
        }

        Usuario usuario = (Usuario) obj;

        String sql = "INSERT INTO usuario (nickname, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = acessarConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNickname());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean emailExiste(String email) {
        return verificarExistencia("email", email);
    }

    public boolean nicknameExiste(String nickname) {
        return verificarExistencia("nickname", nickname);
    }

    private boolean verificarExistencia(String campo, String valor) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE " + campo + " = ?";
        try (Connection conn = acessarConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, valor);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
