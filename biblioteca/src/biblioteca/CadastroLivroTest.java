package biblioteca;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import excecoes.LivroNaoExistenteException;
import excecoes.LivroVazioException;

public class CadastroLivroTest {
	
	private Acervo acervo = new Acervo();
	
	@Before
	public void preparaAcervo() {
		acervo = new Acervo();
	}

	@Test
	public void verificarAcervoVazio() {
		assertEquals(0, acervo.getQuantidadeLivros());
	}
	
	@Test
	public void cadastraUmLivro() {
		Livro livro = new Livro("8172631208973", "Clean Code");
		cadastrarLivro(livro);
		assertEquals(1, acervo.getQuantidadeLivros());
	}
	
	@Test
	public void cadastraDoisLivros() {
		Livro livro = new Livro("8172631208973", "Clean Code");
		cadastrarLivro(livro);
		livro = new Livro("2983741234234", "JavaEE for Dummies");
		cadastrarLivro(livro);
		assertEquals(2, acervo.getQuantidadeLivros());
	}

	private void cadastrarLivro(Livro livro) {
		acervo.cadastrar(livro);
	}
	
	@Test(expected=LivroVazioException.class)
	public void cadastraLivroVazio() {
		acervo.cadastrar(null);
	}
	
	@Test
	public void pesquisarLivroNaoExistente() {
		List<Livro> retornoDePesquisa = acervo.pesquisar("");
		assertEquals(retornoDePesquisa.size(), 0);
	}
	
	@Test
	public void pesquisarLivroExistentePorTitulo() {
		Livro livro = new Livro("12345", "TDD By Example");
		cadastrarLivro(livro);
		List<Livro> retornoDePesquisa = acervo.pesquisar("TDD By Example");
		assertEquals(1, retornoDePesquisa.size());
	}
	
	@Test
	public void pesquisarLivroEmAcervoComTresLivros() {
		Livro livro = new Livro("12345", "TDD By Example");
		cadastrarLivro(livro);
		livro = new Livro("123456", "Harry Potter");
		cadastrarLivro(livro);
		livro = new Livro("1234567", "Senhor dos Anéis");
		cadastrarLivro(livro);
		
		List<Livro> retornoDePesquisa = acervo.pesquisar("TDD By Example");
		assertEquals(1, retornoDePesquisa.size());
		assertEquals("TDD By Example", retornoDePesquisa.get(0).getTitulo());
	}
	
	@Test
	public void pesquisarLivrosPorTrechoDoTituloEmAcervoComTresLivros() {
		Livro livro = new Livro("12345", "TDD By Example");
		cadastrarLivro(livro);
		livro = new Livro("123456", "TDD na pratica");
		cadastrarLivro(livro);
		livro = new Livro("1234567", "Senhor dos Anéis");
		cadastrarLivro(livro);
		
		List<Livro> retornoDePesquisa = acervo.pesquisar("TDD");
		assertEquals(2, retornoDePesquisa.size());
		assertEquals("TDD By Example", retornoDePesquisa.get(0).getTitulo());
		assertEquals("TDD na pratica", retornoDePesquisa.get(1).getTitulo());
	}
	
	@Test
	public void consultarLivroExistentePorISBN() throws LivroNaoExistenteException {
		Livro livro = new Livro("12345", "TDD By Example");
		cadastrarLivro(livro);
		Livro retornoDePesquisa = acervo.consultarPorISBN("12345");
		assertEquals(livro, retornoDePesquisa);
	}
		
	@Test(expected = LivroNaoExistenteException.class)
	public void consultarLivroNaoExistentePorISBN() throws LivroNaoExistenteException{
		acervo.consultarPorISBN("54321");
	}
}
