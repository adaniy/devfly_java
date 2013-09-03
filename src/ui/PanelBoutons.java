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
				
				// on nettoie la page en rendant invisible tous les autres éléments :
				frame.getPanelDeconnexion().setVisible(false);
				frame.getPanelBienvenue().setVisible(false);
				frame.getPanelBoutons().setVisible(false);
				frame.getPanelNouveauVol().setVisible(false);
				frame.getPanelNouvelleDestination().setVisible(false);
				frame.getPanelValiderAnnuler().setVisible(false);
				frame.getLblLogoDroite().setVisible(false);
				frame.getLblLogoGauche().setVisible(false);				
				
				// on supprime ces mêmes éléments
				frame.getContentPane().remove(frame.getPanelDeconnexion());
				frame.getContentPane().remove(frame.getPanelBienvenue());
				frame.getContentPane().remove(frame.getPanelBoutons());
				frame.getContentPane().remove(frame.getPanelNouveauVol());
				frame.getContentPane().remove(frame.getPanelNouvelleDestination());
				frame.getContentPane().remove(frame.getPanelValiderAnnuler());

				// on ajoute le panel de présentation des vols
				frame.getContentPane().add(frame.getPanelVols(), BorderLayout.CENTER);
				
				// on rend visible les éléments ajoutés
				// et on fait un repaint pour avoir le nouvel affichage
				frame.getPanelVols().setVisible(true);
				frame.getContentPane().repaint();
			}
		});
		add(btnVolsProgrammes);
		
		btnVolsEnAttente = new JButton("vols en attente");
		add(btnVolsEnAttente);
		
		btnNouveauVol = new JButton("nouveau vol");
		add(btnNouveauVol);
		
		btnDestinations = new JButton("destinations");
		add(btnDestinations);
		
		btnDeconnexion = new JButton("déconnexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
						(FenetrePrincipale) 
						SwingUtilities.getRoot(PanelBoutons.this);
				
				// on nettoie la page en rendant invisible tous les autres éléments :
				frame.getPanelVols().setVisible(false);
//				frame.getPanelBienvenue().setVisible(false);
//				frame.getPanelBoutons().setVisible(false);
				frame.getPanelNouveauVol().setVisible(false);
				frame.getPanelNouvelleDestination().setVisible(false);
				frame.getPanelValiderAnnuler().setVisible(false);
				frame.getLblLogoDroite().setVisible(false);
				frame.getLblLogoGauche().setVisible(false);
//				frame.getPanelDeconnexion().setVisible(false);
				
				// on supprime ces mêmes éléments
				frame.getContentPane().remove(frame.getPanelVols());
//				frame.getContentPane().remove(frame.getPanelBienvenue());
//				frame.getContentPane().remove(frame.getPanelBoutons());
				frame.getContentPane().remove(frame.getPanelNouveauVol());
				frame.getContentPane().remove(frame.getPanelNouvelleDestination());
				frame.getContentPane().remove(frame.getPanelValiderAnnuler());
//				frame.getContentPane().remove(frame.getPanelDeconnexion());

				// on ajoute le panel de déconnexion + le PanelBoutons
				frame.getContentPane().add(frame.getPanelDeconnexion(), BorderLayout.CENTER);
				frame.getContentPane().add(frame.getPanelBoutons(), BorderLayout.NORTH);
				
				// on rend visible les éléments ajoutés
				// et on fait un repaint pour avoir le nouvel affichage
				frame.getPanelDeconnexion().setVisible(true);
				frame.getPanelBoutons().setVisible(true);
//				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
			}
		});
		add(btnDeconnexion);

	}

}
