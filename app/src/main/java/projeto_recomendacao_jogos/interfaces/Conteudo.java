package projeto_recomendacao_jogos.interfaces;

import java.sql.Date;

public interface Conteudo {
    public Integer getID();
    public void setID(Integer id);
    public Integer getAvaliacao();
    public String getNome();
    public Date getAnoLancamento();
    public String getProdutora();
    public String getGenero();
}