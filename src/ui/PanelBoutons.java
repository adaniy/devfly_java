package ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class PanelBoutons extends JPanel {
	private JButton btnVolsProgrammes;
	private JButton btnVolsEnAttente;
	private JButton btnNouveauVol;
	private JButton btnDestinations;
	private JButton btnDeconnexion;

	/**
	 * Create the panel.
	 */
	public PanelBoutons() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnVolsProgrammes = new JButton("vols programmés");
		add(btnVolsProgrammes);
		
		btnVolsEnAttente = new JButton("vols en attente");
		add(btnVolsEnAttente);
		
		btnNouveauVol = new JButton("nouveau vol");
		add(btnNouveauVol);
		
		btnDestinations = new JButton("destinations");
		add(btnDestinations);
		
		btnDeconnexion = new JButton("déconnexion");
		add(btnDeconnexion);

	}

}
