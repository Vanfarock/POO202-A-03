
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
import pergunta.proposicional.Expression;
import pergunta.proposicional.LogicaProposicionalTask;
import pergunta.proposicional.Origin;
import pergunta.raciocinio.Person;

public class LogicaProposicional extends Enigma {

	public LogicaProposicional(String nomeArquivo) {
		super(nomeArquivo);
	}

	@Override
	public JPanel mostrar() {
		return this.getTask().show();
	}

	@SuppressWarnings("unchecked")
	@Override
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

	}
}
