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
	private JButton btnNouvelAeroport;
	private JButton btnDeconnexion;
	private JButton btnAeroports;

	/**
	 * Create the panel.
	 */
	public PanelBoutons() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Les boutons sont visibles en haut de la fenêtre.
		// Au clic sur l'un d'eux, on affiche les éléments correspondants.
		
		btnVolsProgrammes = new JButton("vols programmés");
		btnVolsProgrammes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				// on affiche les vols programmés
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

				// on affiche les vols en attente
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

				// on affiche le formulaire de création d'un vol
				frame.displayNouveauVol();
			}
		});
		add(btnNouveauVol);

		btnNouvelAeroport = new JButton("nouvel aéroport");
		btnNouvelAeroport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				// on affiche le formulaire de création d'un aéroport
				frame.displayNouvelAeroport();
			}
		});
		add(btnNouvelAeroport);
		
		btnAeroports = new JButton("aéroports");
		btnAeroports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				// on affiche la liste des aéroports
				frame.displayAeroports();
			}
		});
		add(btnAeroports);	

		btnDeconnexion = new JButton("déconnexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				// on affiche la page de déconnexion
				frame.displayDeconnexion();
			}
		});
		add(btnDeconnexion);

	}

}
