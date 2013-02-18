package br.com.jogo;

public class DesvirarCartaDuasVezesSeguidasException extends Exception {
	
	String erro;

	// Construtor padrao - inicializa variavel de instancia para o desconhecido

	public DesvirarCartaDuasVezesSeguidasException() {
		super(); // chamar o construtor da superclasse
		erro = "unknown";
	}

	// Construtor recebe algum tipo de mensagem que o salvo em uma Variavel de
	// instancia

	public DesvirarCartaDuasVezesSeguidasException(String err) {
		super(err); // chamar o construtor da superclasse
		erro = err; // salva messagem
	}

	// metodo publico, pode ser chamado pelo coletor de excecao. Ele retorna a
	// mensagem de erro

	public String getError() {
		return erro;
	}
}



