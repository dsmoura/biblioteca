package biblioteca;

public class Livro {

	private String ISBN;
	private String titulo;
	
	public Livro(String ISBN, String titulo) {
		this.setISBN(ISBN);
		this.setTitulo(titulo);
	}
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
