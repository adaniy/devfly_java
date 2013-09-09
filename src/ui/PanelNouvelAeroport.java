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

public class PanelNouvelAeroport extends JPanel {
	private JTextField textFieldVille;
	private JTextField textFieldPays;
	private JTextField textFieldAita;
	private JLabel labelMessage;
	private PanelValiderAnnuler panelValiderAnnuler;

	/**
	 * Create the panel.
	 */
	public PanelNouvelAeroport() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		// on joue sur les dimensions de la grille pour positionner les éléments :
		gridBagLayout.columnWidths = new int[]{365, 0, 145, 0};
		gridBagLayout.rowHeights = new int[]{0, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		GridBagConstraints gbc_labelMessage = new GridBagConstraints();
		gbc_labelMessage.insets = new Insets(0, 0, 5, 5);
		gbc_labelMessage.gridx = 0;
		gbc_labelMessage.gridy = 0;
		add(labelMessage, gbc_labelMessage);
		
		// Les différents éléments du formulaires :
		
		JLabel lblNouvelleDestination = new JLabel("Nouvelle destination");
		lblNouvelleDestination.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblNouvelleDestination = new GridBagConstraints();
		gbc_lblNouvelleDestination.gridwidth = 2;
		gbc_lblNouvelleDestination.insets = new Insets(0, 0, 5, 0);
		gbc_lblNouvelleDestination.gridx = 1;
		gbc_lblNouvelleDestination.gridy = 1;
		add(lblNouvelleDestination, gbc_lblNouvelleDestination);
		
		JLabel lblCodeAita = new JLabel("Code AITA");
		GridBagConstraints gbc_lblCodeAita = new GridBagConstraints();
		gbc_lblCodeAita.anchor = GridBagConstraints.EAST;
		gbc_lblCodeAita.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodeAita.gridx = 1;
		gbc_lblCodeAita.gridy = 2;
		add(lblCodeAita, gbc_lblCodeAita);
		
		textFieldAita = new JTextField();
		GridBagConstraints gbc_textFieldAita = new GridBagConstraints();
		gbc_textFieldAita.anchor = GridBagConstraints.WEST;
		gbc_textFieldAita.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAita.gridx = 2;
		gbc_textFieldAita.gridy = 2;
		add(textFieldAita, gbc_textFieldAita);
		textFieldAita.setColumns(10);
		
		JLabel lblVille = new JLabel("Ville");
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.anchor = GridBagConstraints.EAST;
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 1;
		gbc_lblVille.gridy = 3;
		add(lblVille, gbc_lblVille);
		
		textFieldVille = new JTextField();
		GridBagConstraints gbc_textFieldVille = new GridBagConstraints();
		gbc_textFieldVille.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVille.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldVille.gridx = 2;
		gbc_textFieldVille.gridy = 3;
		add(textFieldVille, gbc_textFieldVille);
		textFieldVille.setColumns(10);
		
		JLabel lblPays = new JLabel("Pays");
		GridBagConstraints gbc_lblPays = new GridBagConstraints();
		gbc_lblPays.anchor = GridBagConstraints.EAST;
		gbc_lblPays.insets = new Insets(0, 0, 5, 5);
		gbc_lblPays.gridx = 1;
		gbc_lblPays.gridy = 4;
		add(lblPays, gbc_lblPays);
		
		textFieldPays = new JTextField();
		GridBagConstraints gbc_textFieldPays = new GridBagConstraints();
		gbc_textFieldPays.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPays.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPays.gridx = 2;
		gbc_textFieldPays.gridy = 4;
		add(textFieldPays, gbc_textFieldPays);
		textFieldPays.setColumns(10);
		
		panelValiderAnnuler = new PanelValiderAnnuler();
		GridBagConstraints gbc_panelValiderAnnuler = new GridBagConstraints();
		gbc_panelValiderAnnuler.insets = new Insets(0, 0, 5, 0);
		gbc_panelValiderAnnuler.fill = GridBagConstraints.BOTH;
		gbc_panelValiderAnnuler.gridx = 2;
		gbc_panelValiderAnnuler.gridy = 7;
		add(panelValiderAnnuler, gbc_panelValiderAnnuler);

	}

}
