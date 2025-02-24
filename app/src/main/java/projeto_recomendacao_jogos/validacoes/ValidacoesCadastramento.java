package projeto_recomendacao_jogos.validacoes;

import projeto_recomendacao_jogos.dados.ManipularUsuarios;

import java.util.regex.Pattern;

public class ValidacoesCadastramento {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private ManipularUsuarios manipularUsuarios;

    public ValidacoesCadastramento() {
        this.manipularUsuarios = new ManipularUsuarios();
    }

    public boolean validarCampos(String nickname, String email, String senha) {
        return !(nickname.isEmpty() || email.isEmpty() || senha.isEmpty());
    }

    public boolean validarEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public boolean verificarEmailExistente(String email) {
        return manipularUsuarios.emailExiste(email);
    }

    public boolean verificarNicknameExistente(String nickname) {
        return manipularUsuarios.nicknameExiste(nickname);
    }
}