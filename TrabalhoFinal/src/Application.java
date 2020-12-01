


import javax.swing.JFrame;
import javax.swing.JPanel;

import poointerfaces.Modulo;

@SuppressWarnings("serial")
public class Application extends JFrame {
	public Application(JPanel window) {
		setSize(300, 250);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(window);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Modulo m = new Modulo();
		new Application(m.getModulePanel(1));
		new Application(m.getModulePanel(2));
		new Application(m.getModulePanel(3));
		new Application(m.getModulePanel(4));
	}

}