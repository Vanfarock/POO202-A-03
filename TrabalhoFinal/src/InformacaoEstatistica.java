


public class InformacaoEstatistica {
	private int qtdErros;
	private int qtdAcertos;
	private int qtdUsos;

	public InformacaoEstatistica() {
		
	}

	public void setQtdErros(int erro) {
		if (erro > 0) {
			this.qtdErros = erro;
		}
	}

	public int getQtdErros() {
		return qtdErros;
	}

	public void addErro() {
		this.qtdErros++;
	}

	public int getQtdAcertos() {
		return qtdAcertos;
	}

	public void addAcerto() {
		this.qtdAcertos++;
	}
	
	public void setQtdAcertos(int acertos) {
		if(qtdAcertos > 0) {
			this.qtdAcertos = acertos;
		}
	}

	public int getQtdUsos() {
		return qtdUsos;
	}
	
	public void setQtdUsos(int usos) {
		if(usos > 0) {
			this.qtdUsos = usos;
		}
	}

	public void addUso() {
		this.qtdUsos++;
	}

}
