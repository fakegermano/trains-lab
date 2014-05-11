

import java.util.ArrayList;
import java.util.Collections;

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
     * Construtor que inicializa as variaveis internas da classe e da superclasse
     * @param codigo
     */
    public Composicao(String codigo) {
	this.codigo = codigo;
	this.carros = new ArrayList<Carro>();
    }
    /**
     * Retorna o tamanho da composicao (numero de carros )
     * @return
     */
    public int tamanhoComposicao() {
	return this.carros.size();
    }
    /**
     * retorna o carro da posicao indicada
     * @param carro
     * @return
     */
    public Carro getCarro(int carro) {
	return this.carros.get(carro);
    }
	
    /**
     * adiciona um carro na composicao, se for uma locomotiva adiciona no começo
     * @param carro
     */
    public void adicionaCarro(Carro carro) {
	if (carro instanceof Locomotiva) {
	    this.carros.add(0, carro);
	} else {
	    this.carros.add(carro);
	}
    }
	
    /**
     * retorna se a composicao eh valida, ou seja, se possui uma locomotiva
     * @return
     */
    public boolean valida () {
	if (this.carros.size() == 0) {
	    return false;
	}
	if (this.getCarro(0) instanceof Locomotiva) {
	    return true;
	}
	return false;
    }
    /**
     * retorna a soma da capacidade de todos os vagoes de liquidos
     * @return
     */
    public double capacidadeTotalLiquidos() {
	int capacidade = 0;
	for (Carro carro : this.carros) {
	    if (carro instanceof VagaoLiquidos) {
		capacidade += ((Vagao)carro).getCapacidade();
	    }
	}
	return capacidade;
    }
	
    /**
     * retorna a soma da capacidade de todos os vagoes graneleiros
     * @return
     */
    public double capacidadeTotalGraneis() {
	int capacidade = 0;
	for (Carro carro : this.carros) {
	    if (carro instanceof VagaoGraneleiro) {
		capacidade += ((Vagao)carro).getCapacidade();
	    }
	}
	return capacidade;
    }
	
    /**
     * retorna se a composicao esta no estado inflamavel (esta carregando um produto inflamavel)
     * @return
     */
    public boolean inflamavel () {
	for (Carro carro : this.carros) {
	    if (carro instanceof VagaoLiquidos) {
		if (((VagaoLiquidos) carro).inflamavel()) {
		    return true;
		}
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

    /**
     * A funcao carregaGraneis tenta carregar uma certa quantidade de graneis em um dos vagoes da
     * composicao se falhar ela tenta distribuir na composicao, se nao houver capacidade a funcao nao carrega
     * os vagoes
     * @param quantidade
     * @return Vagao[] vetor de vagoes que sofreram o carregamento
     */
    public Vagao[] carregarGraneis(int quantidade) {
	ArrayList<VagaoGraneleiro> vagoes = new ArrayList<VagaoGraneleiro>();
		
	/*
	 *  Tenta colocar em apenas um vagao
	 */
	for (Carro carro : this.carros) {
	    if (carro instanceof VagaoGraneleiro) {
		if (((VagaoGraneleiro) carro).carregarCarga(quantidade)) {
		    vagoes.add((VagaoGraneleiro)carro);
		    break;
		}
	    }
	}
		
	/*
	 * conta a capacidade total (soma) dos vagoes vazios
	 */
	int capacidadeVazios = 0;
	for (Carro carro : this.carros) {
	    if (carro instanceof VagaoGraneleiro) {
		if (!((VagaoGraneleiro) carro).carregado()) {
		    capacidadeVazios += ((VagaoGraneleiro) carro).getCapacidade();
		}
	    }
	}
		
	/*
	 * caso nao conseguiu colocar em apenas um, tenta distribuir na composição
	 */
	if (vagoes.isEmpty()) {
	    int debito = -quantidade;
	    int i=0;
	    if (capacidadeVazios >= quantidade) {
		/*
		 * distribui a quantidade seguidamente na composicao
		 */
		while (debito < 0) {
		    Carro atual = this.carros.get(i++);
		    if (atual instanceof VagaoGraneleiro) {
			if ( (-1)*debito <= ((VagaoGraneleiro)atual).getCapacidade()) {
			    if (((VagaoGraneleiro)atual).carregarCarga((-1)*debito)) {
				vagoes.add((VagaoGraneleiro)atual);
				debito =0;
			    }
			} else {
			    if (((VagaoGraneleiro)atual).carregarCarga(((VagaoGraneleiro)atual).getCapacidade())) {
				vagoes.add((VagaoGraneleiro)atual);
				debito += ((VagaoGraneleiro)atual).getCapacidade();
			    }
			}
		    }
		}
	    } else {
		/*
		 * caso nao haja espaco no trem todo, nao faz o carregamento
		 */
		return null;
	    }
	}
	/*
	 * Conversao da ArrayList para o vetor que é pedido no retorno
	 */
	int i=0;
	Vagao retorno[] = new Vagao[vagoes.size()];
	for (VagaoGraneleiro vagao : vagoes) {
	    retorno[i++] = (Vagao) vagao;
	}
	return retorno;
    }
    /**
     * A Funcao carregarLiquido tenta colocar a quantidade de liquido em apenas 1 vagao 
     * da composicao, se falhar ela tenta distribuir essa quantidade nos vagoes, 
     * caso nao haja capacidade para o liquido na composicao a funao nao carrega os liquidos
     * @param quantidade
     * @param conteudo
     * @return Vagao[] vetor de vagoes que sofreram carregamento
     */
    public Vagao[] carregarLiquido(int quantidade, ConteudoLiquido conteudo) {	
	ArrayList<VagaoLiquidos> vagoes = new ArrayList<VagaoLiquidos>();
		
	/*
	 * tenta carregar a quantidade do conteudo em apenas um vagao
	 */
	for (Carro carro : this.carros) {
	    if (carro instanceof VagaoLiquidos) {
		if ( ((VagaoLiquidos) carro).aceitaConteudo(conteudo)) {
		    if (((VagaoLiquidos) carro).carregarCarga(quantidade)) {
			vagoes.add((VagaoLiquidos)carro);
			((VagaoLiquidos)carro).setConteudoUsado(conteudo);
			break;
		    }
		}
	    }
	}
	/*
	 * conta a quantidade total de capacidade (soma)
	 * na composicao
	 */
	int capacidadeVazios = 0;
	for (Carro carro : this.carros) {
	    if (carro instanceof VagaoLiquidos) {
		if (!((VagaoLiquidos) carro).carregado() && ((VagaoLiquidos) carro).aceitaConteudo(conteudo)) {
		    capacidadeVazios += ((VagaoLiquidos) carro).getCapacidade();
		}
	    }
	}
	/*
	 * caso nao conseguiu colocar em apenas um vagao tenta distribuir
	 */
	if (vagoes.isEmpty()) {
	    int debito = -quantidade;
	    int i=0;
	    if (capacidadeVazios >= quantidade) {
		/*
		 * distribui a quantidade sequencialmente nos vagoes
		 */
		while (debito < 0) {
		    Carro atual = this.carros.get(i++);
		    if (atual instanceof VagaoLiquidos) {
			if ( (-1)*debito <= ((VagaoLiquidos)atual).getCapacidade()) {
			    if ( ((VagaoLiquidos) atual).aceitaConteudo(conteudo)) {
				if (((VagaoLiquidos)atual).carregarCarga((-1)*debito)) {
				    vagoes.add((VagaoLiquidos)atual);
				    ((VagaoLiquidos)atual).setConteudoUsado(conteudo);
				    debito =0;
				}
			    }
			} else {
			    if ( ((VagaoLiquidos) atual).aceitaConteudo(conteudo)) {
				if (((VagaoLiquidos)atual).carregarCarga(((VagaoLiquidos)atual).getCapacidade())) {
				    vagoes.add((VagaoLiquidos)atual);
				    ((VagaoLiquidos)atual).setConteudoUsado(conteudo);
				    debito += ((VagaoLiquidos)atual).getCapacidade();
				}
			    }
			}
		    }
					
		}
	    } else {
		/*
		 * caso nao houve capacidade total para armazenar aquela quantidade
		 */
		return null;
	    }
	}
	/*
	 * conversao de ArrayList para o vetor de retorno
	 */
	int i=0;
	Vagao retorno[] = new Vagao[vagoes.size()];
	for (VagaoLiquidos vagao : vagoes) {
	    retorno[i++] = (Vagao) vagao;
	}
	return retorno;
    }
	
    /**
     * Remove o carro indicado.
     */
    public void removeCarro(Carro carro) {
	this.carros.remove(carro);
    }
    /**
     * Ordena o vetor de carros
     */
    public void ordena() {
    	Collections.sort(carros);
    }
    /**
     * Ordena o vetor de carros por carga
     */
    public void ordenaPorCarga() {
    	TremComparator comparator = new TremComparator();
    	Collections.sort(carros, comparator);
    }
}
