

/**
 * Classe que representa um carro do tipo locomotiva.
 */
public class Locomotiva extends Carro {

	/**
	 * Tipos de motor da locomotiva
	 */
	public enum Motor {ELETRICO, VAPOR, DIESEL}
	
	/**
	 * Variável usada para representar o tipo de motor desta locomotiva
	 */
	private Motor motor;
	/**
	 * Contrutor que chama o construtor da superclasse para setar alguns atributos
	 * e tambem seta os atributos da classe atual
	 * @param codigo
	 * @param motor
	 */
	public Locomotiva(String codigo, Motor motor) {
		super(codigo);
		this.motor = motor;
	}

	/**
	 * Retorna a representação desta locomotiva como String.
	 */
	@SuppressWarnings("incomplete-switch")
	public String toString() {
		switch (motor) {
			case ELETRICO:
				return getCodigo() + "|" + "E";
			case VAPOR:
				return getCodigo() + "|" + "V";
		}
		return getCodigo() + "|" + "D";
	}

	@Override
	public int compareTo(Carro o) {
	    final int BEFORE = -1;
	    
	    if (o instanceof Locomotiva) 
		return this.getCodigo().compareTo(o.getCodigo());
	    else return BEFORE;
	}
	


}
