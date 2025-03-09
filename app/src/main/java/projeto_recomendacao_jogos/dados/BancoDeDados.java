package projeto_recomendacao_jogos.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BancoDeDados {
    protected static final String URL = "jdbc:postgresql://ep-damp-bread-a8c72fk0-pooler.eastus2.azure.neon.tech/neondb?user=neondb_owner&password=npg_lb7T4dUiREIe&sslmode=require";
    protected static final String USUARIO = "neondb_owner";
    protected static final String SENHA = "npg_lb7T4dUiREIe";
    
    public Connection acessarConexao() throws SQLException{
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public abstract Object ler(Object obj);
    public abstract void criar(Object obj);
    public abstract void atualizar(Object obj);
    public abstract void deletar(Object obj);

}
