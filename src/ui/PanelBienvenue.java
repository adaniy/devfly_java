package ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPasswordField;

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
		gridBagLayout.columnWidths = new int[]{263, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblBienvenue = new JLabel("Bienvenue !");
		lblBienvenue.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblBienvenue = new GridBagConstraints();
		gbc_lblBienvenue.gridwidth = 2;
		gbc_lblBienvenue.insets = new Insets(0, 0, 5, 5);
		gbc_lblBienvenue.gridx = 0;
		gbc_lblBienvenue.gridy = 0;
		add(lblBienvenue, gbc_lblBienvenue);

		labelMessage = new JLabel("");
		GridBagConstraints gbc_labelMessage = new GridBagConstraints();
		gbc_labelMessage.insets = new Insets(0, 0, 5, 5);
		gbc_labelMessage.gridx = 0;
		gbc_labelMessage.gridy = 1;
		add(labelMessage, gbc_labelMessage);

		JLabel lblIdentifiant = new JLabel("identifiant");
		GridBagConstraints gbc_lblIdentifiant = new GridBagConstraints();
		gbc_lblIdentifiant.anchor = GridBagConstraints.EAST;
		gbc_lblIdentifiant.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdentifiant.gridx = 0;
		gbc_lblIdentifiant.gridy = 3;
		add(lblIdentifiant, gbc_lblIdentifiant);

		textFieldIdentifiant = new JTextField();
		GridBagConstraints gbc_textFieldIdentifiant = new GridBagConstraints();
		gbc_textFieldIdentifiant.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIdentifiant.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldIdentifiant.gridx = 1;
		gbc_textFieldIdentifiant.gridy = 3;
		add(textFieldIdentifiant, gbc_textFieldIdentifiant);
		textFieldIdentifiant.setColumns(10);

		JLabel lblMotDePasse = new JLabel("mot de passe");
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.anchor = GridBagConstraints.EAST;
		gbc_lblMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePasse.gridx = 0;
		gbc_lblMotDePasse.gridy = 4;
		add(lblMotDePasse, gbc_lblMotDePasse);

		passwordFieldMotDePasse = new JPasswordField();
		GridBagConstraints gbc_passwordFieldMotDePasse = new GridBagConstraints();
		gbc_passwordFieldMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldMotDePasse.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldMotDePasse.gridx = 1;
		gbc_passwordFieldMotDePasse.gridy = 4;
		add(passwordFieldMotDePasse, gbc_passwordFieldMotDePasse);

		panelValiderAnnuler = new PanelValiderAnnuler();
		GridBagConstraints gbc_panelValiderAnnuler = new GridBagConstraints();
		gbc_panelValiderAnnuler.insets = new Insets(0, 0, 5, 5);
		gbc_panelValiderAnnuler.fill = GridBagConstraints.BOTH;
		gbc_panelValiderAnnuler.gridx = 1;
		gbc_panelValiderAnnuler.gridy = 5;
		add(panelValiderAnnuler, gbc_panelValiderAnnuler);

		panelValiderAnnuler.getBtnValider().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelBienvenue.this.setVisible(false);

				// on récupère la frame principale
				FenetrePrincipale frame = 
						(FenetrePrincipale) 
						SwingUtilities.getRoot(PanelBienvenue.this);

				// on supprime le panel de bienvenue et les 2 logos
				frame.getContentPane().remove(PanelBienvenue.this);
				frame.getContentPane().remove(frame.getLblLogoGauche());
				frame.getContentPane().remove(frame.getLblLogoDroite());

				// on ajoute les boutons + le panel avec l'affichage des vols
				frame.getContentPane().add(frame.getPanelVols(), BorderLayout.CENTER);
				frame.getContentPane().add(frame.getPanelBoutons(), BorderLayout.NORTH);

				// on rend visible les éléments ajoutés
				// et on fait un repaint pour avoir le nouvel affichage
				frame.getPanelVols().setVisible(true);
				frame.getPanelBoutons().setVisible(true);
				frame.getContentPane().repaint();
			}
		});

	}
}
