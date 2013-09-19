package ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingUtilities;

import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class PanelBienvenue extends JPanel {

	public PanelBienvenue() {
		addMouseListener(new MouseAdapter() {
			// dès qu'on clique sur la page, on entre dans l'application
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// on récupère la frame principale
				FenetrePrincipale frame = 
						(FenetrePrincipale) 
						SwingUtilities.getRoot(PanelBienvenue.this);
				
				// on utilise la méthode définie dans la frame pour 
				// nettoyer la page et afficher les vols programmés
				frame.displayVolsProgrammes();
			}
		});
		GridBagLayout gridBagLayout = new GridBagLayout();
		// on joue sur les dimensions de la grille pour positionner les éléments :
		gridBagLayout.columnWidths = new int[]{160, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{60, 45, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		// Style, taille et positionnement du mot "Bienvenue" :
		JLabel lblBienvenue = new JLabel("Bienvenue !");
		lblBienvenue.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblBienvenue = new GridBagConstraints();
		gbc_lblBienvenue.insets = new Insets(0, 0, 5, 5);
		gbc_lblBienvenue.gridx = 1;
		gbc_lblBienvenue.gridy = 1;
		add(lblBienvenue, gbc_lblBienvenue);
		
		// Texte "cliquez..."
		JLabel lblCliquez = new JLabel("~~ Cliquez n'importe où pour entrez ~~");
		lblCliquez.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblCliquez = new GridBagConstraints();
		gbc_lblCliquez.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliquez.gridx = 1;
		gbc_lblCliquez.gridy = 3;
		add(lblCliquez, gbc_lblCliquez);
		
		// Texte contenant des informations diverses
		JLabel lblInfos = new JLabel("<html><p>© DEV-FLY 2013 - Site web <i>http://dev-fly.fr</i></p></html>");
		GridBagConstraints gbc_lblInfos = new GridBagConstraints();
		gbc_lblInfos.insets = new Insets(0, 0, 5, 5);
		gbc_lblInfos.gridx = 1;
		gbc_lblInfos.gridy = 5;
		add(lblInfos, gbc_lblInfos);

	}
}
