package biblioteca;

import java.util.HashSet;
import java.util.Set;

import excecoes.LivroVazioException;

public class Acervo {
	
	Set<Livro> livros = new HashSet<Livro>();

	public void cadastra(Livro livro) {
		if (livro == null) {
			throw new LivroVazioException();
		}
		livros.add(livro);
	}

	public int getQuantidadeLivros() {
		return livros.size();
	}

	public Set<Livro> pesquisar(String string) {
	
		return new HashSet<Livro>();
	}
	
	

}
