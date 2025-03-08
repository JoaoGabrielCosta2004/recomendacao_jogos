package projeto_recomendacao_jogos.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ManipularListaDesejos extends BancoDeDados{

    @Override
    public Object ler(Object obj) {
        ArrayList<Integer> listaDesejos = new ArrayList<>();

        if (obj instanceof String id) {
            String sql = "SELECT idjogo FROM listadesejos WHERE emailusuario = ?";
            try (Connection conexao = acessarConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int idjogo = rs.getInt("idjogo");
                    listaDesejos.add(idjogo);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listaDesejos;
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
