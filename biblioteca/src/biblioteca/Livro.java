package biblioteca;

public class Livro {

	private String ISBN;
	
	public Livro(String string) {
		this.setISBN(string);
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

}
