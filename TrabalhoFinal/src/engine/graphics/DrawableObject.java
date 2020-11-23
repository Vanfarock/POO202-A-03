package engine.graphics;

import java.awt.Graphics;
import java.awt.Point;

public abstract class DrawableObject {
	protected Point pos;
	protected int width;
	protected int height;
	protected Anchor anchor;
	protected Graphics graphics;

	public DrawableObject(Point pos, Anchor anchor, int width, int height) {
		setAnchor(anchor);
		setWidth(width);
		setHeight(height);
		setAnchoredPos(pos);
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		if (pos != null) {
			this.pos = pos;
		}
	}
	
	public void setAnchoredPos(Point pos) {
		if (pos == null) {
			throw new IllegalArgumentException("Given point is null");
		}
		switch (getAnchor()) {
		case TopLeft:
		default:
			break;
		case Center:
			pos.translate(-getWidth() / 2, -getHeight() / 2);
			break;
		}
		this.pos = pos;
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

	public Anchor getAnchor() {
		return anchor;
	}

	public void setAnchor(Anchor anchor) {
		if (anchor == null) {
			anchor = Anchor.TopLeft;
		}
		this.anchor = anchor;
	}

	public void move(int dx, int dy) {
		this.getPos().translate(dx, dy);
	}
	
	public abstract void draw(Graphics g);

	public abstract boolean mouseOver(Point mousePos);

}
