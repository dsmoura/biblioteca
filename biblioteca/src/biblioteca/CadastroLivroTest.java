package biblioteca;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excecoes.LivroVazioException;

public class CadastroLivroTest {
	
	private Acervo acervo = new Acervo();
	
	@Before
	public void preparaAcervo() {
		acervo = new Acervo();
	}

	@Test
	public void acervoVazio() {
		assertEquals(0, acervo.getQuantidadeLivros());
	}
	
	@Test
	public void cadastraUmLivro() {
		Livro livro = new Livro("8172631208973");
		acervo.cadastra(livro);
		assertEquals(1, acervo.getQuantidadeLivros());
	}
	
	@Test
	public void cadastraDoisLivros() {
		Livro livro = new Livro("8172631208973");
		acervo.cadastra(livro);
		livro = new Livro("2983741234234");
		acervo.cadastra(livro);
		assertEquals(2, acervo.getQuantidadeLivros());
	}
	
	@Test(expected=LivroVazioException.class)
	public void cadastraLivroVazio() {
		acervo.cadastra(null);
	}
	
	@Test
	public void pesquisarLivroNaoExistente() {
		
		Set<Livro> retornoDePesquisa = acervo.pesquisar("");
		
		assertEquals(retornoDePesquisa.size(), 0);
	}
	
//	@Test
//	public void pesquisarLivroExistente() {
//		
//		Set<Livro> retornoDePesquisa = acervo.pesquisar("1234");
//		
//		assertTrue(retornoDePesquisa.size()>0);
//	}
	
	
}
