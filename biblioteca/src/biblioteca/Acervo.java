package biblioteca;

import java.util.ArrayList;
import java.util.List;

import excecoes.LivroNaoExistenteException;
import excecoes.LivroVazioException;

public class Acervo {
	
	List<Livro> livros = new ArrayList<Livro>();

	public void cadastrar(Livro livro) {
		if (livro == null) {
			throw new LivroVazioException();
		}
		livros.add(livro);
	}

	public int getQuantidadeLivros() {
		return livros.size();
	}

	public List<Livro> pesquisar(String titulo) {
		List<Livro> retorno = new ArrayList<Livro>();
		for(Livro livro : livros){
			if(livro.getTitulo().contains(titulo)){
				retorno.add(livro);
			}
		}
		return retorno;
	}

	public Livro consultarPorISBN(String ISBN) throws LivroNaoExistenteException {
		for(Livro livro : livros){
			if(livro.getISBN().equals(ISBN)){
				return livro;
			}
		}
		throw new LivroNaoExistenteException();
	}
	
	

}
