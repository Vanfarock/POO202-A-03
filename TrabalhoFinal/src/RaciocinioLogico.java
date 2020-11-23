

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import engine.graphics.Anchor;
import pergunta.raciocinio.Boat;
import pergunta.raciocinio.Person;
import pergunta.raciocinio.RaciocinioLogicoTask;

public class RaciocinioLogico extends Enigma {

	public RaciocinioLogico(String nomeArquivo) {
		super(nomeArquivo);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void carregarInformacoes() {
		String filePath = new File("").getAbsolutePath().concat(this.getNomeArquivo());
		JSONParser parser = new JSONParser();
	      try {
	         Object obj = parser.parse(new FileReader(filePath));
	         JSONObject enigma = (JSONObject)obj;
	         
	         long id = (long)enigma.get("id");
	         JSONObject pergunta = (JSONObject)enigma.get("pergunta");
	         JSONArray pessoasJson = (JSONArray)pergunta.get("pessoas");
	         JSONObject barcoJson = (JSONObject)pergunta.get("barco");
	         
	         ArrayList<Person> pessoas = new ArrayList<>();
	         int starting_y = 25;
	         int increment_y = 25;
	         for (JSONObject pessoa : (List<JSONObject>)pessoasJson) {
	        	 pessoas.add(new Person(starting_y, Anchor.TopLeft, 25, 25,
	        			 				(String)pessoa.get("legenda"),
	        			 				(Long)pessoa.get("peso")));
	        	 starting_y += increment_y;
	         }
	         Boat barco = new Boat(new Point(50, 150), Anchor.TopLeft, 100, 25,
	        		 			  (String)barcoJson.get("legenda"),
	        		 			  (Long)barcoJson.get("cargaMaxima"));
	         
	         RaciocinioLogicoTask task = new RaciocinioLogicoTask(pessoas, barco);
	         this.setTask(task);
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	}

	@Override
	public void salvarInformacoes() throws IOException {
		JSONObject jsonObject = new JSONObject();

		FileWriter writeFile = new FileWriter(this.getNomeArquivo());
		
		try {
			writeFile.write(jsonObject.toJSONString());
			writeFile.close();	
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
}
