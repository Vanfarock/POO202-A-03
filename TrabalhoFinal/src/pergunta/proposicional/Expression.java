package pergunta.proposicional;

import java.awt.Point;

import engine.graphics.Rectangle;

public class Expression extends Rectangle {
	private String answer;
	private int order;

	public Expression(int y, int width, int height, String text, int order) {
		super(new Point(10, y), width, height, text);
		this.setOrder(order);
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	public boolean answerIsCorrect(Origin origin) {
		return this.getAnswer().equals(origin.getText().getText());
	}
}