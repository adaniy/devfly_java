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
	private JButton btnDestinations;
	private JButton btnDeconnexion;

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

				frame.displaysVolsProgrammes();
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

				frame.displaysVolsEnAttente();
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

				frame.displaysNouveauVol();
			}
		});
		add(btnNouveauVol);

		btnDestinations = new JButton("destinations");
		btnDestinations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				frame.displaysDestinations();
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

				frame.displaysDeconnexion();
			}
		});
		add(btnDeconnexion);

	}

}
