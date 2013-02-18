package br.com.jogo;

public class JogoMemoriaFachada {
	
        Tabuleiro tab = null;
        Jogador jogador1 = null;
        Jogador jogador2 = null;
        GerenciadorDeJogador gerente = new GerenciadorDeJogador();
        
        int controle = 0;
        int jogadorAtual = 1;
        
	public void adicionarJogador(Jogador jogador){ 
            this.gerente.adicionarJogador(jogador);
	}
	
	public void removerJogador(Jogador jogador){ 
            this.gerente.removerJogador(jogador);
        }
	
	public Jogador pesquisarJogadorNome(String nome) throws JogadorInvalidoException{ 
		return this.gerente.pesquisarJogadorNome(nome); 
	}


	public String desvirarCarta(PosicaoCarta pos) throws DesvirarCartaDuasVezesSeguidasException, PosicaoInvalidaException{
                String carta = null;
                if(this.controle == 0){
                    carta = this.tab.desvirarCarta(pos);
                    ++this.controle;
                }else{
                    carta = this.tab.desvirarCarta(pos);
                    if(this.tab.asCartasDesviradasSaoIguais()){
                    
                        if(this.jogadorAtual == 1){
                            this.jogador1.setPontuacao(this.jogador1.getPontuacao() + 10);
                            this.jogadorAtual = 1;
                        }else{
                            this.jogador2.setPontuacao(this.jogador2.getPontuacao() + 10);
                            this.jogadorAtual = 2;
                        }
                    }else{
                        if(this.jogadorAtual == 1){
                            this.jogador1.setPontuacao(this.jogador1.getPontuacao() - 2);
                            this.jogadorAtual = 2;
                        }else{
                            this.jogador2.setPontuacao(this.jogador2.getPontuacao() - 2);
                            this.jogadorAtual = 1;
                        }
                    }
                    this.tab.virarCartas();
                    this.controle = 0;
                }
            
            return carta;
		
	}
	
	public void iniciarPartida() throws JogadorNaoSelecionadoException{
            if((this.jogador1 == null)||(this.jogador2 == null)){
                throw new JogadorNaoSelecionadoException();
            }
            this.tab = new Tabuleiro();
            this.tab.init();
        }

	public void selecionarJogador1( String nome) throws JogadorInvalidoException{
		this.jogador1 = this.gerente.pesquisarJogadorNome(nome);
	}

        public void selecionarJogador2( String nome)throws JogadorInvalidoException{
        	this.jogador2 = this.gerente.pesquisarJogadorNome(nome);
	}
        public Jogador getJogador1() {
        	return this.jogador1;
        }
        
        public Jogador getJogador2(){
        	return this.jogador2;
        }
        
        public Jogador getJogadorVencedor()throws JogoNaoFinalizadoException{
            if(!this.tab.jogoEstaFinalizado()){
                throw new JogoNaoFinalizadoException("Jogo não acabaou");
            }
            if(this.jogador1.getPontuacao() > this.jogador2.getPontuacao()){
                return this.jogador1;
            }else{
                return this.jogador2;
            }
        }
}