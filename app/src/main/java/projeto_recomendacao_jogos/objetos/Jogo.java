package projeto_recomendacao_jogos.objetos;

import java.sql.Date;

import projeto_recomendacao_jogos.interfaces.IConteudo;



public class Jogo implements IConteudo{
    Integer id;
    String nome;
    String genero;
    Date anoLancamento;
    String produtora;


    public Jogo(String nome, String genero, Date anoLancamento, String produtora){
        this.nome = nome;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.produtora = produtora;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Date getAnoLancamento() {
        return anoLancamento;
    }
    public void setAnoLancamento(Date anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public String getProdutora() {
        return produtora;
    }
    public void setProdutora(String produtora) {
        this.produtora = produtora;
    }

    @Override
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString(){
        return nome + " | " + anoLancamento + " | " + produtora + " | " + genero;
    }

}
