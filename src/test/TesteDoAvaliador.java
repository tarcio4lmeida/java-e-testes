package test;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(maria, 250.0));

		// parte 3 : acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// parte 3 : validacao
		double maiorEsperado = 400;
		double menorEsperado = 250;

		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001); // imprime 400.0
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001); // imprime 400.0

	}

	@Test
	public void deveCalculcarMedia() {
		// cenario: 3 lances em ordem crescente
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 500.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		Assert.assertEquals(400, leiloeiro.getMedia(), 0.0001);
	}

	@Test
	public void testaMediaDeZeroLance() {
		// cenario
		Usuario ewertom = new Usuario("Ewertom");

		// acao
		Leilao leilao = new Leilao("Iphone 7");

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		// validacao
		Assert.assertEquals(0, avaliador.getMedia(), 0.0001);

	}
}

//Testes automatizados de unidade nos trazem diversas vantagens.
//
//Nesse momento, as vantagens que são fáceis de ver:
//
//1) Um teste de unidade executa muito rápido. Veja que ele gasta apenas alguns milissegundos para ser executado. Imagine o tempo que um humano levaria.
//
//2) Um humano pode eventualmente executar um teste incorreto (montar o cenário ou validar a saída errada, por exemplo). A máquina nunca fará isso. A partir do momento que você escreveu o teste, ela sempre vai executar o mesmo teste.
//
//3) É muito mais divertido escrever um teste automatizado do que testar manualmente.