package pergunta;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import engine.Window;
import engine.graphics.Rectangle;
import engine.graphics.Text;

public abstract class Task {
	protected JPanel window;
	protected boolean solved = false;
	protected Led led;
	protected ArrayList<String> questionLines;
	protected boolean questionWasPassed = false;
	protected int qtdErros;

	public Task(ArrayList<String> questionLines) {
		this.setQuestionLines(questionLines);
	}

	public JPanel getWindow() {
		return window;
	}

	protected void setWindow(JPanel window) {
		this.window = window;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public Led getLed() {
		return led;
	}

	public void setLed(Led led) {
		this.led = led;
	}

	public ArrayList<String> getQuestionLines() {
		return questionLines;
	}

	public void setQuestionLines(ArrayList<String> questionLines) {
		this.questionLines = questionLines;
	}

	public boolean getQuestionWasPassed() {
		return questionWasPassed;
	}

	public void setQuestionWasPassed(boolean questionWasPassed) {
		this.questionWasPassed = questionWasPassed;
	}

	public int getQtdErros() {
		return qtdErros;
	}

	public void setQtdErros(int qtdErros) {
		this.qtdErros = qtdErros;
	}
	
	public void addErro() {
		this.qtdErros++;
	}

	protected void showQuestion(Window win) {
		setQuestionWasPassed(false);

		int start_y = 10;
		int start_x = 10;
		int line_height = 20;
		for (String line : questionLines) {
			win.addShape(new Text(new Point(start_x, start_y), line));
			start_y += line_height;
		}

		Rectangle goToRiddle = new Rectangle(new Point(100, 190), 100, 20, "Ir para o desafio");
		win.addShape(goToRiddle);
		win.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (goToRiddle.mouseOver(e.getPoint())) {
					setQuestionWasPassed(true);
					show();
				}
			}
		});
	}

	public abstract JPanel show();
}
