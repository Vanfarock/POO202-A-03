package engine.graphics;

import java.awt.Graphics;
import java.awt.Point;

public class Rectangle extends DrawableObject {
	private Text text;

	public Rectangle(Point pos, Anchor anchor, int width, int height, String text) {
		super(pos, anchor, width, height);
		this.setText(text);
	}

	@Override
	public void setAnchoredPos(Point pos) {
		super.setAnchoredPos(pos);
		if (this.getText() != null) {
			this.getText().setAnchoredPos(new Point(getPos().x, getPos().y + getHeight() / 2));
		}
	}
	
	@Override
	public void setPos(Point pos) {
		super.setPos(pos);
		if (this.getText() != null) {
			this.getText().setPos(new Point(getPos().x, getPos().y + getHeight() / 2));
		}
	}

	public Text getText() {
		return this.text;
	}

	private void setText(String text) { 
		this.text = new Text(new Point(getPos().x, getPos().y + getHeight() / 2), Anchor.Center, text);
	}

	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);
		if (this.getText() != null) {
			this.getText().move(dx, dy);
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawRect(getPos().x, getPos().y, getWidth(), getHeight());
		if (this.getText() != null) {
			this.getText().draw(g);
		}
	}

	@Override
	public boolean mouseOver(Point mousePos) {
		int mouseX = (int) mousePos.getX();
		int mouseY = (int) mousePos.getY();
		Point rectPos = this.getPos();
		return (mouseX >= rectPos.x && mouseX <= rectPos.x + this.getWidth() 
			 && mouseY >= rectPos.y && mouseY <= rectPos.y + this.getHeight());
	}

	public boolean isOverlapping(Rectangle other) {
		Point thisPos = getPos();
		Point otherPos = other.getPos();
		if (thisPos.y > otherPos.y + other.getHeight() ||
			thisPos.y + getHeight() < otherPos.y) {
			return false;
		}
		
		if (thisPos.x + getWidth() < otherPos.x ||
			thisPos.x > otherPos.x + other.getWidth()) {
			return false;
		}
		
		return true;
	}
}
