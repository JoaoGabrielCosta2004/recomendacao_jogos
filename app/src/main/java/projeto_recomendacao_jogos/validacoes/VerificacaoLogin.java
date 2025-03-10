package projeto_recomendacao_jogos.validacoes;

import projeto_recomendacao_jogos.dados.BDStrategy;
import projeto_recomendacao_jogos.dados.ManipularUsuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerificacaoLogin {

    private BDStrategy contexto;

    public VerificacaoLogin() {
        this.contexto = new BDStrategy();
        this.contexto.setStrategy(new ManipularUsuarios());
    }

    public boolean verificarCredenciais(String email, String senha) {
        String sql = "SELECT senha FROM usuario WHERE email = ?";

        try (Connection conexao = (Connection) contexto.ler(null);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                String senhaArmazenada = resultado.getString("senha");
                return senhaArmazenada.equals(senha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}