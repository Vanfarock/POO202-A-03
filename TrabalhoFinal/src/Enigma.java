import java.io.FileNotFoundException;

import javax.swing.JPanel;

public abstract class Enigma {
	private int qtdUso;
	private int qtdErros;
	private int qtdAcertos;
	private int id;
	private String nomeArquivo;
	private String desafio;
	private String solucao;
	private Boolean resolvido;

	public Enigma(String nomeArquivo) {
		this.setNomeArquivo(nomeArquivo);
		this.carregarInformacoes();
	}

	public int getQtdUso() {
		return qtdUso;
	}

	public void addUso() {
		this.qtdUso++;
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

	public int getId() {
		return id;
	}

	private void setId(int id) {
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

	public void setQtdUso(int qtdUso) {
		this.qtdUso = qtdUso;
	}

	public void setQtdErros(int qtdErros) {
		this.qtdErros = qtdErros;
	}

	public void setQtdAcertos(int qtdAcertos) {
		this.qtdAcertos = qtdAcertos;
	}

	public abstract void carregarInformacoes();

	public abstract void salvarInformacoes();

	public abstract JPanel mostrar();
}
