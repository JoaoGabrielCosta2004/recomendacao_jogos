package projeto_recomendacao_jogos.objetos;

import java.util.ArrayList;

import projeto_recomendacao_jogos.interfaces.Conteudo;
import projeto_recomendacao_jogos.interfaces.Usuario;

public class gitUsuarioCliente implements Usuario{
    private String nome;
    private String email;
    private String senha;
    private ArrayList<Conteudo> listaJogos;

    @Override
    public ArrayList<Conteudo> getListaJogos() {
        return listaJogos;
    }

    @Override
    public void addJogo(Conteudo conteudo) {
        
    }

    @Override
    public void removeJogo(Conteudo conteudo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeJogo'");
    }
    
}
