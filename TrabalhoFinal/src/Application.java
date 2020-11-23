

import javax.swing.JFrame;
import javax.swing.JPanel;

import pergunta.raciocinio.RaciocinioLogicoTask;

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
    	RaciocinioLogico rl = new RaciocinioLogico("\\src\\enigmas\\enigma1.json");
    	rl.carregarInformacoes();
    	new Application(rl.mostrar());
    }
    
}