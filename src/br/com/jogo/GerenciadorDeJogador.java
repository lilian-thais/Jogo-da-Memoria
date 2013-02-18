package br.com.jogo;
import java.util.ArrayList;
import java.util.List;


public class GerenciadorDeJogador {
	
	//Lista de jogadores
	private List<Jogador> jogadores;
	
	//construtor inicializando a lista de jogadores
	
	public GerenciadorDeJogador(){
		this.jogadores = new ArrayList<Jogador>();
	}
	
	public void adicionarJogador(Jogador jogador){ //assinatura do metodo
		this.jogadores.add (jogador);
	}
	
	public void removerJogador(Jogador jogador){ //assinatura do metodo
		this.jogadores.remove(jogador);
	}
	
	// metodo retorna o lider do metodo
	
	public Jogador liderDoRankig(){ // for de todos os jogadores
		Jogador aux = null;
		
		for(Jogador j:jogadores){ 
			if(aux==null){
				aux = j;
			}
			
			if (j.getPontuacao() >= aux.getPontuacao()){ //comparando que tem a pontuacao maior
				aux = j;
			}
		}
		
		return aux;
	}

	public Jogador pesquisarJogadorNome(String nome) throws JogadorInvalidoException{ // varrendo a lista e ver qual o jogador que tem o mesmo nome que o nome passado por parametro
		for(Jogador j:jogadores){ // 
			if(j.getNome().equals(nome)){
				return j;
			}
		}
		throw new JogadorInvalidoException("Jogador "+nome+" invalido!"); // quando nao achar o jogador lanca a excecao
	}
}



