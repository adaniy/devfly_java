package ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import java.awt.Color;

public class PanelBienvenue extends JPanel {
	private JTextField textFieldIdentifiant;
	private JLabel labelMessage;
	private JPasswordField passwordFieldMotDePasse;
	private PanelValiderAnnuler panelValiderAnnuler;

	/**
	 * Create the panel.
	 */
	public PanelBienvenue() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		// on joue sur les dimensions de la grille pour positionner les éléments :
		gridBagLayout.columnWidths = new int[]{225, 0, 0, 0};
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
		gbc_lblBienvenue.gridy = 0;
		add(lblBienvenue, gbc_lblBienvenue);

		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		labelMessage.setForeground(Color.RED);
		GridBagConstraints gbc_labelMessage = new GridBagConstraints();
		gbc_labelMessage.insets = new Insets(0, 0, 5, 5);
		gbc_labelMessage.gridx = 1;
		gbc_labelMessage.gridy = 1;
		add(labelMessage, gbc_labelMessage);

		// Label contenant le texte "identifiant"
		JLabel lblIdentifiant = new JLabel("identifiant");
		GridBagConstraints gbc_lblIdentifiant = new GridBagConstraints();
		gbc_lblIdentifiant.anchor = GridBagConstraints.EAST;
		gbc_lblIdentifiant.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdentifiant.gridx = 0;
		gbc_lblIdentifiant.gridy = 3;
		add(lblIdentifiant, gbc_lblIdentifiant);

		// Champ texte pour l'identifiant :
		textFieldIdentifiant = new JTextField();
		GridBagConstraints gbc_textFieldIdentifiant = new GridBagConstraints();
		gbc_textFieldIdentifiant.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIdentifiant.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldIdentifiant.gridx = 1;
		gbc_textFieldIdentifiant.gridy = 3;
		add(textFieldIdentifiant, gbc_textFieldIdentifiant);
		textFieldIdentifiant.setColumns(10);

		// Label contenant le texte "mot de passe"
		JLabel lblMotDePasse = new JLabel("mot de passe");
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.anchor = GridBagConstraints.EAST;
		gbc_lblMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePasse.gridx = 0;
		gbc_lblMotDePasse.gridy = 4;
		add(lblMotDePasse, gbc_lblMotDePasse);

		// Champ pour le mot de passe, les caractères saisis ne sont pas affichés :
		passwordFieldMotDePasse = new JPasswordField();
		GridBagConstraints gbc_passwordFieldMotDePasse = new GridBagConstraints();
		gbc_passwordFieldMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldMotDePasse.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldMotDePasse.gridx = 1;
		gbc_passwordFieldMotDePasse.gridy = 4;
		add(passwordFieldMotDePasse, gbc_passwordFieldMotDePasse);

		panelValiderAnnuler = new PanelValiderAnnuler();
		panelValiderAnnuler.getBtnAnnuler().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Un clic sur le bouton "annuler" efface les éléments saisis.
				getTextFieldIdentifiant().setText("");
				getPasswordFieldMotDePasse().setText("");
			}
		});
		// On positionne le panel avec les boutons "valider" et "annuler" :
		GridBagConstraints gbc_panelValiderAnnuler = new GridBagConstraints();
		gbc_panelValiderAnnuler.insets = new Insets(0, 0, 5, 5);
		gbc_panelValiderAnnuler.fill = GridBagConstraints.BOTH;
		gbc_panelValiderAnnuler.gridx = 1;
		gbc_panelValiderAnnuler.gridy = 5;
		add(panelValiderAnnuler, gbc_panelValiderAnnuler);

		// Action déclenchée au clic sur "valider" :
		panelValiderAnnuler.getBtnValider().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// on récupère la frame principale
				FenetrePrincipale frame = 
						(FenetrePrincipale) 
						SwingUtilities.getRoot(PanelBienvenue.this);
				
				// on utilise la méthode définie dans la frame pour 
				// nettoyer la page et afficher les vols programmés
				frame.displayVolsProgrammes();
			}
		});

	}

	public JTextField getTextFieldIdentifiant() {
		return textFieldIdentifiant;
	}

	public void setTextFieldIdentifiant(JTextField textFieldIdentifiant) {
		this.textFieldIdentifiant = textFieldIdentifiant;
	}

	public JLabel getLabelMessage() {
		return labelMessage;
	}

	public void setLabelMessage(JLabel labelMessage) {
		this.labelMessage = labelMessage;
	}

	public JPasswordField getPasswordFieldMotDePasse() {
		return passwordFieldMotDePasse;
	}

	public void setPasswordFieldMotDePasse(JPasswordField passwordFieldMotDePasse) {
		this.passwordFieldMotDePasse = passwordFieldMotDePasse;
	}
}
