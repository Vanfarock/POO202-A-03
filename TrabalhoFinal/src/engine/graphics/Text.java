package engine.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class Text extends DrawableObject {
	private String text;

	public Text(Point pos, String text) {
		super(pos, 0, 0);
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (text == null) {
			this.text = "";
		}
		else {
			this.text = text;			
		}
	}
	
	@Override
	public void draw(Graphics g) {
		Point position = this.getPos();
		g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
		g.drawString(this.getText(), position.x, position.y);
	}

	@Override
	public boolean mouseOver(Point mousePos) {
		
		return false;
	}
}
