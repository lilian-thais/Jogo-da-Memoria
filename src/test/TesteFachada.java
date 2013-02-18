package test;

import br.com.jogo.DesvirarCartaDuasVezesSeguidasException;
import br.com.jogo.Jogador;
import br.com.jogo.JogadorInvalidoException;
import br.com.jogo.JogadorNaoSelecionadoException;
import br.com.jogo.JogoMemoriaFachada;
import br.com.jogo.JogoNaoFinalizadoException;
import br.com.jogo.PosicaoCarta;
import br.com.jogo.PosicaoInvalidaException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TesteFachada {

    private JogoMemoriaFachada JMF = null;
   
    public void init() {
        if(JMF != null)
            return;
        JMF = new JogoMemoriaFachada();

        Jogador jogador = new Jogador("Jose", 0, 0);
        JMF.adicionarJogador(jogador);
        jogador = new Jogador("Lilian", 0, 0);
        JMF.adicionarJogador(jogador);
        jogador = new Jogador("Roger", 0, 0);
        JMF.adicionarJogador(jogador);
    }

    @Test
    public void testeRegra1v1() {
        init();
        // Testa iniciaPartida sem jogadores
        try {
            JMF.iniciarPartida();
            Assert.fail("Partida iniciada sem jogadores");
        } catch (JogadorNaoSelecionadoException ex) {
        }
    }

    @Test
    public void testeRegra1v2() {
        init();
        // Testa iniciaPartida com apenas 1 jogador
        try {

            JMF.selecionarJogador1("Lilian");
        } catch (JogadorInvalidoException ex) {
        }
        try {
            JMF.iniciarPartida();
            Assert.fail("Partida iniciada sem jogadores");
        } catch (JogadorNaoSelecionadoException ex) {
        }
    }

    @Test
    public void testeRegra1v3() {
        init();
        // Testa iniciaPartida com 2 jogadores
        try {
            JMF.selecionarJogador1("Lilian");
            JMF.selecionarJogador2("Jose");
        } catch (JogadorInvalidoException ex) {
        }
        try {
            JMF.iniciarPartida();
        } catch (JogadorNaoSelecionadoException ex) {
            Assert.fail("Excecao lancada errada.");
        }
    }

    
    public void iniciarPartida() {
        // Testa iniciaPartida com 2 jogadores
        try {
            JMF.selecionarJogador1("Lilian");
            JMF.selecionarJogador2("Jose");
        } catch (JogadorInvalidoException ex) {
        }
        try {
            JMF.iniciarPartida();
        } catch (JogadorNaoSelecionadoException ex) {
            Assert.fail("Excecao lancada errada.");
        }
    }

    @Test
    public void testeConteudoCartaExibidoParaTodos() {
        init();
        iniciarPartida();
        try {

            PosicaoCarta pc = new PosicaoCarta(0, 1);
            String cartaValor = JMF.desvirarCarta(pc);
            Assert.assertEquals("Estrela", cartaValor);

        } catch (PosicaoInvalidaException jie) {
            Assert.fail("A posicao da Carta fornecida eh invalida");
        } catch (DesvirarCartaDuasVezesSeguidasException jie) {
            Assert.fail("A posicao da Carta fornecida eh invalida");
        }
    }

    // Testa acertar Par de cartas ganha 10 pontos e tem nova chance
    public void testeAcertarParDeCartaDesviraNovamente() {
        init();
        iniciarPartida();
        try {

            PosicaoCarta pc = new PosicaoCarta(0, 0);
            String cartaValor = JMF.desvirarCarta(pc);
            PosicaoCarta pc2 = new PosicaoCarta(1, 1);
            String cartaValor2 = JMF.desvirarCarta(pc);
            Jogador j1 = JMF.getJogador1();

            Assert.assertEquals(10, j1.getPontuacao());

        } catch (PosicaoInvalidaException jie) {
        } catch (DesvirarCartaDuasVezesSeguidasException jie) {
        }
    }

    // Testa errar Par de cartas perde 2 pontos
    public void testeErrarParDeCartaPerdePontos() {
        init();
        iniciarPartida();
        try {

            PosicaoCarta cp = new PosicaoCarta(0, 0);
            String cartaValor = JMF.desvirarCarta(cp);
            PosicaoCarta cp2 = new PosicaoCarta(1, 0);
            String cartaValor2 = JMF.desvirarCarta(cp2);
            Jogador j1 = JMF.getJogador1();

            Assert.assertEquals(-2, j1.getPontuacao());

        } catch (PosicaoInvalidaException jie) {
        } catch (DesvirarCartaDuasVezesSeguidasException jie) {
        }
    }

    @Test
    public void testeCartaNaoDeveSerDesviradaDuasVezesSeguidas() {
        init();
        iniciarPartida();
        
        // Nenhuma carta pode ser desvirada duas vezes seguidas
        try {

            PosicaoCarta pcd = new PosicaoCarta(0, 0);
            String cartaValor = JMF.desvirarCarta(pcd);
            PosicaoCarta pcd2 = new PosicaoCarta(0, 0);
            String cartaValor2 = JMF.desvirarCarta(pcd2);


            Assert.fail("A posicao da Carta fornecida eh invalida");

        } catch (PosicaoInvalidaException jie) {
        } catch (DesvirarCartaDuasVezesSeguidasException jie) {
           // Assert.fail("A posicao da Carta fornecida nao pode ser desvirada duas vezes seguidas");
        }
    }

    @Test
    public void testeQuemFoiVencedor() {
        init();
        iniciarPartida();
        
        // Teta quem foi o vencedor	
        try {


            PosicaoCarta posc = new PosicaoCarta(0, 0);
            String cartaValor = JMF.desvirarCarta(posc);
            PosicaoCarta posc2 = new PosicaoCarta(0, 0);
            String cartaValor2 = JMF.desvirarCarta(posc2);
            Jogador vencedor = JMF.getJogadorVencedor();

            Assert.fail("Exception nao foio lancada - A partida nao tem vencedor");

        } catch (PosicaoInvalidaException jie) {
            Assert.fail("Exception nao deveria ser lancada");

        } catch (DesvirarCartaDuasVezesSeguidasException jie) {
           // Assert.fail("A posicao da Carta fornecida nao pode ser desvirada duas vezes seguidas");

        } catch (JogoNaoFinalizadoException jnf) {
        }
    }

    public void testeVenceQuemTiverMaisPontosPartidaAtual() {
        init();
        iniciarPartida();
        
        // Testa quem tiver mais pontos na partida atual vence
        try {

            PosicaoCarta cartp = new PosicaoCarta(0, 0);
            String cartaValor = JMF.desvirarCarta(cartp);
            PosicaoCarta cartp2 = new PosicaoCarta(1, 1);
            String cartaValor2 = JMF.desvirarCarta(cartp2);
            PosicaoCarta cartp3 = new PosicaoCarta(0, 1);
            String cartaValor3 = JMF.desvirarCarta(cartp3);
            PosicaoCarta cartp4 = new PosicaoCarta(1, 0);
            String cartaValor4 = JMF.desvirarCarta(cartp4);
            Jogador jogador1 = JMF.getJogador1();



            Assert.assertEquals(jogador1, JMF.getJogadorVencedor());

        } catch (PosicaoInvalidaException jie) {
            Assert.fail("Exception nao deveria ser lancada");

        } catch (DesvirarCartaDuasVezesSeguidasException jie) {
            Assert.fail("A posicao da Carta fornecida nao pode ser desvirada duas vezes seguidas");

        } catch (JogoNaoFinalizadoException jnf) {
            Assert.fail("O jogo nao foi finalizado");
        }
    }

    public void testePontuacaoJogadorAcrescentadaNaPontuacaoRaking() {
        init();
        iniciarPartida();
        
        try {

            PosicaoCarta pc = new PosicaoCarta(0, 0);
            String cartaValor = JMF.desvirarCarta(pc);
            PosicaoCarta pc2 = new PosicaoCarta(1, 1);
            String cartaValor2 = JMF.desvirarCarta(pc);
            Jogador j1 = JMF.getJogador1();

            Assert.assertEquals(10, j1.getRaking());

        } catch (PosicaoInvalidaException jie) {
            Assert.fail("Exception nao deveria ser lancada");

        } catch (DesvirarCartaDuasVezesSeguidasException jie) {
            Assert.fail("A posicao da Carta fornecida nao pode ser desvirada duas vezes seguidas");
        }
    }
}

