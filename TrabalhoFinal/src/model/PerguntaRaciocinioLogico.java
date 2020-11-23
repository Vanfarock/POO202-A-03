package model;

import java.util.List;

public class PerguntaRaciocinioLogico {
	
	List<Pessoa> pessoas;
	int cargaMaxima;
	
	public PerguntaRaciocinioLogico(List<Pessoa> pessoas, int cargaMaxima) {
		this.setPessoas(pessoas);
		this.setCargaMaxima(cargaMaxima);
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	public int getCargaMaxima() {
		return cargaMaxima;
	}
	public void setCargaMaxima(int cargaMaxima) {
		this.cargaMaxima = cargaMaxima;
	}
	
}
