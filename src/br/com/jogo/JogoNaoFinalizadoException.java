package br.com.jogo;

public class JogoNaoFinalizadoException extends Exception {
	
	String erro;

	
	public JogoNaoFinalizadoException() {
		super(); // chamar o construtor da superclasse
		erro = "unknown";
	}

	
	public JogoNaoFinalizadoException(String err) {
		super(err); // chamar o construtor da superclasse
		erro = err; // salva messagem
	}

	
	public String getError() {
		return erro;
	}
}






