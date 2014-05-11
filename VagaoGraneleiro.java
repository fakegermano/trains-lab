
/**
 * Classe que representa um vagão para carregar granéis.
 */
public class VagaoGraneleiro extends Vagao {
	
	/**
	 * Variável usada para representar a capacidade do vagão
	 */
	private int capacidade;
	/**
	 * Variavel usada para repŕesentar a quantidade de carga que o vagao esta carregando
	 */
	private int cargaAtual;
	/**
	 * construtor que inicializa as variaveis internas
	 * @param codigo
	 * @param capacidade
	 */
	public VagaoGraneleiro(String codigo, int capacidade) {
		super(codigo);
		this.capacidade = capacidade;
		this.cargaAtual = 0;
	}
	/**
	 * Metodo get para a capacidade do vagao
	 */
	public int getCapacidade() {
		return this.capacidade;
	}

	/**
	 * Retorna a representação deste vagão como String.
	 */
	public String toString() {
		return "G|" + getCodigo() + "|" + getCapacidade() + "|" + quantidadeCarregada();
	}

	@Override
	public boolean carregarCarga(int quantidade) {
		if (this.carregado() || this.getCapacidade() < quantidade)
			return false;
		this.cargaAtual += quantidade;
		return true;
	}

	@Override
	public boolean carregado() {
		if (this.quantidadeCarregada() > 0)
			return true;
		return false;
	}

	@Override
	public int quantidadeCarregada() {
		return this.cargaAtual;
	}

}
