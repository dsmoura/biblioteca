package biblioteca;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

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
	
	@Test
	public void devePagarTodosEmprestimosAtrasado() {
		
		ServicoDeposito servicoDeposito = mock(ServicoDeposito.class);
		when(servicoDeposito.debita(anyDouble())).thenReturn(true);
		
		Aluno alunoMock = mock(Aluno.class);
		
		List<Emprestimo> historicoEmprestimos2 = new ArrayList<Emprestimo>();
		historicoEmprestimos2.add(new Emprestimo(false, 5));
		historicoEmprestimos2.add(new Emprestimo(false, 10));
		historicoEmprestimos2.add(new Emprestimo(true, 8));
		when(alunoMock.getHistoricoEmprestimos()).thenReturn(historicoEmprestimos2);
		
		for (Emprestimo e : alunoMock.getHistoricoEmprestimos()) {
			e.realizarPagamento(servicoDeposito);
			assertTrue(e.isPago());
		}
		
		verify(servicoDeposito, times(2)).debita(anyDouble());
	}
	
	private void cadastrarLivro(String ISBN, String titulo) {
		Livro livro = new Livro(ISBN, titulo);
		acervo.cadastrar(livro);
	}
	
	@Test
	public void validaPagamento2() {
		
		List<String> mockedList = mock(List.class);
	
		// using mock object - it does not throw any "unexpected interaction" exception
		mockedList.add("one");
		mockedList.clear();
	
		// selective, explicit, highly readable verification
		verify(mockedList).add("one");		//verifica se foi chamado
		verify(mockedList).clear();
		
		
		
		//Stubbing
		
		 //You can mock concrete classes, not only interfaces
		 LinkedList<String> mockedList3 = mock(LinkedList.class);

		 //stubbing
		 when(mockedList3.get(0)).thenReturn("first");
		 when(mockedList3.get(1)).thenThrow(new RuntimeException());

		 //following prints "first"
		 System.out.println(mockedList3.get(0));

		 //following prints "null" because get(999) was not stubbed
		 System.out.println(mockedList3.get(999));

		 //Although it is possible to verify a stubbed invocation, usually it's just redundant
		 //If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
		 //If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
		 verify(mockedList3).get(0);

		 //following throws runtime exception
//		 System.out.println(mockedList3.get(1));
		 

		 
		 //Argument matchers
		 
		 
		//stubbing using built-in anyInt() argument matcher
		 when(mockedList.get(anyInt())).thenReturn("element");

		 //following prints "element"
		 System.out.println(mockedList.get(999));

		 //you can also verify using an argument matcher
		 verify(mockedList).get(anyInt());
		 
		 
		 
		 // Verifying exact number of invocations / at least x / never
		 
		 //using mock
		 mockedList.add("once");

		 mockedList.add("twice");
		 mockedList.add("twice");

		 mockedList.add("three times");
		 mockedList.add("three times");
		 mockedList.add("three times");

		 //following two verifications work exactly the same - times(1) is used by default
		 verify(mockedList).add("once");
		 verify(mockedList, times(1)).add("once");

		 //exact number of invocations verification
		 verify(mockedList, times(2)).add("twice");
		 verify(mockedList, times(3)).add("three times");

		 //verification using never(). never() is an alias to times(0)
		 verify(mockedList, never()).add("never happened");

		 //verification using atLeast()/atMost()
		 verify(mockedList, atLeastOnce()).add("three times");
		 verify(mockedList, atLeast(0)).add("five times");
		 verify(mockedList, atMost(5)).add("three times");
		 
		 
		 verifyNoMoreInteractions(mockedList);
		 
		 
		 doThrow(new RuntimeException()).when(mockedList).clear();

		   //following throws RuntimeException:
		 try {
			 mockedList.clear();
		} catch (RuntimeException e) {
			System.out.println("doThrow() works");
		}  
		 
		 
		 
		 //Spying on real objects
		 
		   List<String> list = new LinkedList<String>();
		   list.add(":D");
		   List<String> spy = spy(list);

		   //optionally, you can stub out some methods:
		   when(spy.size()).thenReturn(100);

		   //using the spy calls *real* methods
		   spy.add("one");
		   spy.add("two");

		   //prints "one" - the first element of a list
		   System.out.println(spy);
		   System.out.println(list);

		   //size() method was stubbed - 100 is printed
		   System.out.println("spy size = " + spy.size());
		   System.out.println("list size = " +list.size());

		   //optionally, you can verify
		   verify(spy).add("one");
		   verify(spy).add("two");
		   
		   
		   //reinit
		   reset(mockedList);
		   
		   
		   System.out.println("isMock()? " + Mockito.mockingDetails(mockedList).isMock());
		   System.out.println("isSpy()? " + Mockito.mockingDetails(mockedList).isSpy());

		   
		   
		   //bdd
		   BDDMockito.given(mockedList.size()).willReturn(2);
		   
		   BDDMockito.then(mockedList).should(times(2));
		   
	}
	
}
