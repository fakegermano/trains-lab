

/**
 * Classe abstrata que representa um vagão
 *
 */
public abstract class Vagao extends Carro implements Carregavel {

	/**
	 * Construtor da classe.
	 * @param codigo Código do vagão 
	 */
	public Vagao(String codigo) {
		super(codigo);
	}
	
	/**
	 * Use para obter a capacidade do vagão.
	 * @return Capacidade do vagão.
	 */
	public abstract int getCapacidade ();
}
