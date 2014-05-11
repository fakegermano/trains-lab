
/**
 * Classe que representa um vagão para carregar líquidos.
 */
public class VagaoLiquidos extends Vagao {
	
	/**
	 * Variável usada para representar a capacidade do vagão
	 */
	private int capacidade;
	private ConteudoLiquido[] conteudos;
	
	/**
	 * Construtor que chama o contrutor da superclasse para setar alguns atributos, 
	 * seta alguns atributos locais, e toma uma série de parametros
	 * para registrar em um atributo do tipo vetor
	 * @param codigo
	 * @param capacidade
	 * @param conteudos
	 */
	public VagaoLiquidos(String codigo, int capacidade, ConteudoLiquido... conteudos) {
		super(codigo);
		this.capacidade = capacidade;
		this.conteudos = conteudos;
	}
	/**
	 * Método que retorna a capacidade liquida do vagao
	 * @return int capacidade do vagao
	 */
	public int getCapacidade() {
		return this.capacidade;
	}

	/**
	 * Retorna a representação deste vagão como String.
	 */
	public String toString() {
		String retorno = "L|" + getCodigo();
		retorno += conteudosAceitos();
		if (inflamavel())
			retorno += "I";
		else
			retorno += "N";
		return retorno + "|" + getCapacidade();
	}
	/**
	 * Returna uma representação em string dos conteudos aceitos, que segue o formato
	 * '|conteudo1|conteudo2|coteudo3|...|conteudon|'
	 * @return String no formato especificado
	 */
	private String conteudosAceitos() {
		String output = "";
		for (ConteudoLiquido conteudo : conteudos) {
			output += "|" + ConteudoLiquido.stringFormat(conteudo);
		}
		output += "|";
		return output;
	}
	/**
	 * Avalia se ha algun conteudo inflamavel nos conteudos liquidos do vagao
	 * @return boolean true se ha conteudo inflamavel
	 */
	public boolean inflamavel() {
		for (ConteudoLiquido conteudo : conteudos) {
			if (ConteudoLiquido.inflamavel(conteudo)) {
				return true;
			}
		}
		return false;
	}
	

}
