package projeto_recomendacao_jogos.objetos;

import projeto_recomendacao_jogos.interfaces.IAvaliacao;

public class Avaliacao implements IAvaliacao{
    private int idJogo;
    private String emailUsuario;
    private boolean gostei;
    private String comentario;
    public Avaliacao(int idJogo, String emailUsuario, boolean gostei){
        this.idJogo = idJogo;
        this.emailUsuario = emailUsuario;
        this.gostei = gostei;
    }
    @Override
    public Boolean retornarAvaliacao() {
        return gostei;
    }
    @Override
    public void editComentario(String comentario) {
        this.comentario = comentario;
    }
    @Override
    public String getComentario() {
        return comentario;
    }

    public int getIdJogo(){
        return idJogo;
    }

    public String getEmailUsuario(){
        return emailUsuario;
    }
}
