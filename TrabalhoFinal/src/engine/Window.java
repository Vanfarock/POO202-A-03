package engine;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import engine.graphics.DrawableObject;

@SuppressWarnings("serial")
public class Window extends JPanel {
	ArrayList<DrawableObject> shapes;

	public Window() {
		setSize(300, 250);
		setVisible(true);
		shapes = new ArrayList<DrawableObject>();
	}

	public ArrayList<DrawableObject> getShapes() {
		return shapes;
	}

	public void addShape(DrawableObject shape) {
		if (shape != null) {
			this.getShapes().add(shape);			
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (DrawableObject shape : this.getShapes()) {
			shape.draw(g);
		}
	}
}
