package ui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class PanelNouveauVol extends JPanel {
	private JTextField textFieldDateDeDepart;
	private JTextField textFieldHeureDeDepart;
	private JTextField textFieldDureeDuVol;
	private JTextField textFieldTarif;
	private JLabel labelMessage;
	private JComboBox comboBoxVilleDeDepart;
	private JComboBox comboBoxVilleDarrivee;
	private PanelValiderAnnuler panelValiderAnnuler;

	/**
	 * Create the panel.
	 */
	public PanelNouveauVol() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		// on joue sur les dimensions de la grille pour positionner les éléments :
		gridBagLayout.columnWidths = new int[]{300, 0, 145, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		labelMessage.setForeground(Color.RED);
		GridBagConstraints gbc_labelMessage = new GridBagConstraints();
		gbc_labelMessage.anchor = GridBagConstraints.NORTHWEST;
		gbc_labelMessage.insets = new Insets(0, 0, 5, 5);
		gbc_labelMessage.gridx = 1;
		gbc_labelMessage.gridy = 0;
		add(labelMessage, gbc_labelMessage);
		
		// Le titre de la page :
		JLabel lblNouveauVol = new JLabel("Nouveau vol");
		lblNouveauVol.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblNouveauVol = new GridBagConstraints();
		gbc_lblNouveauVol.gridwidth = 2;
		gbc_lblNouveauVol.insets = new Insets(0, 0, 5, 0);
		gbc_lblNouveauVol.gridx = 1;
		gbc_lblNouveauVol.gridy = 2;
		add(lblNouveauVol, gbc_lblNouveauVol);
		
		// Les différents éléments du formulaires :
		
		JLabel lblVilleDeDepart = new JLabel("Ville de départ");
		GridBagConstraints gbc_lblVilleDeDepart = new GridBagConstraints();
		gbc_lblVilleDeDepart.anchor = GridBagConstraints.EAST;
		gbc_lblVilleDeDepart.insets = new Insets(0, 0, 5, 5);
		gbc_lblVilleDeDepart.gridx = 1;
		gbc_lblVilleDeDepart.gridy = 3;
		add(lblVilleDeDepart, gbc_lblVilleDeDepart);
		
		comboBoxVilleDeDepart = new JComboBox();
		GridBagConstraints gbc_comboBoxVilleDeDepart = new GridBagConstraints();
		gbc_comboBoxVilleDeDepart.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxVilleDeDepart.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVilleDeDepart.gridx = 2;
		gbc_comboBoxVilleDeDepart.gridy = 3;
		add(comboBoxVilleDeDepart, gbc_comboBoxVilleDeDepart);
		
		JLabel lblVilleDarrivee = new JLabel("Ville d'arrivée");
		GridBagConstraints gbc_lblVilleDarrivee = new GridBagConstraints();
		gbc_lblVilleDarrivee.anchor = GridBagConstraints.EAST;
		gbc_lblVilleDarrivee.insets = new Insets(0, 0, 5, 5);
		gbc_lblVilleDarrivee.gridx = 1;
		gbc_lblVilleDarrivee.gridy = 4;
		add(lblVilleDarrivee, gbc_lblVilleDarrivee);
		
		comboBoxVilleDarrivee = new JComboBox();
		GridBagConstraints gbc_comboBoxVilleDarrivee = new GridBagConstraints();
		gbc_comboBoxVilleDarrivee.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxVilleDarrivee.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVilleDarrivee.gridx = 2;
		gbc_comboBoxVilleDarrivee.gridy = 4;
		add(comboBoxVilleDarrivee, gbc_comboBoxVilleDarrivee);
		
		JLabel lblDateDeDepart = new JLabel("Date de départ");
		GridBagConstraints gbc_lblDateDeDepart = new GridBagConstraints();
		gbc_lblDateDeDepart.anchor = GridBagConstraints.EAST;
		gbc_lblDateDeDepart.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateDeDepart.gridx = 1;
		gbc_lblDateDeDepart.gridy = 5;
		add(lblDateDeDepart, gbc_lblDateDeDepart);
		
		textFieldDateDeDepart = new JTextField();
		GridBagConstraints gbc_textFieldDateDeDepart = new GridBagConstraints();
		gbc_textFieldDateDeDepart.anchor = GridBagConstraints.WEST;
		gbc_textFieldDateDeDepart.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDateDeDepart.gridx = 2;
		gbc_textFieldDateDeDepart.gridy = 5;
		add(textFieldDateDeDepart, gbc_textFieldDateDeDepart);
		textFieldDateDeDepart.setColumns(10);
		
		JLabel lblHeureDeDepart = new JLabel("Heure de départ");
		GridBagConstraints gbc_lblHeureDeDepart = new GridBagConstraints();
		gbc_lblHeureDeDepart.anchor = GridBagConstraints.EAST;
		gbc_lblHeureDeDepart.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeureDeDepart.gridx = 1;
		gbc_lblHeureDeDepart.gridy = 6;
		add(lblHeureDeDepart, gbc_lblHeureDeDepart);
		
		textFieldHeureDeDepart = new JTextField();
		GridBagConstraints gbc_textFieldHeureDeDepart = new GridBagConstraints();
		gbc_textFieldHeureDeDepart.anchor = GridBagConstraints.WEST;
		gbc_textFieldHeureDeDepart.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHeureDeDepart.gridx = 2;
		gbc_textFieldHeureDeDepart.gridy = 6;
		add(textFieldHeureDeDepart, gbc_textFieldHeureDeDepart);
		textFieldHeureDeDepart.setColumns(10);
		
		JLabel lblDureeDuVol = new JLabel("Durée du vol en minutes");
		GridBagConstraints gbc_lblDureeDuVol = new GridBagConstraints();
		gbc_lblDureeDuVol.anchor = GridBagConstraints.EAST;
		gbc_lblDureeDuVol.insets = new Insets(0, 0, 5, 5);
		gbc_lblDureeDuVol.gridx = 1;
		gbc_lblDureeDuVol.gridy = 7;
		add(lblDureeDuVol, gbc_lblDureeDuVol);
		
		textFieldDureeDuVol = new JTextField();
		GridBagConstraints gbc_textFieldDureeDuVol = new GridBagConstraints();
		gbc_textFieldDureeDuVol.anchor = GridBagConstraints.WEST;
		gbc_textFieldDureeDuVol.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDureeDuVol.gridx = 2;
		gbc_textFieldDureeDuVol.gridy = 7;
		add(textFieldDureeDuVol, gbc_textFieldDureeDuVol);
		textFieldDureeDuVol.setColumns(10);
		
		JLabel lblTarif = new JLabel("Tarif en euros");
		GridBagConstraints gbc_lblTarif = new GridBagConstraints();
		gbc_lblTarif.anchor = GridBagConstraints.EAST;
		gbc_lblTarif.insets = new Insets(0, 0, 5, 5);
		gbc_lblTarif.gridx = 1;
		gbc_lblTarif.gridy = 8;
		add(lblTarif, gbc_lblTarif);
		
		textFieldTarif = new JTextField();
		GridBagConstraints gbc_textFieldTarif = new GridBagConstraints();
		gbc_textFieldTarif.anchor = GridBagConstraints.WEST;
		gbc_textFieldTarif.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldTarif.gridx = 2;
		gbc_textFieldTarif.gridy = 8;
		add(textFieldTarif, gbc_textFieldTarif);
		textFieldTarif.setColumns(10);
		
		panelValiderAnnuler = new PanelValiderAnnuler();
		GridBagConstraints gbc_panelValiderAnnuler = new GridBagConstraints();
		gbc_panelValiderAnnuler.fill = GridBagConstraints.BOTH;
		gbc_panelValiderAnnuler.gridx = 2;
		gbc_panelValiderAnnuler.gridy = 10;
		add(panelValiderAnnuler, gbc_panelValiderAnnuler);

	}

}
