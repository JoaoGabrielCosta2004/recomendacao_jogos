package projeto_recomendacao_jogos.dados;
 
 public class BDStrategy {
     //mudar o nome dessa classe
 
     
     private BancoDeDados dados;
     public void setStrategy(BancoDeDados dados){
         this.dados = dados;
     }
     
     public Object ler(Object obj) {
         return dados.ler(obj);
     }
 
     public void criar(Object obj) {
         dados.criar(obj);
     }
 
     public void atualizar(Object obj) {
         dados.atualizar(obj);
     }
 
     public void deletar(Object obj) {
         dados.deletar(obj);
     }
 }
