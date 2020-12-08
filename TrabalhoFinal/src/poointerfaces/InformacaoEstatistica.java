package poointerfaces;

import java.io.FileReader;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InformacaoEstatistica {
	private int qtdErros = 0;
	private int qtdAcertos = 0;
	private int qtdUsos = 0;
	
	public InformacaoEstatistica() {
		
	}

	public void carregarEstatistica(List<String> caminhos) {
		
		for (String caminhoDoArquivo : caminhos) {
			
			String filePath = caminhoDoArquivo;
			JSONParser parser = new JSONParser();
			try {
				Object obj = parser.parse(new FileReader(filePath));
				JSONObject config = (JSONObject) obj;
				
				int erros = ((Long) config.get("qtdErros")).intValue();
				this.addErro(erros);
	
				int acertos = ((Long) config.get("qtdAcertos")).intValue();
				this.addAcertos(acertos);
	
				int usos = ((Long) config.get("qtdUso")).intValue();
				this.addUso(usos);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		if (qtdAcertos > 0) {
			this.qtdAcertos = acertos;
		}
	}

	public int getQtdUsos() {
		return qtdUsos;
	}

	public void setQtdUsos(int usos) {
		if (usos > 0) {
			this.qtdUsos = usos;
		}
	}

	public void addUso() {
		this.qtdUsos++;
	}
	
	
	public void addUso(int numero) {
		this.qtdUsos+= numero;
	}
	
	public void addErro(int numero) {
		this.qtdErros+= numero;
	}
	
	public void addAcertos(int numero) {
		this.qtdAcertos+= numero;
	}
	
	
}
