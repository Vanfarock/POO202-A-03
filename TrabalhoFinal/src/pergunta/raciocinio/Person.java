package pergunta.raciocinio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import engine.graphics.Rectangle;

public class Person extends Rectangle {
	private Point initialPos;
	private long weight;
	private Side currentSide;
	private final static int LEFT_SIDE_X = 15;
	private final static int RIGHT_SIDE_X = 255;

	public Person(int y, int width, int height, String text, long weight) {
		super(new Point(LEFT_SIDE_X, y), width, height, text);
		this.setInitialPos(getPos());
		this.setWeight(weight);
		currentSide = Side.Left;
	}

	public Point getInitialPos() {
		return initialPos;
	}

	public void setInitialPos(Point initialPos) {
		if (initialPos != null) {
			this.initialPos = initialPos;
		}
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public Side getCurrentSide() {
		return currentSide;
	}

	public void setCurrentSide(Side currentSide) {
		this.currentSide = currentSide;
	}

	public void flipSide(Side newSide) {
		this.setCurrentSide(newSide);
		Point currentInitialPos = getInitialPos();
		if (getCurrentSide() == Side.Left) {
			this.setInitialPos(new Point(LEFT_SIDE_X, currentInitialPos.y));
		} else if (getCurrentSide() == Side.Right) {
			this.setInitialPos(new Point(RIGHT_SIDE_X, currentInitialPos.y));
		}
		this.setPos(this.getInitialPos());
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		super.draw(g);
	}
}
