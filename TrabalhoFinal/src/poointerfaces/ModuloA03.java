package poointerfaces;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ModuloA03 implements ModuleInterface {
	private BombInterface bomb;
	private HashMap<Integer, Enigma> enigmas;
	private Enigma enigmaAtivo;
	private InformacaoEstatistica infoEstatistica;

	private final String RIDDLES_FILENAME = "\\enigmas.json";

	public ModuloA03() {
		this.enigmas = new HashMap<Integer, Enigma>(); 
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
		this.carregarEnigmas();
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
			try {
				return enigmaEncontrado.getQtdAcertos();
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
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
	
	public void addErro() {
		this.infoEstatistica.addErro();
		this.bomb.addError();
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
	
	private String getRelativePath() {
		return this.getBomb().getFilesPath().toString();
	}
	
	@SuppressWarnings("unchecked")
	private void carregarEnigmas() {
		String filePath = new File("").getAbsolutePath().concat(getRelativePath()  + RIDDLES_FILENAME);
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(filePath));
			JSONObject config = (JSONObject) obj;
			JSONArray enigmasJson = (JSONArray) config.get("enigmas");
			int id = 1;
			
			for (JSONObject enigma : (ArrayList<JSONObject>) enigmasJson) {
				Enigma novoEnigma = null;
				String nomeArquivo = "\\" + (String) enigma.get("arquivo");
				
				switch ((String) enigma.get("tipo")) {
				case "Raciocinio":
					novoEnigma = new RaciocinioLogico(id, getRelativePath() + nomeArquivo, this);
					break;
				case "Logica":
					novoEnigma = new Logica(id, getRelativePath() + nomeArquivo, this);
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
