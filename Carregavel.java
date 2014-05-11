
/**
 * Representa um item que pode ser carregado com uma carga
 */
public interface Carregavel {

	/**
	 * Deve carregar o classe implementada com a quantidade
	 * de carga indicada. Só deve carregar se já não houver outra carga carregada.
	 * Se quantidade de carga exceder capacidade, não deve carregar. 
	 * @param quantidade Quantidade de carga a ser carregada.
	 * @return True se foi possível carregar, false caso contrário.
	 */
	public boolean carregarCarga(int quantidade);
	
	/**
	 * Indica se o item em questão está carregado ou não. Quando um vagão novo
	 * é criado, ele deve ser criado vazio.
	 * @return True se está carregado, false caso contrário.
	 */
	public boolean carregado();
	
	/**
	 * Retorna a quantidade de carga carregada.
	 * @return Quantidade de carga carregada. Se não houver carga presente, retornar zero.
	 */
	public int quantidadeCarregada();
}
