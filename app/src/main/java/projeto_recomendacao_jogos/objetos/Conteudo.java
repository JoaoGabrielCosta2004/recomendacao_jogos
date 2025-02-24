package projeto_recomendacao_jogos.objetos;

public class Conteudo {
    private String nome;
    private String categoria;

    public Conteudo(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Conteudo conteudo = (Conteudo) obj;
        return nome.equals(conteudo.nome) && categoria.equals(conteudo.categoria);
    }

    @Override
    public int hashCode() {
        return nome.hashCode() + categoria.hashCode();
    }

    @Override
    public String toString() {
        return "Conteudo{" +
                "nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
