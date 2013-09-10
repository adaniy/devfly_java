package ui;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

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
		// par défaut, c'est ce bouton qui est "sélectionné" :
		btnVolsProgrammes.setBackground(SystemColor.activeCaption);
		btnVolsProgrammes.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVolsProgrammes.setForeground(Color.BLUE);
		
		btnVolsProgrammes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cf méthode définie ci-dessous
				// On réinitialise le style de chaque bouton et
				// on donne un style particulier au bouton sélectionné.
				styleBouton(btnVolsProgrammes);
				
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
				// cf méthode définie ci-dessous
				// On réinitialise le style de chaque bouton et
				// on donne un style particulier au bouton sélectionné.
				styleBouton(btnVolsEnAttente);
				
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
				// cf méthode définie ci-dessous
				// On réinitialise le style de chaque bouton et
				// on donne un style particulier au bouton sélectionné.
				styleBouton(btnNouveauVol);
				
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				// on affiche le formulaire de création d'un vol
				frame.displayNouveauVol();
			}
		});
		add(btnNouveauVol);

		btnAeroports = new JButton("aéroports");
		btnAeroports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cf méthode définie ci-dessous
				// On réinitialise le style de chaque bouton et
				// on donne un style particulier au bouton sélectionné.
				styleBouton(btnAeroports);
				
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				// on affiche la liste des aéroports
				frame.displayAeroports();
			}
		});
		add(btnAeroports);
		
		btnNouvelAeroport = new JButton("nouvel aéroport");
		btnNouvelAeroport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cf méthode définie ci-dessous
				// On réinitialise le style de chaque bouton et
				// on donne un style particulier au bouton sélectionné.
				styleBouton(btnNouvelAeroport);
				
				// on récupère la frame principale
				FenetrePrincipale frame = 
				(FenetrePrincipale) 
				SwingUtilities.getRoot(PanelBoutons.this);

				// on affiche le formulaire de création d'un aéroport
				frame.displayNouvelAeroport();
			}
		});
		add(btnNouvelAeroport);

		btnDeconnexion = new JButton("déconnexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// cf méthode définie ci-dessous
				// On réinitialise le style de chaque bouton et
				// on donne un style particulier au bouton sélectionné.
				styleBouton(btnDeconnexion);
				
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
	
	// méthode private utilisée uniquement dans ce panel
	// pour réinitiliaser le style de tous les boutons
	// et appliquer un style particulier au bouton sélectionné
	private void styleBouton(JButton monBouton){
		// on rétablit le style initial de chaque bouton :
		btnVolsProgrammes.setBackground(UIManager.getColor("Button.background"));
		btnVolsProgrammes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnVolsProgrammes.setForeground(Color.BLACK);
		
		btnVolsEnAttente.setBackground(UIManager.getColor("Button.background"));
		btnVolsEnAttente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnVolsEnAttente.setForeground(Color.BLACK);
		
		btnNouveauVol.setBackground(UIManager.getColor("Button.background"));
		btnNouveauVol.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNouveauVol.setForeground(Color.BLACK);
		
		btnNouvelAeroport.setBackground(UIManager.getColor("Button.background"));
		btnNouvelAeroport.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNouvelAeroport.setForeground(Color.BLACK);
		
		btnDeconnexion.setBackground(UIManager.getColor("Button.background"));
		btnDeconnexion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDeconnexion.setForeground(Color.BLACK);
		
		btnAeroports.setBackground(UIManager.getColor("Button.background"));
		btnAeroports.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAeroports.setForeground(Color.BLACK);
		
		// On applique un stype particulier au bouton sélectionné :
		monBouton.setBackground(SystemColor.activeCaption);
		monBouton.setFont(new Font("Tahoma", Font.BOLD, 12));
		monBouton.setForeground(Color.BLUE);
	}

}
