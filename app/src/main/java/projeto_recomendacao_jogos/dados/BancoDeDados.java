package projeto_recomendacao_jogos.conexao_bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDeDados {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://ep-damp-bread-a8c72fk0-pooler.eastus2.azure.neon.tech/neondb?user=neondb_owner&password=npg_lb7T4dUiREIe&sslmode=require";
        String usuario = "neondb_owner";
        String senha = "npg_lb7T4dUiREIe";

        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso!");

            // Não esqueça de fechar a conexão após o uso
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
