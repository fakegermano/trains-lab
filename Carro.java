

/**
 * Classe abstrata que representa um carro (vagão ou locomotiva)
 * ferroviário.
 *
 */
public abstract class Carro implements Comparable<Carro>{

	/**
	 * Código do carro
	 */
	private String codigo;
	
	/**
	 * Cria novo carro.
	 * @param codigo Código do carro
	 */
	public Carro (String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Adquire código do carro
	 * @return Código do carro.
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Retorna representação como String.
	 */
	public abstract String toString();
	
}
