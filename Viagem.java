
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe que representa uma viagem
 *
 */
public class Viagem {

	/**
	 * Composição usada na viagem
	 */
	private Composicao composicao;
	
	/**
	 * Lista de cidades visitadas na viagem
	 */
	private ArrayList<String> cidades;
	
	/**
	 * Mapa com as cidades e os vagões que devem ser retirados da composição
	 */
	private HashMap<String, ArrayList<Vagao>> entregas;

	/**
	 * Índice que representa a viagem atual.
	 */
	private int cidadeAtual;
	
	/**
	 * Construtor padrão, recebe a composição
	 * @param composicao Composição usada na viagem
	 */
	public Viagem(Composicao composicao) {
		this.composicao = composicao;
		cidades = new ArrayList<String>();
		entregas = new HashMap<String, ArrayList<Vagao>>();
		cidadeAtual = -1;
	}
	
	/**
	 * Adiciona uma nova cidade na rota, sempre no final
	 * @param destino Destino final da viagem
	 */
	public void adicionarDestino (String destino) {
		cidades.add(destino);
		entregas.put(destino, new ArrayList<Vagao>());
	}
	
	/**
	 * Adiciona uma carga do tipo granéis a ser transportada na viagem.
	 * @param quantidade Quantidade de carga
	 * @param destino Cidade de destino da carga
	 * @return True se foi possível carregar a carga, false caso contrário.
	 */
	public boolean adicaoCargaGraneis (int quantidade, String destino) {
		Vagao[] vagoes = composicao.carregarGraneis(quantidade);
		if (vagoes == null)
			return false;
		for (Vagao vagao : vagoes) {
			entregas.get(destino).add(vagao);
		}
		return true;
	}
	
	/**
	 * Adiciona uma carga líquida a ser transportada na viagem.
	 * @param quantidade Quantidade de carga
	 * @param conteudo Cidade de destino da carga
	 * @param destino Cidade destino desta carga
	 * @return True se foi possível carregar a carga, false caso contrário.
	 */
	public boolean adicaoCargaLiquida (int quantidade, ConteudoLiquido conteudo,
			String destino) {
		Vagao[] vagoes = composicao.carregarLiquido(quantidade, conteudo);
		if (vagoes == null)
			return false;
		for (Vagao vagao : vagoes) {
			entregas.get(destino).add(vagao);
		}
		return true;
	}
	
	/**
	 * Inicia viagem colocando locomotiva na primeira cidade. Só permite iniciar viagem
	 * se a composição for válida.
	 * @return True se viagem iniciou, false caso contrário.
	 */
	public boolean iniciaViagem() {
		if (composicao.valida()) {
			cidadeAtual = 0;
			return true;
		}
		return false;
	}
	
	/**
	 * Avança a cidade atual da viagem. Só avança se viagem já iniciou.
	 * @return True se viagem avançou, false caso contrário.
	 */
	public boolean avancaCidade() {
		if (cidadeAtual == -1)
			return false;
		cidadeAtual++;
		ArrayList<Vagao> descarrego = entregas.get(cidades.get(cidadeAtual));
		for (Vagao vagao : descarrego) {
			composicao.removeCarro(vagao);
		}
		return true;
	}
	
	public String toString() {
		String retorno = "";
		
		for (String cidade : cidades) {
			retorno = retorno + cidade + "|";
			for (Vagao vagao : entregas.get(cidade)) {
				retorno = retorno + vagao.getCodigo() + "|";
			}
			retorno = retorno + "\n";
		}

		if (cidadeAtual != -1)
			retorno = retorno + "Cidade atual: " + cidades.get(cidadeAtual) + "\n";		
		else
			retorno = retorno + "Viagem nao iniciada\n";		

		return retorno;
	}
}
