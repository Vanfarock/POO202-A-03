package pergunta.logica;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import engine.Window;
import pergunta.Led;
import pergunta.Task;
import poointerfaces.Enigma;

public class LogicaTask extends Task {
	private ArrayList<Expression> expressions;
	private ArrayList<Origin> origins;
	private Origin elementPressed;
	private Button button;
	
	public LogicaTask(ArrayList<String> questionLines, ArrayList<Expression> expressions, ArrayList<Origin> origins, Enigma enigma) {
		super(questionLines, enigma);
		this.setWindow(new Window());
		this.expressions = expressions;
		this.origins = origins;
	}	
	
	public JPanel show() {		
		Window win = (Window)this.getWindow();
		
		if (getQuestionWasPassed()) {
			initializeWindowObjects(win);
			
			win.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (button.mouseOver(e.getPoint())) {
						if (button.questionIsCorrect(expressions, origins)) {
							endGame(win);							
						} else {
							try {
								addErro();
							} catch (IOException | ParseException e1) {
								e1.printStackTrace();
							}
						}
					}
					
					for (Origin o : origins) {
						if (o.mouseOver(e.getPoint())) {
							elementPressed = o;
							elementPressed.setPos(e.getPoint());
							win.repaint();
							break;
						}								
					}
				}
			});
			
			win.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (elementPressed != null) {
						for (Origin o : origins) {
							if (o != elementPressed && o.mouseOver(e.getPoint())) {
								elementPressed.switchPosition(o);
								win.repaint();
								return;							
							}
						}
						elementPressed.resetPos();
						elementPressed = null;
						win.repaint();
					}
				}
			});
			
			win.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					if (elementPressed != null) {
						elementPressed.setPos(e.getPoint());
						win.repaint();
					}
				}
			});
		} else {
			this.showQuestion(win);
		}
		
		return this.getWindow();
	}
	
	private void initializeWindowObjects(Window win) {
		win.getShapes().clear();
		win.removeListeners();
		
		for (Expression e : expressions) {
			win.addShape(e);
		}
		
		for (Origin o : origins) {
			win.addShape(o);
		}
		
		button = new Button(new Point(220, 185), 60, 30, "Verificar");
		win.addShape(button);
		
		setLed(new Led(new Point(250, 5), 30, 30, "Verificar"));
		win.addShape(getLed());
		win.repaint();
	}
	
	private void endGame(Window win) {
		win.getShapes().clear();
		win.addShape(getLed());
		this.setSolved(true);
		this.getLed().setSolved(true);
		win.repaint();
	}
}
