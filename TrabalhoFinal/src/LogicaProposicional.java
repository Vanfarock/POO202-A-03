import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pergunta.proposicional.Expression;
import pergunta.proposicional.LogicaProposicionalTask;
import pergunta.proposicional.Origin;	

public class LogicaProposicional extends Enigma {
	
	public LogicaProposicional(int id, String nomeArquivo) {
		super(id, nomeArquivo);
	}

	@Override
	public JPanel mostrar() {
		return this.getTask().show();
	}
	
	@SuppressWarnings("unchecked")
	@Override
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
	}
}
