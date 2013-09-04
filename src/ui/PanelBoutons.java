package ui;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelBoutons extends JPanel {
	private JButton btnVolsProgrammes;
	private JButton btnVolsEnAttente;
	private JButton btnNouveauVol;
	private JButton btnNouvelleDestination;
	private JButton btnDeconnexion;
	private JButton btnDestinations;

	/**
	 * Create the panel.
	 */
	public PanelBoutons() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnVolsProgrammes = new JButton("vols programmés");
		btnVolsProgrammes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				frame.displayVolsProgrammes();
			}
		});
		add(btnVolsProgrammes);

		btnVolsEnAttente = new JButton("vols en attente");
		btnVolsEnAttente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				frame.displayVolsEnAttente();
			}
		});
		add(btnVolsEnAttente);

		btnNouveauVol = new JButton("nouveau vol");
		btnNouveauVol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				frame.displayNouveauVol();
			}
		});
		add(btnNouveauVol);

		btnNouvelleDestination = new JButton("nouvelle destination");
		btnNouvelleDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				frame.displayNouvelleDestination();
			}
		});
		add(btnNouvelleDestination);
		
		btnDestinations = new JButton("destinations");
		btnDestinations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				frame.displayDestinations();
			}
		});
		add(btnDestinations);	

		btnDeconnexion = new JButton("déconnexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				frame.displayDeconnexion();
			}
		});
		add(btnDeconnexion);

	}

}
