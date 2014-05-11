
import java.util.ArrayList;

/**
 * Classe que representa um vagão para carregar líquidos.
 */
public class VagaoLiquidos extends Vagao {
	
	
	/**
	 * Representa conteúdo sendo usado atualmente.
	 */
	private ConteudoLiquido conteudo;
	/**
	 * ArrayList que representa os conteudos que o vagao aceita
	 */
	private ArrayList<ConteudoLiquido> conteudos;
	/**
	 * Variável usada para representar a capacidade do vagão
	 */
	private int capacidade;
	/**
	 * Usada para representar a quantidade de carga que esta sendo usada atualmente
	 */
	private int cargaAtual;
	/**
	 * Inicializa as variaveis internas da classe e da superclasse
	 * @param codigo
	 * @param capacidade
	 * @param conteudos
	 */
	public VagaoLiquidos(String codigo, int capacidade, ConteudoLiquido... conteudos) {
		super(codigo);
		this.capacidade = capacidade;
		this.conteudos = new ArrayList<ConteudoLiquido>();
		for (ConteudoLiquido conteudo : conteudos) {
			this.conteudos.add(conteudo);
		}
		this.conteudo = this.conteudos.get(0);
		this.cargaAtual = 0;
	}
	/**
	 * metodo get para a capacidade do vagao
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
		return retorno + "|" + getCapacidade() + "|" + ConteudoLiquido.stringFormat(conteudo) + "|" + quantidadeCarregada();
	}
	
	/**
	 * Retorna uma representação em string de todos os conteudos que este vagao pode aceitar
	 * @return
	 */
	private String conteudosAceitos() {
		String output = "|";
		for (ConteudoLiquido conteudo : this.conteudos) {
			output += ConteudoLiquido.stringFormat(conteudo) + "|";
		}
		return output;
	}

	/**
	 * retorna se o conteudo que esta sendo guardado atualmente eh inflamavel
	 * @return
	 */
	public boolean inflamavel() {
		if (ConteudoLiquido.inflamavel(this.conteudo)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean carregarCarga(int quantidade) {
		if (this.carregado()) {
			return false;
		} 
		if (this.getCapacidade() < quantidade) {
			return false;
		}
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
	/**
	 * altera o conteudo atual do vagao
	 * @param conteudo
	 */
	public void setConteudoUsado(ConteudoLiquido conteudo) {
		this.conteudo = conteudo;
	}

	/**
	 * verifica se o vagao pode aceitar o conteudo dado no parametro
	 * @param conteudo
	 * @return
	 */
	public boolean aceitaConteudo(ConteudoLiquido conteudo) {
		for (ConteudoLiquido conteudoPossivel : conteudos) {
			if (conteudoPossivel == conteudo) {
				return true;
			}
		}
		return false;
	}
}
