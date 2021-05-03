package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.matematica_maluca.MatematicaMaluca;

public class TesteDaMatematica {

	@Test
	public void numeroDeveSerMaiorque30() {
		int numero = 31;
		int retornoEsperado = 124;
		
		assertEquals(retornoEsperado, MatematicaMaluca.contaMaluca(numero));
	}
	
	
	@Test
	public void numeroDeveSerMaiorQue30MenorQue10() {
		int numero = 15;
		int retornoEsperado = 45;
		
		assertEquals(retornoEsperado, MatematicaMaluca.contaMaluca(numero));
	}
	
	@Test
	public void numeroDeveSerMenorQue101() {
		int numero = 10;
		int retornoEsperado = 20;
		
		assertEquals(retornoEsperado, MatematicaMaluca.contaMaluca(numero));
	}
	
	
	@Test
	public void numeroDeveSerMenorQue10() {}
	
}
