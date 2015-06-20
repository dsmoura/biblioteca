package biblioteca;

public class Emprestimo {

	private boolean pago;
	private double valor;

	public Emprestimo(boolean pago, double valor) {
		this.setPago(pago);
		this.setValor(valor);
	}

	public void realizarPagamento(ServicoDeposito servicoDeposito) {
		if (!this.isPago()) {
			this.pago = servicoDeposito.debita(this.valor);
		}
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
