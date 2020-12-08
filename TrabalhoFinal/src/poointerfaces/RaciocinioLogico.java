package poointerfaces;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pergunta.raciocinio.Boat;
import pergunta.raciocinio.Person;
import pergunta.raciocinio.RaciocinioLogicoTask;

public class RaciocinioLogico extends Enigma {

	public RaciocinioLogico(int id, String nomeArquivo, ModuleInterface moduloA03) {
		super(id, nomeArquivo, moduloA03);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void inicializarTask(JSONObject config) {
		
		try {
			JSONArray questionLines = (JSONArray) config.get("linhasQuestao");
			
			JSONObject pergunta = (JSONObject) config.get("pergunta");
			JSONArray pessoasJson = (JSONArray) pergunta.get("pessoas");
			JSONObject barcoJson = (JSONObject) pergunta.get("barco");
	
			ArrayList<Person> pessoas = new ArrayList<>();
			int starting_y = 40;
			int increment_y = 25;
			//System.out.println(pessoasJson);
			for (JSONObject pessoa : (List<JSONObject>) pessoasJson) {
				System.out.println(pessoa);
				pessoas.add(new Person(starting_y, 25, 25, (String) pessoa.get("legenda"),
						(long) pessoa.get("peso")));
				starting_y += increment_y;
			}
			
			Boat barco = new Boat(new Point(50, 150), 100, 25, (String) barcoJson.get("legenda"),
					(long) barcoJson.get("cargaMaxima"));
	
			RaciocinioLogicoTask task = new RaciocinioLogicoTask((ArrayList<String>)questionLines, pessoas, barco, this);
			this.setTask(task);
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
