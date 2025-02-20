package projeto_recomendacao_jogos.interfaces;

import java.util.List;

public interface Usuario {
    public List<Conteudo> getListaJogos();
    public void addJogo(Conteudo conteudo);
    public void removeJogo(Conteudo conteudo);
}
