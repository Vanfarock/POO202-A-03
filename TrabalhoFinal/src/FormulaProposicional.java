
import java.util.ArrayList;

public class FormulaProposicional {
	private ArrayList<String> expressoes;
	private ArrayList<String> resultados;

	public ArrayList<String> getExpressoes() {
		return expressoes;
	}

	public void setExpressoes(ArrayList<String> expressoes) {
		if (expressoes == null) {
			throw new IllegalArgumentException("Expressoes invalidas");
		}
		this.expressoes = expressoes;
	}

	public ArrayList<String> getResultados() {
		return resultados;
	}

	public void setResultados(ArrayList<String> resultados) {
		if (resultados == null) {
			throw new IllegalArgumentException("Resultados invalidos");
		}
		this.resultados = resultados;
	}

}
