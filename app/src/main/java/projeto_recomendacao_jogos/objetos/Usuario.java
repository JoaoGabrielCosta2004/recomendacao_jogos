package projeto_recomendacao_jogos.objetos;

public class Usuario {
    private String email;
    private String nickname;
    private String senha;

    public Usuario(String nickname, String email, String senha) {
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSenha() {
        return senha;
    }
}
