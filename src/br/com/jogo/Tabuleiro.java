package br.com.jogo;

public class Tabuleiro {
	
	// Matriz de cartas
	private Carta[][] tabuleiro; 
	
        private PosicaoCarta pc1 = null;
        private PosicaoCarta pc2 = null;
        
        public void init(){
            //Aloca tabuleiro
            this.tabuleiro = new Carta[2][2];
            
            //Preenche tabuleiro
            this.tabuleiro[0][0] = new Carta(0, "Bola");
            this.tabuleiro[0][1] = new Carta(1, "Estrela");
            this.tabuleiro[1][0] = new Carta(2, "Estrela");
            this.tabuleiro[1][1] = new Carta(3, "Bola");
        }
        
        public boolean jogoEstaFinalizado(){
            boolean finalizado = true;
            for(int i=0; i < this.tabuleiro.length; ++i){
                for(int j=0; j < this.tabuleiro.length; ++j){
                    if(this.tabuleiro[i][j].estaVirada()){
                        System.out.println("X: " + i + " Y: " + j);
                        finalizado = false;
                        break;
                    }
                }
            }
            return finalizado;
        }
        
        public String desvirarCarta(PosicaoCarta pc) throws DesvirarCartaDuasVezesSeguidasException{
            if(this.pc2 != null)
                return null;
                
            Carta c = this.tabuleiro[pc.getX()][pc.getY()];
            if(!c.estaVirada()){
                throw new DesvirarCartaDuasVezesSeguidasException("Carta já virada.");
            }
            c.desvira();
            
            if(this.pc1 == null){
                this.pc1 = pc;
            }else{
                this.pc2 = pc;
            }
            
            return c.getHistoria();
        }
        
        public boolean asCartasDesviradasSaoIguais(){
            Carta c1 = this.tabuleiro[this.pc1.getX()][this.pc1.getY()];
            Carta c2 = this.tabuleiro[this.pc2.getX()][this.pc2.getY()];
            
            if(c1.equals(c2)){
                return true;
            }else{
                return false;
            }
        }
        
        public void virarCartas(){
            if(!this.asCartasDesviradasSaoIguais()){
                this.tabuleiro[this.pc1.getX()][this.pc1.getY()].desvira();
                this.tabuleiro[this.pc2.getX()][this.pc2.getY()].desvira();
            }
            this.pc1 = null;
            this.pc2 = null;
        }

}
