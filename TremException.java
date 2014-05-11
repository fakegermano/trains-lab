
@SuppressWarnings("serial")
public class TremException extends Exception {

	/**
	 * Constantes que definem os tipos de erro
	 */
	public static final int COMPOSICAO_INVALIDA = 0;
	public static final int CARREGAMENTO_INVALIDO = 1;
	
	/**
	 * Variavel interna que guarda o tipo de excessao
	 */
	private int tipo;
	
	/**
	 * Construtor da classe
	 * @param mensagem
	 * @param tipo
	 */
	public TremException (String mensagem, int tipo) throws RuntimeException {
		super(mensagem);
		if (tipo == COMPOSICAO_INVALIDA || tipo == CARREGAMENTO_INVALIDO) {
			this.tipo = tipo;
		} else {
			throw new RuntimeException("Tipo invalido");
		}
	}
	
	/**
	 * get padrao para o tipo
	 * @return int tipo da exception
	 */
	public int getTipo() {
		return this.tipo; 
	}
}
