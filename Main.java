
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
		
		@SuppressWarnings("resource")
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
			}
			
		} while (!comando.equals("sair"));
	}

}
