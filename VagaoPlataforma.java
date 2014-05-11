

public class VagaoPlataforma extends Vagao {
	
	private int capacidade;
	
	private int cargaAtual;
	
	private String tipoCarga;
	
	public VagaoPlataforma(String codigo, int capacidade, String tipoCarga) {
		super(codigo);
		this.capacidade = capacidade;
		this.tipoCarga = tipoCarga;
		this.cargaAtual = 0;
	}

	public String getTipoCarga() {
		return this.tipoCarga;
	}
	@Override
	public boolean carregarCarga(int quantidade) {
		if (this.carregado()) {
			return false;
		} else if (this.getCapacidade() < quantidade) {
			return false;
		} else {
			this.cargaAtual += quantidade;
			return true;
		}
	}

	@Override
	public boolean carregado() {
		if (this.quantidadeCarregada() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int quantidadeCarregada() {
		return this.cargaAtual;
	}

	@Override
	public int getCapacidade() {
		return this.capacidade;
	}

	@Override
	public String toString() {
		return "P|"+ this.getCodigo() + "|" + 
				this.getCapacidade() + "|" + 
				this.quantidadeCarregada() + "|" + 
				this.getTipoCarga();
	}

}
