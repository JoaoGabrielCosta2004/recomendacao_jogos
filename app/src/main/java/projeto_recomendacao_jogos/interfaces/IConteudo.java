package projeto_recomendacao_jogos.interfaces;

import java.sql.Date;

public interface IConteudo {
    public Integer getID();
    public void setID(Integer id);
    public String getNome();
    public Date getAnoLancamento();
    public String getProdutora();
    public String getGenero();
}