package pergunta.raciocinio;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

import engine.Window;
import engine.graphics.Text;
import pergunta.Led;
import pergunta.Task;

public class RaciocinioLogicoTask extends Task {
	
	private ArrayList<Person> people;
	private Boat boat;
	private Button button;
	private Person elementPressed;

	public RaciocinioLogicoTask(ArrayList<Person> people, Boat boat) {
		this.setWindow(new Window());
		this.people = people;
		this.boat = boat;
	}
	
	public JPanel show() {		
		Window win = (Window)this.getWindow();
		initializeWindowObjects(win);
		
		win.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				for (Person person : people) {
					if (person.mouseOver(e.getPoint())) {
						elementPressed = person;
						elementPressed.setPos(e.getPoint());
						win.repaint();
						break;
					}								
				}
				if (button.mouseOver(e.getPoint())) {
					button.crossRiver(win, boat);
					if (gameIsOver()) {
						endGame(win);
					}
				}
			}
		});
		
		win.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (elementPressed != null) {
					boolean overlaps = elementPressed.isOverlapping(boat);
					boolean containsElement = boat.getPeople().contains(elementPressed);
					if (overlaps && !containsElement) {
						if (!boat.addPerson(elementPressed)) {
							elementPressed.setPos(elementPressed.getInitialPos());
						}
					} else if (!overlaps && containsElement) {
						boat.removePerson(elementPressed);
					}
					if (!overlaps) {
						elementPressed.setPos(elementPressed.getInitialPos());
					}
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
		
		return this.getWindow();
	}

	private void initializeWindowObjects(Window win) {
		button = new Button(new Point(50, 190), 200, 25, "Atravessar");
		
		for (Person person : people) {
			win.addShape(person);			
		}
		win.addShape(boat);
		win.addShape(button);
		
		setLed(new Led(new Point(250, 5), 30, 30, "Verificar"));
		win.addShape(getLed());
	}
	
	private boolean gameIsOver() {
		boolean over = true;
		for (Person p : people) {
			over = over && p.getCurrentSide() == Side.Right;
		}
		return over;
	}
	
	private void endGame(Window win) {
		win.getShapes().clear();
		win.addShape(getLed());
		getLed().setSolved(true);
		this.setSolved(true);
		win.repaint();
	}
}
