
import java.util.Comparator;

public class TremComparator implements Comparator<Carro> {

	private int compareVagaoGraneleiro(VagaoGraneleiro o1, VagaoGraneleiro o2) {
		if (o1.quantidadeCarregada() - o2.quantidadeCarregada() == 0) {
			return o1.compareTo(o2);
		} else {
			return o1.quantidadeCarregada() - o2.quantidadeCarregada();
		}
	}

	private int compareVagaoLiquidos(VagaoLiquidos o1, VagaoLiquidos o2) {
		if (ConteudoLiquido.stringFormat(o1.getConteudoUsado()).compareTo(ConteudoLiquido.stringFormat(o2.getConteudoUsado())) == 0) {
			if (o1.quantidadeCarregada() - o2.quantidadeCarregada() == 0) {
				return o1.compareTo(o2);
			} else {
				return o1.quantidadeCarregada() - o2.quantidadeCarregada();
			}
		} else {
			return ConteudoLiquido.stringFormat(o1.getConteudoUsado()).compareTo(ConteudoLiquido.stringFormat(o2.getConteudoUsado()));
		}
	}

	private int compareVagaoPlataforma(VagaoPlataforma o1, VagaoPlataforma o2) {
		if (o1.getTipoCarga().compareTo(o2.getTipoCarga()) == 0) {
			if (o1.quantidadeCarregada() - o2.quantidadeCarregada() == 0) {
				return o1.compareTo(o2);
			} else {
				return o1.quantidadeCarregada() - o2.quantidadeCarregada();
			}
		} else {
			return o1.getTipoCarga().compareTo(o2.getTipoCarga());
		}
	}

	@Override
	public int compare(Carro o1, Carro o2) {
		if (o1 instanceof VagaoGraneleiro && o2 instanceof VagaoGraneleiro) {
			return compareVagaoGraneleiro((VagaoGraneleiro) o1, (VagaoGraneleiro) o2); 
		} else if (o1 instanceof VagaoLiquidos && o2 instanceof VagaoLiquidos) {
			return compareVagaoLiquidos((VagaoLiquidos) o1,(VagaoLiquidos) o2);
		} else if (o1 instanceof VagaoPlataforma && o2 instanceof VagaoPlataforma) {
			return compareVagaoPlataforma((VagaoPlataforma) o1, (VagaoPlataforma) o2);
		} else {
			return o1.compareTo(o2);
		}
	}

}
