

/**
 * Enumeração que representa o tipo de conteúdo de um vagão
 * de líquidos.
 *
 */
public enum ConteudoLiquido {
	/* Tipos de conteúdo */
	AGUA, PETROLEO, ALCOOL;
	
	/**
	 * Use este método para saber se algum tipo de conteúdo é inflamável.
	 * @param conteudo Tipo de conteúdo
	 * @return True se o conteúdo for inflamável, falso caso contrário.
	 */
	@SuppressWarnings("incomplete-switch")
	public static boolean inflamavel(ConteudoLiquido conteudo) {
		switch (conteudo) {
			case PETROLEO: return true;
			case ALCOOL: return true;
		}
		return false;
	}
	
	/**
	 * Use este método para obter uma String representando um tipo de conteúdo líquido.
	 * @param conteudo Tipo de conteúdo
	 * @return Representação string do tipo de conteúdo.
	 */
	public static String stringFormat(ConteudoLiquido conteudo) {
		switch (conteudo) {
			case PETROLEO: return "PE";
			case ALCOOL: return "AL";
			case AGUA: return "AG";
	}
	return null;
	}
}
