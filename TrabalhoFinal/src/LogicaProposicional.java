
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;	

public class LogicaProposicional extends Enigma {
	
	public LogicaProposicional(String nomeArquivo) {
		super(nomeArquivo);
		// TODO Auto-generated constructor stub
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
	         JSONArray expressoes = (JSONArray)pergunta.get("expressoes");
	         JSONArray resultados = (JSONArray)pergunta.get("resultados");
	         String tipo = (String)enigma.get("tipo");
	         
	         FormulaProposicional formula = new FormulaProposicional();
	         formula.setExpressoes((ArrayList<String>)expressoes);
	         formula.setResultados((ArrayList<String>)resultados);
	         
	         System.out.println(formula.getExpressoes());
	         System.out.println(formula.getResultados());
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	}

	@Override
	public void salvarInformacoes() {
		// TODO Auto-generated method stub
		
	}
	
}