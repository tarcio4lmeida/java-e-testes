package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	
	private Avaliador leiloeiro;
	
	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
        System.out.println("inicializando teste!");

	}

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
		leiloeiro.avalia(leilao);

		// parte 3 : validacao
		double maiorEsperado = 400;
		double menorEsperado = 250;

		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001); // imprime 400.0
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001); // imprime 400.0

	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario joao = new Usuario("Joao");
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 1000.0));

		leiloeiro.avalia(leilao);

		Assert.assertEquals(1000, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(1000, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEntenderLeilaoCujoLancesForamAleatorios() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Usuario tarcio = new Usuario("Tarcio");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 450.0));
		leilao.propoe(new Lance(joao, 120.0));
		leilao.propoe(new Lance(maria, 700.0));
		leilao.propoe(new Lance(tarcio, 630.0));
		leilao.propoe(new Lance(tarcio, 700.0));

		leiloeiro.avalia(leilao);

		assertEquals(700.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEntenderTesteEmOrdemDecrescenteDoAvaliador() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Usuario tarcio = new Usuario("Tarcio");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 400.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(tarcio, 100.0));

		leiloeiro.avalia(leilao);

		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(100.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");

		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(steveJobs, 3000));

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario billGates = new Usuario("Bill Gates");

		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(billGates, 3000));
		leilao.propoe(new Lance(steveJobs, 3000));
		leilao.propoe(new Lance(billGates, 3000));
		leilao.propoe(new Lance(steveJobs, 4000));
		leilao.propoe(new Lance(billGates, 3000));
		leilao.propoe(new Lance(steveJobs, 5000));
		leilao.propoe(new Lance(billGates, 3000));
		leilao.propoe(new Lance(steveJobs, 6000));
		leilao.propoe(new Lance(billGates, 999));
		leilao.propoe(new Lance(steveJobs, 7000));

		assertEquals(10, leilao.getLances().size());
		int ultimo = leilao.getLances().size() - 1;
		assertEquals(999, leilao.getLances().get(ultimo).getValor(), 0.00001);
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