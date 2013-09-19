package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.sql.SQLException;

import javax.swing.ImageIcon;

import java.awt.GridBagLayout;


@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame {
	private PanelBienvenue panelBienvenue;
	private PanelBoutons panelBoutons;
	private PanelVolsProgrammes panelVolsProgrammes;
	private PanelVolsEnAttente panelVolsEnAttente;
	private PanelDeconnexion panelDeconnexion;
	private PanelNouveauVol panelNouveauVol;
	private PanelNouvelAeroport panelNouvelAeroport;
	private PanelAeroports panelAeroports;
	private PanelModifAeroport panelModifAeroport;
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
	 * @throws SQLException 
	 */
	public FenetrePrincipale() throws SQLException {
		// la fenêtre n'est pas redimensionnable
		setResizable(false);
		// on fixe sa position et sa taille :
		setBounds(100, 100, 1000, 600);
		// le processus s'arrête à la fermeture de la fenêtre :
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelBienvenue = new PanelBienvenue();
		GridBagLayout gridBagLayout = (GridBagLayout) panelBienvenue.getLayout();
		gridBagLayout.rowHeights = new int[]{130, 135, 0, 0, 0, 0, 0};
		// le panelBienvenue est visible par défaut, on l'ajoute au centre du ContentPane
		getContentPane().add(panelBienvenue, BorderLayout.CENTER);

		// par défaut, le logo de la compagnie apparait à gauche et à droite
		lblLogoGauche = new JLabel("");
		lblLogoGauche.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/img/logo.jpg")));
		getContentPane().add(lblLogoGauche, BorderLayout.WEST);

		lblLogoDroite = new JLabel("");
		lblLogoDroite.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/img/logo.jpg")));
		getContentPane().add(lblLogoDroite, BorderLayout.EAST);

		// on instancie les panels non visibles au départ :
		panelDeconnexion = new PanelDeconnexion();
		panelNouveauVol = new PanelNouveauVol();
		panelNouvelAeroport = new PanelNouvelAeroport();
		panelAeroports = new PanelAeroports();
		panelValiderAnnuler = new PanelValiderAnnuler();
		panelVolsProgrammes = new PanelVolsProgrammes();
		panelVolsEnAttente = new PanelVolsEnAttente();
		panelModifAeroport = new PanelModifAeroport();
		panelBoutons = new PanelBoutons();
	}

	public PanelBienvenue getPanelBienvenue() {
		return panelBienvenue;
	}

	public PanelBoutons getPanelBoutons() {
		return panelBoutons;
	}

	public PanelVolsProgrammes getPanelVolsProgrammes() {
		return panelVolsProgrammes;
	}	

	public PanelVolsEnAttente getPanelVolsEnAttente() {
		return panelVolsEnAttente;
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

	public PanelNouvelAeroport getPanelNouvelAeroport() {
		return panelNouvelAeroport;
	}

	public PanelValiderAnnuler getPanelValiderAnnuler() {
		return panelValiderAnnuler;
	}
	
	public PanelAeroports getPanelAeroports() {
		return panelAeroports;
	}

	public PanelModifAeroport getPanelModifAeroport() {
		return panelModifAeroport;
	}

	public void hideElements()
	{
		// on nettoie la page en rendant invisible tous les éléments :
		getPanelDeconnexion().setVisible(false);
		getPanelBienvenue().setVisible(false);
		getPanelBoutons().setVisible(false);
		getPanelNouveauVol().setVisible(false);
		getPanelNouvelAeroport().setVisible(false);
		getPanelAeroports().setVisible(false);
		getPanelValiderAnnuler().setVisible(false);
		getPanelVolsProgrammes().setVisible(false);
		getPanelVolsEnAttente().setVisible(false);
		getPanelModifAeroport().setVisible(false);
		getLblLogoDroite().setVisible(false);
		getLblLogoGauche().setVisible(false);				

		// on supprime ces mêmes éléments
		getContentPane().removeAll();
	}

	public void displayVolsProgrammes()
	{
		hideElements();
		// on ajoute le panel de présentation des vols programmés + les boutons
		getContentPane().add(getPanelBoutons(), BorderLayout.NORTH);
		getContentPane().add(getPanelVolsProgrammes(), BorderLayout.CENTER);

		// on rend visible les éléments ajoutés
		// et on fait un repaint pour avoir le nouvel affichage
		getPanelBoutons().setVisible(true);
		getPanelVolsProgrammes().setVisible(true);
		getContentPane().repaint();
	}

	public void displayVolsEnAttente()
	{
		hideElements();
		// on ajoute le panel de présentation des vols en attente + les boutons
		getContentPane().add(getPanelBoutons(), BorderLayout.NORTH);
		getContentPane().add(getPanelVolsEnAttente(), BorderLayout.CENTER);

		// on rend visible les éléments ajoutés
		// et on fait un repaint pour avoir le nouvel affichage
		getPanelBoutons().setVisible(true);
		getPanelVolsEnAttente().setVisible(true);
		getContentPane().repaint();
	}

	public void displayNouveauVol()
	{
		hideElements();
		// on ajoute le panel de création d'un vol + les boutons
		getContentPane().add(getPanelBoutons(), BorderLayout.NORTH);
		getContentPane().add(getPanelNouveauVol(), BorderLayout.CENTER);

		// on rend visible les éléments ajoutés
		// et on fait un repaint pour avoir le nouvel affichage
		getPanelBoutons().setVisible(true);
		getPanelNouveauVol().setVisible(true);
		getContentPane().repaint();
	}

	public void displayNouvelAeroport()
	{
		hideElements();
		// on ajoute le panel de création d'un aéroport + les boutons
		getContentPane().add(getPanelBoutons(), BorderLayout.NORTH);
		getContentPane().add(getPanelNouvelAeroport(), BorderLayout.CENTER);

		// on rend visible les éléments ajoutés
		// et on fait un repaint pour avoir le nouvel affichage
		getPanelBoutons().setVisible(true);
		getPanelNouvelAeroport().setVisible(true);
		getContentPane().repaint();
	}
	
	public void displayAeroports()
	{
		hideElements();
		// on ajoute le panel des aéroports + les boutons
		getContentPane().add(getPanelBoutons(), BorderLayout.NORTH);
		getContentPane().add(getPanelAeroports(), BorderLayout.CENTER);

		// on rend visible les éléments ajoutés
		// et on fait un repaint pour avoir le nouvel affichage
		getPanelBoutons().setVisible(true);
		getPanelAeroports().setVisible(true);
		getContentPane().repaint();
	}

	public void displayDeconnexion()
	{
		hideElements();
		// on ajoute le panel de déconnexion + le PanelBoutons
		getContentPane().add(getPanelBoutons(), BorderLayout.NORTH);
		getContentPane().add(getPanelDeconnexion(), BorderLayout.CENTER);

		// on rend visible les éléments ajoutés
		// et on fait un repaint pour avoir le nouvel affichage
		getPanelBoutons().setVisible(true);
		getPanelDeconnexion().setVisible(true);
		getContentPane().repaint();
	}
}
