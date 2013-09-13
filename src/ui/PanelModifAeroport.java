package ui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelModifAeroport extends JPanel {
	private JTextField textFieldCode;
	private JTextField textFieldVille;
	private JTextField textFieldPays;

	public JTextField getTextFieldCode() {
		return textFieldCode;
	}

	public JTextField getTextFieldVille() {
		return textFieldVille;
	}

	public JTextField getTextFieldPays() {
		return textFieldPays;
	}

	/**
	 * Create the panel.
	 */
	public PanelModifAeroport() {
		// on joue sur les dimensions de la grille pour positionner les éléments :
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 46, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		// Label qui pourra contenir les différents messages à afficher :
		JLabel lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(Color.RED);
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.gridwidth = 2;
		gbc_lblMessage.anchor = GridBagConstraints.EAST;
		gbc_lblMessage.insets = new Insets(0, 0, 5, 5);
		gbc_lblMessage.gridx = 1;
		gbc_lblMessage.gridy = 1;
		add(lblMessage, gbc_lblMessage);
		
		// Les différents éléments du formulaires :
		
		JLabel lblCode = new JLabel("code AITA");
		lblCode.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 1;
		gbc_lblCode.gridy = 2;
		add(lblCode, gbc_lblCode);
		
		textFieldCode = new JTextField();
		GridBagConstraints gbc_textFieldCode = new GridBagConstraints();
		gbc_textFieldCode.gridwidth = 2;
		gbc_textFieldCode.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCode.gridx = 2;
		gbc_textFieldCode.gridy = 2;
		add(textFieldCode, gbc_textFieldCode);
		textFieldCode.setColumns(10);
		
		JLabel lblVille = new JLabel("ville");
		lblVille.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 1;
		gbc_lblVille.gridy = 3;
		add(lblVille, gbc_lblVille);
		
		textFieldVille = new JTextField();
		GridBagConstraints gbc_textFieldVille = new GridBagConstraints();
		gbc_textFieldVille.gridwidth = 2;
		gbc_textFieldVille.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldVille.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVille.gridx = 2;
		gbc_textFieldVille.gridy = 3;
		add(textFieldVille, gbc_textFieldVille);
		textFieldVille.setColumns(10);
		
		JLabel lblPays = new JLabel("pays");
		lblPays.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPays = new GridBagConstraints();
		gbc_lblPays.insets = new Insets(0, 0, 5, 5);
		gbc_lblPays.gridx = 1;
		gbc_lblPays.gridy = 4;
		add(lblPays, gbc_lblPays);
		
		textFieldPays = new JTextField();
		GridBagConstraints gbc_textFieldPays = new GridBagConstraints();
		gbc_textFieldPays.gridwidth = 2;
		gbc_textFieldPays.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPays.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPays.gridx = 2;
		gbc_textFieldPays.gridy = 4;
		add(textFieldPays, gbc_textFieldPays);
		textFieldPays.setColumns(10);
		
		JButton btnMettreAJour = new JButton("mettre à jour");
		GridBagConstraints gbc_btnMettreAJour = new GridBagConstraints();
		gbc_btnMettreAJour.insets = new Insets(0, 0, 5, 5);
		gbc_btnMettreAJour.gridx = 2;
		gbc_btnMettreAJour.gridy = 5;
		add(btnMettreAJour, gbc_btnMettreAJour);
		
		JButton btnAnnuler = new JButton("annuler");
		GridBagConstraints gbc_btnAnnuler = new GridBagConstraints();
		gbc_btnAnnuler.insets = new Insets(0, 0, 5, 5);
		gbc_btnAnnuler.gridx = 3;
		gbc_btnAnnuler.gridy = 5;
		add(btnAnnuler, gbc_btnAnnuler);
		
		JButton btnSupprimer = new JButton("supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSupprimer.setBackground(Color.PINK);
		btnSupprimer.setForeground(Color.RED);
		GridBagConstraints gbc_btnSupprimer = new GridBagConstraints();
		gbc_btnSupprimer.insets = new Insets(0, 0, 0, 5);
		gbc_btnSupprimer.gridx = 2;
		gbc_btnSupprimer.gridy = 6;
		add(btnSupprimer, gbc_btnSupprimer);

	}

}
