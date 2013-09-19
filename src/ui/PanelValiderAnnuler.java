package ui;

import javax.swing.JPanel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PanelValiderAnnuler extends JPanel {
	// boutons "valider" et "annuler" qui se retrouvent sur différentes vues
	private JButton btnValider;
	private JButton btnAnnuler;


	public PanelValiderAnnuler() {
		
		btnValider = new JButton("valider");
		add(btnValider);
		
		btnAnnuler = new JButton("annuler");
		add(btnAnnuler);
	
	}

	public JButton getBtnValider() {
		return btnValider;
	}

	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}

}
