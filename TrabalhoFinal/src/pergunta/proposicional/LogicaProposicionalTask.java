package pergunta.proposicional;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import java.util.Collections;

import engine.Window;
import engine.graphics.Anchor;
import pergunta.Task;

public class LogicaProposicionalTask implements Task {
	private JPanel window;
	private ArrayList<Expression> expressions;
	private ArrayList<Origin> origins;
	
	public LogicaProposicionalTask(ArrayList<Expression> expressions, ArrayList<Origin> origins) {
		this.setWindow(new Window());
		this.expressions = expressions;
		this.origins = origins;
	}	
	
	public JPanel getWindow() {
		return window;
	}

	private void setWindow(JPanel window) {
		this.window = window;
	}

	@Override
	public JPanel show() {		
		Window win = (Window)this.getWindow();
		randomizeOriginsPosition();
		initializeWindowObjects(win);
		
		return this.getWindow();
	}

	private void randomizeOriginsPosition() {
		ArrayList<Integer> shuffledPositions = new ArrayList<Integer>();
		for (Origin o : origins) {
			if (o.isMovable()) {
				shuffledPositions.add(o.getPos().y);				
			}
		}
		
		Collections.shuffle(shuffledPositions);
		
		for (int i = 0; i < shuffledPositions.size(); i++) {
			if (origins.get(i).isMovable()) {
				origins.get(i).getPos().y = shuffledPositions.get(i);				
			}
		}
	}
	
	private void initializeWindowObjects(Window win) {		
		for (Expression e : expressions) {
			win.addShape(e);
		}
		
		for (Origin o : origins) {
			win.addShape(o);
		}
	}
}
