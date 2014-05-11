
import java.util.ArrayList;

/**
 * Classe que representa uma composição ferroviária.
 * Uma composição é um conjunto de locomotiva(s) e
 * vagões conectados para para fazer uma viagem.
 *
 */
public class Composicao {

	/**
	 * Variável usada para representar os carros desta composição
	 */
	private ArrayList<Carro> carros;
	
	/**
	 * Variável usada para armazenar o código
	 */
	private String codigo;
	
	/**
	 * Construtor que inicializa as variaveis internas
	 * @param codigo
	 */
	public Composicao(String codigo) {
		this.codigo = codigo;
		this.carros = new ArrayList<Carro>();
	}
	
	/**
	 * Retorna o tamanho da composição
	 * @return int numero de carros na composição
	 */
	public int tamanhoComposicao() {
		return carros.size();
	}
	/**
	 * Retorna o carro na posição indicada no parametro
	 * @param carro
	 * @return Carro carro que estava no indice
	 */
	public Carro getCarro(int carro) {
		return carros.get(carro);
	}
	/**
	 * Adiciona o carro passado em parametro na composição atual
	 * @param carro
	 */
	public void adicionaCarro(Carro carro) {
		if (carro.getClass().equals(Locomotiva.class)) {
			carros.add(0, carro);
		} else {
			carros.add(carro);
		}
	}
	
	/**
	 * Avalia se a composição é válida, ou seja, possui pelo menos uma locomotiva
	 * @return boolean true se o primeiro carro eh locomotiva
	 */
	public boolean valida () {
		if (carros.size() == 0) 
			return false;
		if (carros.get(0).getClass().equals(Locomotiva.class)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Retorna a soma das capacidades dos vagoes de liquidos na composição
	 * @return double soma das capacidades dos vagoes de liquidos
	 */
	public double capacidadeTotalLiquidos() {
		double soma=0;
		for (Carro vagao : carros) {
			if (vagao.getClass().equals(VagaoLiquidos.class)) {
				soma += ((VagaoLiquidos)vagao).getCapacidade();
			}
		}
		return soma;
	}
	/**
	 * Retorna a soma das capacidades dos vagoes graneleiros na composição
	 * @return double soma das capacidades dos vagoes graneleiros
	 */
	public double capacidadeTotalGraneis() {
		double soma=0;
		for (Carro vagao : carros) {
			if (vagao.getClass().equals(VagaoGraneleiro.class)) {
				soma += ((VagaoGraneleiro)vagao).getCapacidade();
			}
		}
		return soma;
	}
	/**
	 * Retorna se a composição atual é imflamavel, ou seja, possui pelo menos um
	 * vagao de liquidos com liquidos inflamaveis
	 * @return boolean true para composicao inflamavel
	 */
	public boolean inflamavel () {
		for (Carro vagao : carros) {
			if (vagao.getClass().equals(VagaoLiquidos.class)) {
				if (((VagaoLiquidos)vagao).inflamavel() == true)
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Retorna a representação desta composição como String.
	 */
	public String toString() {
		String retorno = codigo + "|" + capacidadeTotalLiquidos() + "|" +
				capacidadeTotalGraneis() + "|";
		for (Carro carro : carros) {
			retorno = retorno + carro.getCodigo() + "|"; 
		}
		return retorno;
	}
}
