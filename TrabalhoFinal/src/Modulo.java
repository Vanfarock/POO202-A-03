
import java.util.HashMap;

import javax.swing.JPanel;

public class Modulo implements ModuleInterface {
	private BombInterface bomb;
	private HashMap<Integer, Enigma> enigmas;
	private Enigma enigmaAtivo;
	private int qtdAtivacoes;

	public Modulo() {
		this.enigmas = this.carregarEnigmas();
	}
	
	public BombInterface getBomb() {
		return bomb;
	}

	public void setBomb(BombInterface bomb) {
		if (bomb == null) {
			throw new IllegalArgumentException("Bomba invalida");
		}
		this.bomb = bomb;
	}

	public HashMap<Integer, Enigma> getEnigmas() {
		return enigmas;
	}

	private void addEnigma(Enigma enigma) {
		if (enigma == null) {
			throw new IllegalArgumentException("Enigma invalido");
		}
		this.enigmas.put(enigma.getId(), enigma);
	}

	public Enigma getEnigmaAtivo() {
		return enigmaAtivo;
	}

	private void setEnigmaAtivo(Enigma enigmaAtivo) {
		if (enigmaAtivo == null) {
			throw new IllegalArgumentException("Enigma ativo invalido");
		}
		this.enigmaAtivo = enigmaAtivo;
	}

	public int getQtdAtivacoes() {
		return qtdAtivacoes;
	}

	public void setQtdAtivacoes(int qtdAtivacoes) {
		this.qtdAtivacoes = qtdAtivacoes;
	}

	@Override
	public void attach(BombInterface bomb) {
		this.setBomb(bomb);
	}

	@Override
	public int getActivations() {
		return this.getQtdAtivacoes();
	}

	@Override
	public int getExecutions(int enigma) {
		Enigma enigmaEncontrado = this.getEnigma(enigma);
		if (enigmaEncontrado != null) {
			return enigmaEncontrado.getQtdUso();
		}
		return 0;
	}

	@Override
	public JPanel getModulePanel(int enigma) {
		Enigma enigmaEncontrado = this.getEnigma(enigma);
		if (enigmaEncontrado != null) {
			return enigmaEncontrado.mostrar();
		}
		return null;
	}

	@Override
	public int getRightAnswers(int enigma) {
		Enigma enigmaEncontrado = this.getEnigma(enigma);
		if (enigmaEncontrado != null) {
			return enigmaEncontrado.getQtdAcertos();
		}
		return 0;
	}

	@Override
	public int getWrongAnswers(int enigma) {
		Enigma enigmaEncontrado = this.getEnigma(enigma);
		if (enigmaEncontrado != null) {
			return enigmaEncontrado.getQtdErros();
		}
		return 0;
	}

	@Override
	public boolean isDefused() {
		boolean estaDefusado = true;
		for (Enigma enigma : this.getEnigmas().values()) {
			estaDefusado = estaDefusado && enigma.getResolvido();
		}
		return estaDefusado;
//		Enigma enigma = this.getEnigmaAtivo();
//		if (enigma != null) {
//			return enigma.getResolvido();
//		}
//		return false;
	}

	private Enigma getEnigma(int enigma) {
		return this.getEnigmas().get(enigma);
	}
	
	private HashMap<Integer, Enigma> carregarEnigmas() {
		// TODO
		return null;
	}

}
