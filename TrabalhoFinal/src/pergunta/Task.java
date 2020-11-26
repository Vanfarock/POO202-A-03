package pergunta;

import javax.swing.JPanel;

public abstract class Task {
	protected JPanel window;
	private boolean solved = false;
	private Led led;

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

	public abstract JPanel show();
}
