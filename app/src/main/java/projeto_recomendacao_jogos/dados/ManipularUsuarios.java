package projeto_recomendacao_jogos.dados;

import java.sql.Connection;
import java.sql.SQLException;

public class ManipularUsuarios extends BancoDeDados{

    @Override
    public Connection ler(Object obj) {
        try {
            return acessarConexao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
