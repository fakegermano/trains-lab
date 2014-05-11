
/**
 * Classe que representa um vagão para carregar granéis.
 */
public class VagaoGraneleiro extends Vagao {
	
	/**
	 * Variável usada para representar a capacidade do vagão
	 */
	private int capacidade;
	/**
	 * Construtor que chama o construtor da superclasse para setar alguns atributos
	 * e tambem seta alguns atributos da classe atual
	 * @param codigo
	 * @param capacidade
	 */
	public VagaoGraneleiro(String codigo, int capacidade) {
		super(codigo);
		this.capacidade = capacidade;
	}
	/**
	 * Retorna a capacidade de solidos do vagao atual
	 * @return int capacidade do vagao
	 */
	public int getCapacidade() {
		return this.capacidade;
	}

	/**
	 * Retorna a representação deste vagão como String.
	 */
	public String toString() {
		return "G|" + getCodigo() + "|" + getCapacidade();
	}

}
