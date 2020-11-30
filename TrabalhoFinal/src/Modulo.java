
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Modulo implements ModuleInterface {
	private BombInterface bomb;
	private HashMap<Integer, Enigma> enigmas;
	private Enigma enigmaAtivo;
	private InformacaoEstatistica infoEstatistica;

	private final String RELATIVE_PATH = "\\src\\enigmas\\";
	private final String RIDDLES_FILENAME = "enigmas.json";

	public Modulo() {
		this.enigmas = new HashMap<Integer, Enigma>(); 
		this.carregarEnigmas();
		this.infoEstatistica = new InformacaoEstatistica();
	}

	public BombInterface getBomb() {
		return bomb;
	}

	public void setBomb(BombInterface bomb) {
		this.bomb = bomb;
	}

	public HashMap<Integer, Enigma> getEnigmas() {
		return enigmas;
	}

	private void addEnigma(Enigma enigma) {
		if (enigma != null) {
			this.enigmas.put((int) enigma.getId(), enigma);
		}
	}

	public Enigma getEnigmaAtivo() {
		return enigmaAtivo;
	}

	private void setEnigmaAtivo(Enigma enigmaAtivo) {
		if (enigmaAtivo != null) {
			this.enigmaAtivo = enigmaAtivo;
		}
	}

	public InformacaoEstatistica getInfoEstatistica() {
		return infoEstatistica;
	}

	public void setInfoEstatistica(InformacaoEstatistica infoEstatistica) {
		this.infoEstatistica = infoEstatistica;
	}

	@Override
	public void attach(BombInterface bomb) {
		this.setBomb(bomb);
	}

	@Override
	public int getActivations() {
		return this.getInfoEstatistica().getQtdUsos();
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
		this.setEnigmaAtivo(enigmaEncontrado);
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
			return (int) enigmaEncontrado.getQtdErros();
		}
		return 0;
	}

	@Override
	public boolean isDefused() {
		boolean estaDefusado = false;
		if (this.getEnigmaAtivo() != null) {
			estaDefusado = this.getEnigmaAtivo().getResolvido();
		}
		return estaDefusado;
	}

	private Enigma getEnigma(int enigma) {
		return this.getEnigmas().get(enigma);
	}

	@SuppressWarnings("unchecked")
	private void carregarEnigmas() {
		String filePath = new File("").getAbsolutePath().concat(RELATIVE_PATH + RIDDLES_FILENAME);
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(filePath));
			JSONObject config = (JSONObject) obj;
			JSONArray enigmasJson = (JSONArray) config.get("enigmas");
			int id = 1;
			for (JSONObject enigma : (ArrayList<JSONObject>) enigmasJson) {
				Enigma novoEnigma = null;
				String nomeArquivo = (String) enigma.get("arquivo");
				switch ((String) enigma.get("tipo")) {
				case "Raciocinio":
					novoEnigma = new RaciocinioLogico(id, RELATIVE_PATH + nomeArquivo);
					break;
				case "LogicaPredicados":
					novoEnigma = new LogicaPredicados(id, RELATIVE_PATH + nomeArquivo);
					break;
				case "LogicaProposicional":
					novoEnigma = new LogicaProposicional(id, RELATIVE_PATH + nomeArquivo);
					break;
				}
				if (novoEnigma != null) {
					this.addEnigma(novoEnigma);
					id++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
