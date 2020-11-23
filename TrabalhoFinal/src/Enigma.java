
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;

import org.json.simple.JSONObject;

import pergunta.Task;

public abstract class Enigma {
	private long qtdUso;
	private long qtdErros;
	private long qtdAcertos;
	private long id;
	private String nomeArquivo;
	private String desafio;
	private String solucao;
	private Boolean resolvido;
	private Task task;
	private JSONObject config;

	public Enigma(String nomeArquivo) {
		this.setNomeArquivo(nomeArquivo);
		this.carregarInformacoes();
	}

	public long getQtdUso() {
		return qtdUso;
	}

	public void addUso() {
		this.qtdUso++;
	}

	public long getQtdErros() {
		return qtdErros;
	}

	public void addErro() {
		this.qtdErros++;
	}

	public long getQtdAcertos() {
		return qtdAcertos;
	}

	public void addAcerto() {
		this.qtdAcertos++;
	}

	public long getId() {
		return id;
	}

	protected void setId(long id) {
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

	public void setQtdUso(long qtdUso) {
		this.qtdUso = qtdUso;
	}

	public void setQtdErros(long qtdErros) {
		this.qtdErros = qtdErros;
	}

	public void setQtdAcertos(long qtdAcertos) {
		this.qtdAcertos = qtdAcertos;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	protected JSONObject getConfig() {
		return config;
	}

	protected void setConfig(JSONObject config) {
		this.config = config;
	}

	public JPanel mostrar() {
		return this.getTask().show();
	}

	public abstract void carregarInformacoes();

	public abstract void salvarInformacoes() throws IOException;

}
