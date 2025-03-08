package projeto_recomendacao_jogos.recomendacao;

import projeto_recomendacao_jogos.interfaces.IConteudo;
import projeto_recomendacao_jogos.interfaces.IRecomendacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import projeto_recomendacao_jogos.dados.ManipularAvaliacao;
import projeto_recomendacao_jogos.dados.ManipularJogos;

public class Recomendador implements IRecomendacao{
    private ManipularAvaliacao manipuladorAvaliacoes;
    private ManipularJogos manipuladorJogos;
    private String emailUsuario;
    private Random random;
    public Recomendador(String emailUsuario){
        this.emailUsuario = emailUsuario;
        manipuladorAvaliacoes = new ManipularAvaliacao();
        random = new Random();
    }

    @Override
    public IConteudo getRecomendacao() {
        int idJogo = getRandomJogoRecomendado(emailUsuario); 
        return (IConteudo) manipuladorJogos.ler(idJogo);
    }

    public Integer getRandomJogoRecomendado(String emailAvaliador){
        Map<String, Map<Integer, Boolean>> avaliacoes = manipuladorAvaliacoes.lerListaAvaliacao();
        Map<Integer, Boolean> avaliacoesUsuario = avaliacoes.getOrDefault(emailAvaliador, new HashMap<>());
        Map<Integer, Integer> pontuacaoJogos = new HashMap<>();

        for (var outroUsuario : avaliacoes.entrySet()) {
            if (outroUsuario.getKey().equals(emailAvaliador)) continue;
            for (var jogo : outroUsuario.getValue().entrySet()) {
                if (!avaliacoesUsuario.containsKey(jogo.getKey()) && jogo.getValue()) {
                    pontuacaoJogos.put(jogo.getKey(), pontuacaoJogos.getOrDefault(jogo.getKey(), 0) + 1);
                }
            }
        }
        
        List<Integer> recomendacoes = new ArrayList<>(pontuacaoJogos.keySet());
        if (recomendacoes.isEmpty()) return null;
        return recomendacoes.get(random.nextInt(recomendacoes.size()));
    }
    
}
