package pergunta.logica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import engine.graphics.Rectangle;

public class Button extends Rectangle {

	public Button(Point pos, int width, int height, String text) {
		super(pos, width, height, text);
	}

	public boolean questionIsCorrect(ArrayList<Expression> expressions, ArrayList<Origin> origins) {
		for (Expression e : expressions) {
			System.out.println("Expression: " + e.getText().getText());
			for (Origin o : origins) {
				if (o.getOrder() == e.getOrder()) {
					if (!e.answerIsCorrect(o)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		super.draw(g);
	}
}
