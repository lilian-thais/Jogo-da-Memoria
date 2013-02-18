package br.com.jogo;

public class PosicaoInvalidaException extends Exception {
	
	String erro;

	public PosicaoInvalidaException() {
		super(); // chamar o construtor da superclasse
		erro = "unknown";
	}

	
	public PosicaoInvalidaException(String err) {
		super(err); // chamar o construtor da superclasse
		erro = err; // salva messagem
	}

	public String getError() {
		return erro;
	}
}


	