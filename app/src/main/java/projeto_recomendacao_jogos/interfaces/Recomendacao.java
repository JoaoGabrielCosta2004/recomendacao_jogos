package projeto_recomendacao_jogos.interfaces;

import java.util.List;

public interface Recomendacao {
    public void ordenar();
    public List<Conteudo> filtrarJogos(Object e);
    public List<Conteudo> getListaRecomendados();
}
