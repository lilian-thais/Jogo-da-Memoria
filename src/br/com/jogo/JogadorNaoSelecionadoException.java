package br.com.jogo;

public class JogadorNaoSelecionadoException extends Exception {

	String erro;

	// Construtor padrao - inicializa variavel de instancia para o desconhecido

	public JogadorNaoSelecionadoException() {
		super(); // chamar o construtor da superclasse
		erro = "unknown";
	}

	// Construtor recebe algum tipo de mensagem que e salvo em uma Variavel de
	// instancia

	public JogadorNaoSelecionadoException(String err) {
		super(err); // chamar o construtor da superclasse
		erro = err; // salva messagem
	}

	// metodo publico, pode ser chamado pelo coletor de excecao. Ele retorna a
	// mensagem de erro

	public String getError() {
		return erro;
	}
}
