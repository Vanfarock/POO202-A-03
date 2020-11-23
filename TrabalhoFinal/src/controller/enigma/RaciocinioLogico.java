package controller.enigma;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.Enigma;
import model.FormulaProposicional;
import model.PerguntaRaciocinioLogico;
import model.Pessoa;

public class RaciocinioLogico extends Enigma {

	public RaciocinioLogico(String nomeArquivo) {
		super(nomeArquivo);
	}

	@Override
	public JPanel mostrar() {
		return null;
		// TODO Auto-generated method stub
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
	         JSONArray pessoas = (JSONArray)pergunta.get("pessoas");
	         Integer cargaMaxima = (Integer)pergunta.get("cargaMaxima");
	         String resposta = (String)enigma.get("resposta");
	         String tipo = (String)enigma.get("tipo");
	         
	         
	         List<Pessoa> pessoa = new ArrayList<>();
	         (List<Integer>) pessoas.forEach(peso ->{
	        	 pessoa.add(new Pessoa(peso));
	         });
	         PerguntaRaciocinioLogico formula = new PerguntaRaciocinioLogico(pessoa, cargaMaxima);
	         
	         this.setSolucao(resposta);
	         
	         System.out.println(formula.getCargaMaxima());
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	}

	@Override
	public void salvarInformacoes() {
		JSONObject jsonObject = new JSONObject();

		FileWriter writeFile = new FileWriter(this.getNomeArquivo());
		
		jsonObject.put("chave", "valor");
		jsonObject.put("chave", "valor");
		jsonObject.put("chave", "valor");
		jsonObject.put("chave", "valor");
		
		try {
			writeFile.write(jsonObject.toJSONString());
			writeFile.close();	
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
}
