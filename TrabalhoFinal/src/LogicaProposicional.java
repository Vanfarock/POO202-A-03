
<<<<<<< HEAD

import java.awt.Point;
=======
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
>>>>>>> f3c5d0cd3ef23ccdc56c10410386dba50af380ed
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pergunta.proposicional.Expression;
import pergunta.proposicional.LogicaProposicionalTask;
<<<<<<< HEAD
import pergunta.proposicional.Origin;	

public class LogicaProposicional extends Enigma {
	
	public LogicaProposicional(int id, String nomeArquivo) {
		super(id, nomeArquivo);
=======
import pergunta.proposicional.Origin;
import pergunta.raciocinio.Person;

public class LogicaProposicional extends Enigma {

	public LogicaProposicional(String nomeArquivo) {
		super(nomeArquivo);
>>>>>>> f3c5d0cd3ef23ccdc56c10410386dba50af380ed
	}

	@Override
	public JPanel mostrar() {
		return this.getTask().show();
	}
	
	@SuppressWarnings("unchecked")
	@Override
<<<<<<< HEAD
	protected void inicializarTask(JSONObject config) {
		JSONObject pergunta = (JSONObject)config.get("pergunta");
	  	  JSONArray expressionsJson = (JSONArray)pergunta.get("expressoes");
	  	  JSONArray resultsJson = (JSONArray)pergunta.get("resultados");
	    
	  	  int starting_y = 5;
	  	  int increment_y = 30;
	  	  int order = 1;
	  	  HashMap<Integer, Origin> origins = new HashMap<Integer, Origin>();
	  	  for (String result : (List<String>)resultsJson) {
	  		  origins.put(order, new Origin(0, 100, increment_y, result, order));
	  		  order++;
	  	  }
	  	  
	  	  order = 1;
	  	  ArrayList<Expression> expressions = new ArrayList<Expression>();
	  	  for (String expression : (List<String>)expressionsJson) {
	  		  Expression newExpression = new Expression(starting_y, 100, increment_y, expression, order);
	  		  newExpression.setAnswer(origins.get(order).getText().getText());
	  		  expressions.add(newExpression);
	  		  order++;
	  		  starting_y += increment_y;
	  	  }
	  	
	  	  starting_y = 5;
	  	  order = 1;
	  	  ArrayList<Origin> shuffledOrigins = new ArrayList<Origin>(origins.values());
	  	  Collections.shuffle(shuffledOrigins);
		  for (Origin o: shuffledOrigins) {
			  o.setOrder(order);
			  o.setInitialPos(new Point(o.getPos().x, starting_y));
			  order++;
			  starting_y += increment_y;
		  }
	  	  
	  	  LogicaProposicionalTask task = new LogicaProposicionalTask(expressions, shuffledOrigins);
	  	  this.setTask(task);
=======
	public void carregarInformacoes() {
		String filePath = new File("").getAbsolutePath().concat(this.getNomeArquivo());
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(filePath));
	    	JSONObject config = (JSONObject) obj;

			this.setConfig(config);

			long id = (long) config.get("id");
			this.setId(id);

			JSONObject pergunta = (JSONObject) config.get("pergunta");
			JSONArray expressionsJson = (JSONArray) pergunta.get("expressoes");
			JSONArray resultsJson = (JSONArray) pergunta.get("resultados");

			ArrayList<Expression> expressions = new ArrayList<Expression>();
			int starting_y_expression = 10;
			int increment_y_expression = 30;
			for (String expression : (List<String>) expressionsJson) {
				expressions.add(
						new Expression(starting_y_expression, Anchor.TopLeft, 100, increment_y_expression, expression));
				starting_y_expression += increment_y_expression;
			}

			ArrayList<Origin> origins = new ArrayList<Origin>();
			int starting_y_result = 10;
			int increment_y_result = 30;
			for (String result : (List<String>) resultsJson) {
				origins.add(new Origin(starting_y_result, Anchor.TopLeft, 100, increment_y_result, result));
				starting_y_result += increment_y_result;
			}

			LogicaProposicionalTask task = new LogicaProposicionalTask(expressions, origins);
			this.setTask(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void salvarInformacoes() throws IOException {
		String filePath = new File("").getAbsolutePath().concat(this.getNomeArquivo());
		FileWriter writeFile = new FileWriter(filePath);

		try {
			JSONObject config = this.getConfig();

			config.remove("qtdErros");
			config.put("qtdErros", this.getQtdErros());

			config.remove("qtdUso");
			config.put("qtdUso", this.getQtdErros());

			config.remove("qtdAcertos");
			config.put("qtdAcertos", this.getQtdAcertos());

			writeFile.write(config.toJSONString());
			writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

>>>>>>> f3c5d0cd3ef23ccdc56c10410386dba50af380ed
	}
}
