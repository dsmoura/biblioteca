package biblioteca;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import excecoes.LivroNaoExistenteException;
import excecoes.LivroVazioException;

public class CadastroLivroTest {
	
	private Acervo acervo;
	
	@Before
	public void preparaAcervo() {
		acervo = new Acervo();
	}

	@Test
	public void deveConterAcervoVazio() {
		assertEquals(0, acervo.getQuantidadeLivros());
	}
	
	@Test
	public void deveCadastrarUmLivro() {
		cadastrarLivro("8172631208973", "Clean Code");
		assertEquals(1, acervo.getQuantidadeLivros());
	}
	
	@Test
	public void deveCadastrarDoisLivros() {
		cadastrarLivro("8172631208973", "Clean Code");
		cadastrarLivro("2983741234234", "JavaEE for Dummies");
		assertEquals(2, acervo.getQuantidadeLivros());
	}

	@Test(expected=LivroVazioException.class)
	public void deveImpedirCadastrarLivroVazio() {
		acervo.cadastrar(null);
	}
	
	@Test
	public void deveEncontrarNenhumLivroNoAcervo() {
		List<Livro> livrosEncontrados = acervo.pesquisar("");
		assertEquals(livrosEncontrados.size(), 0);
	}
	
	@Test
	public void deveEncontrarLivroNoAcervo() {
		cadastrarLivro("12345", "TDD By Example");
		cadastrarLivro("123456", "Harry Potter");
		cadastrarLivro("1234567", "Senhor dos Anéis");
		
		List<Livro> livrosEncontrados = acervo.pesquisar("TDD By Example");
		assertEquals(1, livrosEncontrados.size());
		assertEquals("TDD By Example", livrosEncontrados.get(0).getTitulo());
	}
	
	@Test
	public void deveEncontrarLivrosNoAcertoPorTrechoDoTitulo() {
		cadastrarLivro("12345", "TDD By Example");
		cadastrarLivro("123456", "TDD na pratica");
		cadastrarLivro("1234567", "Senhor dos Anéis");
		
		List<Livro> livrosEncontrados = acervo.pesquisar("TDD");
		assertEquals(2, livrosEncontrados.size());
		assertEquals("TDD By Example", livrosEncontrados.get(0).getTitulo());
		assertEquals("TDD na pratica", livrosEncontrados.get(1).getTitulo());
	}
	
	@Test
	public void deveConsultarLivroPorISBN() throws LivroNaoExistenteException {
		cadastrarLivro("283764", "Clean Code");
		cadastrarLivro("12345", "TDD By Example");
		Livro livroEncontrado = acervo.consultarPorISBN("12345");
		assertEquals("TDD By Example", livroEncontrado.getTitulo());
	}
		
	@Test(expected = LivroNaoExistenteException.class)
	public void deveRetornarErroAoConsultarLivroNaoExistentePorISBN() throws LivroNaoExistenteException{
		acervo.consultarPorISBN("54321");
	}
	
	private void cadastrarLivro(String ISBN, String titulo) {
		Livro livro = new Livro(ISBN, titulo);
		acervo.cadastrar(livro);
	}
}
