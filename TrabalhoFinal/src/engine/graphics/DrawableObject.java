package engine.graphics;

import java.awt.Graphics;
import java.awt.Point;

public abstract class DrawableObject {
	protected Point pos;
	protected int width;
	protected int height;
	protected Graphics graphics;

	public DrawableObject(Point pos, int width, int height) {
		setWidth(width);
		setHeight(height);
		setPos(pos);
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		if (pos != null) {
			this.pos = pos;
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void move(int dx, int dy) {
		this.getPos().translate(dx, dy);
	}
	
	public abstract void draw(Graphics g);

	public abstract boolean mouseOver(Point mousePos);

}
