

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import pergunta.Task;

public abstract class Enigma {

	private int id;
	private String nomeArquivo;
	private String desafio;
	private String solucao;
	private Boolean resolvido;
	private Task task;

	private InformacaoEstatistica infoEstatistica;

	public Enigma(int id, String nomeArquivo) {
		this.setId(id);
		this.setNomeArquivo(nomeArquivo);
		this.infoEstatistica = new InformacaoEstatistica();
		this.carregarInformacoes();
	}

	public InformacaoEstatistica getInfoEstatistica() {
		return infoEstatistica;
	}

	public void setInfoEstatistica(InformacaoEstatistica infoEstatistica) {
		this.infoEstatistica = infoEstatistica;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("Id de enigma invalido");
		}
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		if (nomeArquivo == null) {
			throw new IllegalArgumentException("Nome de arquivo invalido");
		}
		this.nomeArquivo = nomeArquivo;
	}

	public String getDesafio() {
		return desafio;
	}

	public void setDesafio(String desafio) {
		if (desafio == null) {
			throw new IllegalArgumentException("Desafio de enigma invalido");
		}
		this.desafio = desafio;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		if (solucao == null) {
			throw new IllegalArgumentException("Solucao de enigma invalida");
		}
		this.solucao = solucao;
	}

	public Boolean getResolvido() {
		return resolvido;
	}

	public void setResolvido(Boolean resolvido) {
		this.resolvido = resolvido;
	}

	public void setQtdUso(int qtdUsos) {
		this.getInfoEstatistica().setQtdUsos(qtdUsos);
	}

	public void addUso() {
		this.getInfoEstatistica().addUso();
	}

	public int getQtdUso() {
		return getInfoEstatistica().getQtdUsos();
	}

	public void setQtdErros(int qtdErros) {
		this.getInfoEstatistica().setQtdErros(qtdErros);
	}

	public void addErro() {
		this.getInfoEstatistica().addErro();
	}

	public int getQtdErros() {
		return getInfoEstatistica().getQtdErros() + getTask().getQtdErros();
	}

	public void setQtdAcertos(int qtdAcertos) {
		this.getInfoEstatistica().setQtdAcertos(qtdAcertos);
	}

	public void addAcerto() {
		this.getInfoEstatistica().addAcerto();
	}

	public int getQtdAcertos() {
		if (getTask().isSolved()) {
			getInfoEstatistica().addAcerto();
		}
		return getInfoEstatistica().getQtdAcertos();
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public JPanel mostrar() {
		return this.getTask().show();
	}

	public void carregarInformacoes() {
		String filePath = new File("").getAbsolutePath().concat(this.getNomeArquivo());
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(filePath));
			JSONObject config = (JSONObject) obj;
			
			int erros = ((Long) config.get("qtdErros")).intValue();
			this.setQtdErros(erros);

			int acertos = ((Long) config.get("qtdAcertos")).intValue();
			this.setQtdAcertos(acertos);

			int usos = ((Long) config.get("qtdUso")).intValue();
			this.setQtdUso(usos);

			inicializarTask(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void salvarInformacoes() throws IOException, ParseException {
		String filePath = new File("").getAbsolutePath().concat(this.getNomeArquivo());
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(filePath));
			JSONObject config = (JSONObject) obj;
			config.put("qtdErros", this.getQtdErros());
			config.put("qtdAcertos", this.getQtdAcertos());
			config.put("qtdUsos", this.getQtdUso());

			try (FileWriter writeFile = new FileWriter(filePath)) {
				writeFile.write(config.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected abstract void inicializarTask(JSONObject config);
}
