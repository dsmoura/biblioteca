package biblioteca;

public class ServicoDeposito implements ServicoDepositoInterface {

	@Override
	public Boolean debita(double valor) {
		throw new RuntimeException("Sem acesso ao servi�o de dep�sito.");
	}

}
