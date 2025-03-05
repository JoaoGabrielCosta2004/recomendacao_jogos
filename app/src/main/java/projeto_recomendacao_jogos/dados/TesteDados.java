package projeto_recomendacao_jogos.dados;

import projeto_recomendacao_jogos.objetos.Jogo;
import java.sql.*;

public class TesteDados {
    public static void main(String[] args) {
        // Criação do contexto e do jogo
        AcessoDados context = new AcessoDados();
        
        // Criando o novo jogo com java.sql.Date
        Date dataLancamento = Date.valueOf("2021-03-26");  // Utilizando Date.valueOf() para criar uma data SQL
        Jogo testejogo = new Jogo("Monster Hunter Rise", "RPG", dataLancamento, "Capcom");

        // Definindo a estratégia de manipulação de jogos
        context.setStrategy(new ManipularJogos());

        // Criando o jogo no banco de dados
        context.criar(testejogo);

        // Verificando o ID gerado após a inserção (caso seja necessário)
        System.out.println("ID do jogo inserido: " + testejogo.getID());
    }
}
