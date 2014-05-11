
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Classe para programa principal.
 *
 */
public class Main {

	/**
	 * Programa principal
	 * @param args Não usado
	 */
	public static void main(String[] args) {
		HashMap<String, Carro> carros = new HashMap<String, Carro>();
		HashMap<String, Composicao> composicoes = new HashMap<String, Composicao>();
		HashMap<String, Viagem> viagens = new HashMap<String, Viagem>();
		
		Scanner entrada = new Scanner(System.in);
		String comando;
		
		do {
			comando = entrada.next();
			
			if (comando.equals("cnova")) { // composição nova
				String codigo = entrada.next();
				composicoes.put(codigo, new Composicao(codigo));
				
			} else if (comando.equals("cnovo")) { // carro novo
				String tipo = entrada.next();
				String codigo = entrada.next();
				
				if (tipo.equals("locomotiva")) { // locomotiva
					String motor = entrada.next();
					if (motor.equals("E"))
						carros.put(codigo, new Locomotiva(codigo, Locomotiva.Motor.ELETRICO));
					else if (motor.equals("V"))
						carros.put(codigo, new Locomotiva(codigo, Locomotiva.Motor.VAPOR));
					else if (motor.equals("D"))
						carros.put(codigo, new Locomotiva(codigo, Locomotiva.Motor.DIESEL));
				} else if (tipo.equals("vagaog")) { // vagão graneleiro
					int capacidade = entrada.nextInt();
					carros.put(codigo, new VagaoGraneleiro(codigo, capacidade));
				} else if (tipo.equals("vagaol")) { // vagão de líquidos
					int capacidade = entrada.nextInt();
					int tiposConteudo = entrada.nextInt();
					ConteudoLiquido conteudos[] = new ConteudoLiquido[tiposConteudo];

					for (int i = 0; i < tiposConteudo; i++) {
						String conteudo = entrada.next();
						if (conteudo.equals("AG"))
							conteudos[i] = ConteudoLiquido.AGUA;
						else if (conteudo.equals("PE"))
							conteudos[i] = ConteudoLiquido.PETROLEO;
						else if (conteudo.equals("AL"))
							conteudos[i] = ConteudoLiquido.ALCOOL;
					}

					carros.put(codigo, new VagaoLiquidos(codigo, capacidade, conteudos));
				} else if (tipo.equals("vagaop")) { // vagão plataforma
					int capacidade = entrada.nextInt();
					String tipoCarga = entrada.next();
					carros.put(codigo, new VagaoPlataforma(codigo, capacidade, tipoCarga));
				}
			} else if (comando.equals("cadd")) { // adiciona carro na composição
				String codigoComposicao = entrada.next();
				String codigoCarro = entrada.next();
				composicoes.get(codigoComposicao).adicionaCarro(carros.get(codigoCarro));
				
			} else if (comando.equals("cinfo")) { // lista dados da composição 
				String codigoComposicao = entrada.next();
				Composicao composicao = composicoes.get(codigoComposicao);
				System.out.println(composicao);
				
				if (composicao.valida()) System.out.print("Valida / ");
				else System.out.print("Invalida / ");
				
				if (composicao.inflamavel()) System.out.println("Inflamavel");
				else System.out.println("Nao Inflamavel");
				
				int tamanhoComposicao = composicao.tamanhoComposicao();
				for (int i = 0; i < tamanhoComposicao; i++) {
					System.out.println(composicao.getCarro(i));
				}
				
			} else if (comando.equals("listar")) { // lista composições presentes
				Iterator<Map.Entry<String,Composicao>> it = composicoes.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String,Composicao> elem = it.next(); 
					System.out.println(elem.getValue());
				}
			} else if (comando.equals("vadd")) { // adiciona nova viagem
				String codigoComposicao = entrada.next();
				viagens.put(codigoComposicao, new Viagem(composicoes.get(codigoComposicao)));
			} else if (comando.equals("vaddc")) { // adiciona nova cidade no roteiro da viagem
				String codigoComposicao = entrada.next();
				String cidade = entrada.next();
				Viagem viagem = viagens.get(codigoComposicao);
				viagem.adicionarDestino(cidade);
			} else if (comando.equals("vinfo")) { // cidades de uma viagem
				String codigoComposicao = entrada.next();
				Viagem viagem = viagens.get(codigoComposicao);
				System.out.println(viagem);
			} else if (comando.equals("vinit")) { // inicia viagem
				String codigoComposicao = entrada.next();
				Viagem viagem = viagens.get(codigoComposicao);
				
				try {
					viagem.iniciaViagem();
					System.out.println("Viagem da composicao " + codigoComposicao + " iniciada");
				} catch (TremException e) {
					if (e.getTipo() == TremException.COMPOSICAO_INVALIDA) {
						System.out.println("Viagem da composicao " + codigoComposicao + " invalida");
						System.out.println(e.getMessage());
					} else
						e.printStackTrace();
				}
			} else if (comando.equals("vavanca")) { // avança cidade na viagem
				String codigoComposicao = entrada.next();
				Viagem viagem = viagens.get(codigoComposicao);
				if (viagem.avancaCidade())
					System.out.println("Composicao " + codigoComposicao + " avancou");
				else
					System.out.println("Composicao " + codigoComposicao + " nao avancou");
			} else if (comando.equals("vaddcargag")) { // adiciona carga de granéis
				String codigoComposicao = entrada.next();
				String cidade = entrada.next();
				int quantidade = entrada.nextInt();
				Viagem viagem = viagens.get(codigoComposicao);
				
				try {
					viagem.adicaoCargaGraneis(quantidade, cidade);
					System.out.println("Carga de graneis carregado na composicao " + codigoComposicao);
				} catch (TremException e) {
					if (e.getTipo() == TremException.CARREGAMENTO_INVALIDO) {
						System.out.println("Quantidade de graneis nao pode ser adicionada");
						System.out.println(e.getMessage());
					} else
						e.printStackTrace();
				}
			} else if (comando.equals("vaddcargal")) { // adiciona carga de líquida
				String codigoComposicao = entrada.next();
				String cidade = entrada.next();
				int quantidade = entrada.nextInt();
				Viagem viagem = viagens.get(codigoComposicao);

				String conteudo = entrada.next();
				ConteudoLiquido tipoConteudo = null;
				if (conteudo.equals("AG"))
					tipoConteudo = ConteudoLiquido.AGUA;
				else if (conteudo.equals("PE"))
					tipoConteudo = ConteudoLiquido.PETROLEO;
				else if (conteudo.equals("AL"))
					tipoConteudo = ConteudoLiquido.ALCOOL;

				try {
					viagem.adicaoCargaLiquida(quantidade, tipoConteudo, cidade);
					System.out.println("Carga de liquidos carregado na composicao " + codigoComposicao);
				} catch (TremException e) {
					if (e.getTipo() == TremException.CARREGAMENTO_INVALIDO) {
						System.out.println("Quantidade de liquidos nao pode ser adicionada");
						System.out.println(e.getMessage());
					} else
						e.printStackTrace();
				}
			} else if (comando.equals("carregarP")) {
				String codigoVagao = entrada.next();
				int quantidade = entrada.nextInt();
				
				Carro carro = carros.get(codigoVagao);
				if (carro instanceof VagaoPlataforma) {
					VagaoPlataforma vagao = (VagaoPlataforma)carro;
					vagao.carregarCarga(quantidade);
				}
					
			}
			
		} while (!comando.equals("sair"));
		
		entrada.close();
	}

}
