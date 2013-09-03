package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;

public class FenetrePrincipale extends JFrame {
	private PanelBienvenue panelBienvenue;
	private PanelBoutons panelBoutons;
	private PanelVols panelVols;
	private PanelDeconnexion panelDeconnexion;
	private PanelNouveauVol panelNouveauVol;
	private PanelNouvelleDestination panelNouvelleDestination;
	private PanelValiderAnnuler panelValiderAnnuler;
	
	private JLabel lblLogoGauche;
	private JLabel lblLogoDroite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipale frame = new FenetrePrincipale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetrePrincipale() {
		setResizable(false);
		setBounds(100, 100, 1000, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelBienvenue = new PanelBienvenue();
		getContentPane().add(panelBienvenue, BorderLayout.CENTER);
		panelVols = new PanelVols();
		panelBoutons = new PanelBoutons();
		
		lblLogoGauche = new JLabel("");
		lblLogoGauche.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/img/logo.jpg")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		
		getContentPane().add(lblLogoGauche, BorderLayout.WEST);
		
		lblLogoDroite = new JLabel("");
		lblLogoDroite.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/img/logo.jpg")));
		getContentPane().add(lblLogoDroite, BorderLayout.EAST);
		
	}

	public PanelBienvenue getPanelBienvenue() {
		return panelBienvenue;
	}

	public PanelBoutons getPanelBoutons() {
		return panelBoutons;
	}

	public PanelVols getPanelVols() {
		return panelVols;
	}

	public JLabel getLblLogoGauche() {
		return lblLogoGauche;
	}

	public JLabel getLblLogoDroite() {
		return lblLogoDroite;
	}

	public PanelDeconnexion getPanelDeconnexion() {
		return panelDeconnexion;
	}

	public PanelNouveauVol getPanelNouveauVol() {
		return panelNouveauVol;
	}

	public PanelNouvelleDestination getPanelNouvelleDestination() {
		return panelNouvelleDestination;
	}

	public PanelValiderAnnuler getPanelValiderAnnuler() {
		return panelValiderAnnuler;
	}	
}
