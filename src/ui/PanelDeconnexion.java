package ui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;

public class PanelDeconnexion extends JPanel {
	private JLabel labelMessage;
	private JButton btnConfirmer;

	/**
	 * Create the panel.
	 */
	public PanelDeconnexion() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{320, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 55, 0, 60, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		labelMessage = new JLabel("");
		GridBagConstraints gbc_labelMessage = new GridBagConstraints();
		gbc_labelMessage.insets = new Insets(0, 0, 5, 5);
		gbc_labelMessage.gridx = 0;
		gbc_labelMessage.gridy = 0;
		add(labelMessage, gbc_labelMessage);
		
		JLabel lblDeconnexion = new JLabel("Vous êtes sur le point de vous déconnecter !");
		GridBagConstraints gbc_lblDeconnexion = new GridBagConstraints();
		gbc_lblDeconnexion.insets = new Insets(0, 0, 5, 0);
		gbc_lblDeconnexion.gridx = 1;
		gbc_lblDeconnexion.gridy = 3;
		add(lblDeconnexion, gbc_lblDeconnexion);
		
		btnConfirmer = new JButton("confirmer");
		GridBagConstraints gbc_btnConfirmer = new GridBagConstraints();
		gbc_btnConfirmer.gridx = 1;
		gbc_btnConfirmer.gridy = 5;
		add(btnConfirmer, gbc_btnConfirmer);

	}

}