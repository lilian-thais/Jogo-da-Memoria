package br.com.jogo;

public class Carta {

	private int codigo;
        private boolean virada = true;
	private String historia;
        
        Carta(int c, String d){
            this.codigo = c;
            this.historia = d;
        }
	
        public boolean equals(Carta c){
            if(this.historia.equals(c.historia)){
                return true;
            }else{
                return false;
            }
        }
        
        public void desvira(){
            this.virada = false;
        }
        
        public void vira(){
            this.virada = true;
        }
        
        public boolean estaVirada(){
            return this.virada;
        }
        
	public int getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(int codigo){
		this.codigo = codigo;
	}
	
	public String getHistoria(){
		return this.historia;
	}
	
	public void setHistoria(String historia){
		this.historia = historia;
	}
	
	public String toString(){
		return "Codigo:"+this.codigo+"\n historia";
		
	}
	
}
