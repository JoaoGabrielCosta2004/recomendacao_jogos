package projeto_recomendacao_jogos.interfaces;

import projeto_recomendacao_jogos.objetos.Conteudo;
import java.util.List;

public interface IUsuario {
    List<Conteudo> getListaJogos();
    void addJogo(Conteudo conteudo);
    void removeJogo(Conteudo conteudo);
    String getNickname();
    String getSenha();
    String getEmail();
}
