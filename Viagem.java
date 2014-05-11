

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
	 * Adiciona uma garga de graneis à viagem
	 * @param quantidade
	 * @param destino
	 * @throws TremException
	 */
	public void adicaoCargaGraneis (int quantidade, String destino) throws TremException{
		Vagao[] vagoes = composicao.carregarGraneis(quantidade);
		if (vagoes == null)
			throw new TremException("Impossivel carregar graneis", TremException.CARREGAMENTO_INVALIDO);
		for (Vagao vagao : vagoes) {
			entregas.get(destino).add(vagao);
		}
	}
	
	/**
	 * Adiciona uma carga liquida na viagem
	 * @param quantidade
	 * @param conteudo
	 * @param destino
	 * @throws TremException
	 */
	public void adicaoCargaLiquida (int quantidade, ConteudoLiquido conteudo,
			String destino) throws TremException {
		Vagao[] vagoes = composicao.carregarLiquido(quantidade, conteudo);
		if (vagoes == null)
			throw new TremException("Impossivel carregar liquido", TremException.CARREGAMENTO_INVALIDO);
		for (Vagao vagao : vagoes) {
			entregas.get(destino).add(vagao);
		}
	}
	
	/**
	 * Inicia a viagem caso a composicao seja valida
	 * @throws TremException
	 */
	public void iniciaViagem() throws TremException{
		if (composicao.valida()) {
			cidadeAtual = 0;
		} else {
			throw new TremException("Impossivel iniciar viagem - composicao invalida", TremException.COMPOSICAO_INVALIDA);
		}
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
