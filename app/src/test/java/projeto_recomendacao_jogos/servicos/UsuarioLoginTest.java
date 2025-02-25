package projeto_recomendacao_jogos.servicos;

import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioLoginTest {
    private static Connection connection;

    @BeforeAll
    static void configurarConexao() throws SQLException {
        String url = "jdbc:postgresql://ep-damp-bread-a8c72fk0-pooler.eastus2.azure.neon.tech/neondb?user=neondb_owner&password=npg_lb7T4dUiREIe&sslmode=require";
        String usuario = "neondb_owner";
        String senha = "npg_lb7T4dUiREIe";
        connection = DriverManager.getConnection(url, usuario, senha);
    }

    @Test
    void testarLoginComCredenciaisCorretas() throws SQLException {
        String email = "aiden_flarestone@example.com";
        String senha = "redwing2024";

        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            assertTrue(rs.next());
        }
    }

    @Test
    void testarLoginComCredenciaisCorretas2() throws SQLException {
        String email = "jake_embersoul@example.com";
        String senha = "blazingpath88";

        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            assertTrue(rs.next());
        }
    }

    @Test
    void testarLoginComCredenciaisErradas() throws SQLException {
        String email = "teste@email.com";
        String senhaErrada = "senhaIncorreta";

        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senhaErrada);
            ResultSet rs = stmt.executeQuery();
            assertFalse(rs.next());
        }
    }

    @AfterAll
    static void fecharConexao() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}