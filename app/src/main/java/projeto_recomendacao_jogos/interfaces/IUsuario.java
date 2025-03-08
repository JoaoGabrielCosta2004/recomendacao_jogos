package projeto_recomendacao_jogos.interfaces;

import java.util.List;

public interface IUsuario {
    List<IConteudo> getListaJogos();
    void addJogo(IConteudo conteudo);
    void removeJogo(IConteudo conteudo);
    String getNickname();
    String getSenha();
    String getEmail();
}
