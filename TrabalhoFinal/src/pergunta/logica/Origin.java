package pergunta.logica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import engine.graphics.Rectangle;

public class Origin extends Rectangle {
	private Point initialPos;
	private int order;

	public Origin(int y, int width, int height, String text, int order) {
		super(new Point(110, y), width, height, text);
		this.setInitialPos(getPos());
		this.setOrder(order);
	}

	public Point getInitialPos() {
		return initialPos;
	}

	public void setInitialPos(Point initialPos) {
		this.initialPos = initialPos;
		this.setPos(initialPos);
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void resetPos() {
		this.setPos(getInitialPos());
	}

	public boolean isMovable() {
		String thisText = this.getText().getText().replaceAll("\\s+", "");
		return !thisText.equals("Premissa") && !thisText.equals("Hipótese");
	}

	public void switchPosition(Origin other) {
		Point thisPos = getInitialPos();
		Point otherPos = other.getInitialPos();
		int thisOrder = getOrder();
		int otherOrder = other.getOrder();

		this.setInitialPos(otherPos);
		other.setInitialPos(thisPos);
		
		this.setOrder(otherOrder);
		other.setOrder(thisOrder);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(50, 120, 50));
		super.draw(g);
	}
}
