package projeto_recomendacao_jogos.servicos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class UsuarioCadastroTest {
    private static Connection connection;

    @BeforeAll
    static void configurarConexao() throws SQLException {
        String url = "jdbc:postgresql://ep-damp-bread-a8c72fk0-pooler.eastus2.azure.neon.tech/neondb?user=neondb_owner&password=npg_lb7T4dUiREIe&sslmode=require";
        String usuario = "neondb_owner";
        String senha = "npg_lb7T4dUiREIe";
        connection = DriverManager.getConnection(url, usuario, senha);
    }

    // @Test
    // void testarCadastroUsuario() throws SQLException {
    //     String emailTeste = "teste@email.com";
    //     String nicknameTeste = "UsuarioTeste";
    //     String senhaTeste = "123456";

    //     // Inserindo usuário no banco
    //     String sqlInserir = "INSERT INTO usuario (nickname, email, senha) VALUES (?, ?, ?)";
    //     try (PreparedStatement stmt = connection.prepareStatement(sqlInserir)) {
    //         stmt.setString(1, nicknameTeste);
    //         stmt.setString(2, emailTeste);
    //         stmt.setString(3, senhaTeste);
    //         int linhasAfetadas = stmt.executeUpdate();
    //         assertEquals(1, linhasAfetadas);
    //     }

    //     // Verificando se foi inserido corretamente
    //     String sqlVerificar = "SELECT * FROM usuario WHERE email = ?";
    //     try (PreparedStatement stmt = connection.prepareStatement(sqlVerificar)) {
    //         stmt.setString(1, emailTeste);
    //         ResultSet rs = stmt.executeQuery();
    //         assertTrue(rs.next());
    //         assertEquals(nicknameTeste, rs.getString("nickname"));
    //         assertEquals(emailTeste, rs.getString("email"));
    //         assertEquals(senhaTeste, rs.getString("senha"));
    //     }
    // }

    @AfterAll
    static void fecharConexao() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}