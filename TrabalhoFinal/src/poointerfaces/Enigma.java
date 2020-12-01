package poointerfaces;


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
	private Integer qtdErros;
	private Integer qtdAcertos;
	private Integer qtdUso;
	private ModuloA03 moduloA03;

	public Enigma(int id, String nomeArquivo, ModuleInterface moduloA03) {
		this.setId(id);
		this.setNomeArquivo(nomeArquivo);
		this.setModulo(moduloA03);
		this.carregarInformacoes();
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

	private ModuleInterface getModulo() {
		return moduloA03;
	}

	private void setModulo(ModuleInterface moduloA03) {
		this.moduloA03 = (ModuloA03) moduloA03;
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
		this.qtdUso = qtdUsos;
	}

	public void addUso() throws IOException, ParseException {
		this.qtdUso++;
		this.salvarInformacoes();
	}

	public int getQtdUso() {
		return this.qtdUso;
	}

	public void setQtdErros(int qtdErros) {
		this.qtdErros = qtdErros;
	}

	public void addErro() throws IOException, ParseException {
		this.qtdErros++;
		this.salvarInformacoes();
		this.moduloA03.addErro();
	}

	public int getQtdErros() {
		return this.qtdErros;
	}

	public void setQtdAcertos(int qtdAcertos) {
		this.qtdErros = qtdErros;
	}

	public void addAcerto() throws IOException, ParseException {
		this.qtdAcertos++;
		this.salvarInformacoes();
	}

	public int getQtdAcertos() throws IOException, ParseException {
		if (getTask().isSolved()) {
			this.addAcerto();
		}
		return this.qtdAcertos;
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
