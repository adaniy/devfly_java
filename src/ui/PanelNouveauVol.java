package ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

import model.Aeroport;
import model.Vol;
import dao.MysqlDao;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PanelNouveauVol extends JPanel {
	private JTextField textFieldDateDeDepart;
	private JTextField textFieldHeureDeDepart;
	private JTextField textFieldDureeDuVol;
	private JTextField textFieldTarif;
	private JLabel labelMessage;
	private JComboBox comboBoxVilleDeDepart;
	private JComboBox comboBoxVilleDarrivee;
	private PanelValiderAnnuler panelValiderAnnuler;
	private MysqlDao dao = new MysqlDao();

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public PanelNouveauVol() throws SQLException {
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
		gbc_labelMessage.gridx = 2;
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
		
		// Une combobox présentant les villes de départ possibles :
		comboBoxVilleDeDepart = new JComboBox();
		GridBagConstraints gbc_comboBoxVilleDeDepart = new GridBagConstraints();
		gbc_comboBoxVilleDeDepart.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxVilleDeDepart.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVilleDeDepart.gridx = 2;
		gbc_comboBoxVilleDeDepart.gridy = 3;
		add(comboBoxVilleDeDepart, gbc_comboBoxVilleDeDepart);
		
		// Les données dans la combobox vont provenir des données en base.
		// On récupère la liste des objets Aeroport :
		List<Aeroport> aeroports = dao.getAllAeroports();
		
		// On initialise un tableau de chaînes de caractères de la taille
		// de la liste, on y placera les villes.
		String[]villes = new String[aeroports.size()];
		
		// On parcourt la liste :
		for(int i = 0 ; i < aeroports.size(); i++){
			String ville = aeroports.get(i).getVille(); // on récupère la ville
			// On ajoute la ville dans le tableau :
			villes[i] = ville;
		}
		
		Arrays.sort(villes); // pour trier les villes par ordre alphabétique
		
		// on donne le tableau de villes au model :
		DefaultComboBoxModel<String>modelDepart = new DefaultComboBoxModel<>(villes);
		// on ajoute le model à la combobox :
		comboBoxVilleDeDepart.setModel(modelDepart);
		// on pourra faire défiler les villes avec la molette de la souris :
		comboBoxVilleDeDepart.setMaximumRowCount(6); // 6 villes visibles à chaque fois
		
		JLabel lblVilleDarrivee = new JLabel("Ville d'arrivée");
		GridBagConstraints gbc_lblVilleDarrivee = new GridBagConstraints();
		gbc_lblVilleDarrivee.anchor = GridBagConstraints.EAST;
		gbc_lblVilleDarrivee.insets = new Insets(0, 0, 5, 5);
		gbc_lblVilleDarrivee.gridx = 1;
		gbc_lblVilleDarrivee.gridy = 4;
		add(lblVilleDarrivee, gbc_lblVilleDarrivee);
		
		// Une combobox présentant les villes d'arrivée possibles :
		comboBoxVilleDarrivee = new JComboBox();
		GridBagConstraints gbc_comboBoxVilleDarrivee = new GridBagConstraints();
		gbc_comboBoxVilleDarrivee.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxVilleDarrivee.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVilleDarrivee.gridx = 2;
		gbc_comboBoxVilleDarrivee.gridy = 4;
		add(comboBoxVilleDarrivee, gbc_comboBoxVilleDarrivee);
		
		// Les données dans la combobox proviennent également des données
		// en base, on utilise les mêmes données que pour les villes de départ.
		
		// on donne le tableau de villes au model :
		DefaultComboBoxModel<String>modelArrivee = new DefaultComboBoxModel<>(villes);
		// on ajoute le model à la combobox :
		comboBoxVilleDarrivee.setModel(modelArrivee);
		// on pourra faire défiler les villes avec la molette de la souris :
		comboBoxVilleDarrivee.setMaximumRowCount(6); // 6 villes visibles à chaque fois
		
		JLabel lblDateDeDepart = new JLabel("Date de départ (jj/mm/aaaa)");
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
		
		JLabel lblHeureDeDepart = new JLabel("Heure de départ (hh:mm)");
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
		
		JLabel lblDureeDuVol = new JLabel("Durée du vol en minutes (> 9 min)");
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
		
		JLabel lblTarif = new JLabel("Tarif en euros au format __,xx");
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
		panelValiderAnnuler.getBtnAnnuler().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Un clic sur le bouton "annuler" efface les éléments saisis
				// et réinitialise les combobox.
				getComboBoxVilleDeDepart().setSelectedIndex(0);
				getComboBoxVilleDarrivee().setSelectedIndex(0);
				getTextFieldDateDeDepart().setText("");
				getTextFieldHeureDeDepart().setText("");
				getTextFieldDureeDuVol().setText("");
				getTextFieldTarif().setText("");
			}
		});
		
		panelValiderAnnuler.getBtnValider().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// au clic sur le bouton "valider", on ajoute un vol en base si les données saisies sont ok.
				
				// On réinitialise le message éventuellement inscrit :
				getLabelMessage().setText("");
				
				// On récupère les données saisies
				String villeDepart = getComboBoxVilleDeDepart().getSelectedItem().toString();
				String villeArrivee = getComboBoxVilleDarrivee().getSelectedItem().toString();
				String dateDepart = getTextFieldDateDeDepart().getText();
				String heureDepart = getTextFieldHeureDeDepart().getText();
				String duree = getTextFieldDureeDuVol().getText();
				String tarifRecupere = getTextFieldTarif().getText();
				// On remplace l'éventuelle virgule saisie par un point (sera nécessaire pour convertir en float)
				String tarif = tarifRecupere.replace(",", ".");
				
				// TODO : à optimiser
				// On vérifie la validité des informations saisies,
				// on affiche un message si la saisie est incorrecte.
				
				// On définit les formats voulus pour la date, l'heure, la durée du vol :
				String regexDate = "^(0[1-9]|1[0-9]|2[0-9]|30|31)/(0[1-9]|1[0-2])/[0-9]{4}";
				String regexHeure = "^([0-1][0-9]|2[0-3]):[0-5][0-9]$";
				String regexDuree = "^[1-9][0-9]+$"; // la durée du vol ne peut pas être inférieure à 10 min
				// Le tarif est un nombre décimal (rq : on laisse la possibilité à la compagnie d'indiquer
				// un tarif à zéro pour les événements particuliers).
				String regexTarif = "^[0-9]+\\.[0-9]{2}$";
				
				if(!villePrevue(villeDepart) || !villePrevue(villeArrivee) ||
						villeDepart.equals(villeArrivee)){
					// si l'une des villes n'est pas ok, ou que les villes de
					// départ et d'arrivée sont similaires :
					getLabelMessage().setText("Le trajet indiqué n'est pas correct !");
				}else if(!dateDepart.matches(regexDate)){
					// si la date n'est pas au bon format :
					getLabelMessage().setText("Vérifiez le format de la date svp !");
				}else if(!futureDate(dateDepart)){
					// On ne passe ici que si la date a un format valide !
					getLabelMessage().setText("La date ne peut être antérieure à demain !");
				}else if(!heureDepart.matches(regexHeure)){
					// si l'heure n'est pas au bon format :
					getLabelMessage().setText("Vérifiez le format de l'heure svp !");
				}else if(!duree.matches(regexDuree)){
					// si la durée n'est pas au bon format :
					getLabelMessage().setText("Vérifiez la durée du vol svp !");
				}else if(!tarif.matches(regexTarif)){
					// si le tarif n'est pas au bon format :
					getLabelMessage().setText("Vérifiez le format du tarif (ex : 230,00)");
				}else{
					// si on arrive ici, tout est ok, on insère le vol en base
					
					// on récupère les objets Aeroport
					// (on sait que les villes passées en paramètre sont bien correctes)
					Aeroport aeroportDepart = null;
					Aeroport aeroportArrivee = null;
					try {
						aeroportDepart = dao.getAeroportByVille(villeDepart);
						aeroportArrivee = dao.getAeroportByVille(villeArrivee);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}					
					
					// On concatène la date et l'heure de départ
					String dateHeureDepart = dateDepart + " " + heureDepart;
					
					// On tranforme le résultat de String en Date
					Date dateDeDepart = null;
					try {
						dateDeDepart = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateHeureDepart);
						// TODO corriger la date d'arrivée (+ heure)
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					// On transforme la durée récupérée en int
					int dureeInt = Integer.parseInt(duree);
					
					// pour calculer la date d'arrivée, on convertit la date de départ en timestamp
					// et la durée en millisecondes, et on les additionne
					long departMillisecondes = dateDeDepart.getTime();
					long dureeMillisecondes = dureeInt * 1000;
					
					long arriveeMillisecondes = departMillisecondes + dureeMillisecondes;
					//long arriveeSecondes = arriveeMillisecondes / 1000;
					// On convertit le TimeStamp obtenu en date util :
					//Date dateDArrivee = new Date(arriveeMillisecondes); // ou * 1000
					Timestamp dateDArrivee = new Timestamp(arriveeMillisecondes); // semble être en heure ?
					
					// On transforme le tarif récupéré en float
					float tarifFloat = Float.parseFloat(tarif);
					
					Vol volTest = new Vol(aeroportDepart, aeroportArrivee, dateDeDepart, dateDArrivee, dureeInt, tarifFloat);
					try {
						dao.addNewVol(volTest);
						// On affiche un message pour prévenir que tout s'est bien passé
						getLabelMessage().setText("Le vol a bien été ajouté !");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					// On réinitialise ensuite les champs et les combobox.
					getComboBoxVilleDeDepart().setSelectedIndex(0);
					getComboBoxVilleDarrivee().setSelectedIndex(0);
					getTextFieldDateDeDepart().setText("");
					getTextFieldHeureDeDepart().setText("");
					getTextFieldDureeDuVol().setText("");
					getTextFieldTarif().setText("");
					
					// on va recharger la liste des vols en attente pour que le nouveau vol apparaisse
					
					// On récupère les vols en attente
					List<Vol> volsEnAttente;
					try {
						volsEnAttente = dao.getAllVolsEnAttente();
						
						// on récupère la frame principale
						FenetrePrincipale frame = (FenetrePrincipale) SwingUtilities.getRoot(PanelNouveauVol.this);
						// on récupère la JTable
						JTable table = frame.getPanelVolsEnAttente().getTableVolsEnAttente();
						
						// On crée le model avec les bonnes données et on le donne à la JTable
						// On utilise pour cela la méthode statique définie dans Vol
						Vol.TableCreation(volsEnAttente, table);
						
						// On dimensionne les colonnes :
						Vol.columnSizeVols(table);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_panelValiderAnnuler = new GridBagConstraints();
		gbc_panelValiderAnnuler.fill = GridBagConstraints.BOTH;
		gbc_panelValiderAnnuler.gridx = 2;
		gbc_panelValiderAnnuler.gridy = 10;
		add(panelValiderAnnuler, gbc_panelValiderAnnuler);

	}

	public JLabel getLabelMessage() {
		return labelMessage;
	}

	public void setLabelMessage(JLabel labelMessage) {
		this.labelMessage = labelMessage;
	}

	public JTextField getTextFieldDateDeDepart() {
		return textFieldDateDeDepart;
	}

	public void setTextFieldDateDeDepart(JTextField textFieldDateDeDepart) {
		this.textFieldDateDeDepart = textFieldDateDeDepart;
	}

	public JTextField getTextFieldHeureDeDepart() {
		return textFieldHeureDeDepart;
	}

	public void setTextFieldHeureDeDepart(JTextField textFieldHeureDeDepart) {
		this.textFieldHeureDeDepart = textFieldHeureDeDepart;
	}

	public JTextField getTextFieldDureeDuVol() {
		return textFieldDureeDuVol;
	}

	public void setTextFieldDureeDuVol(JTextField textFieldDureeDuVol) {
		this.textFieldDureeDuVol = textFieldDureeDuVol;
	}

	public JTextField getTextFieldTarif() {
		return textFieldTarif;
	}

	public void setTextFieldTarif(JTextField textFieldTarif) {
		this.textFieldTarif = textFieldTarif;
	}

	public JComboBox getComboBoxVilleDeDepart() {
		return comboBoxVilleDeDepart;
	}

	public void setComboBoxVilleDeDepart(JComboBox comboBoxVilleDeDepart) {
		this.comboBoxVilleDeDepart = comboBoxVilleDeDepart;
	}

	public JComboBox getComboBoxVilleDarrivee() {
		return comboBoxVilleDarrivee;
	}

	public void setComboBoxVilleDarrivee(JComboBox comboBoxVilleDarrivee) {
		this.comboBoxVilleDarrivee = comboBoxVilleDarrivee;
	}
	
	// Prend en paramètre une date sous forme de chaîne de caractères jj/mm/aaaa
	// Renvoie vrai si la date indiquée est dans le futur
	private boolean futureDate(String laDate){ // on n'utilise cette méthode que dans ce panel
		// On crée une date à partir de la chaîne récupérée :
		Date laDateFormatee = null;
		try {
			laDateFormatee = new SimpleDateFormat("dd/MM/yyyy").parse(laDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// On récupère le timestamp de la date indiquée :
		long timeStampLaDate = laDateFormatee.getTime();
		// On récupère le timestamp actuel :
		Date d = new Date();
		long timeStampActuel = d.getTime();
		
		// On fait la différence entre les 2 timeStamp
		if(timeStampLaDate - timeStampActuel > 0){
			return true; // date future
		}else{
			return false;
		}
	}
	
	// utilisée pour vérifier si les villes de départ et d'arrivée
	// correspondent à celles prévues par la compagnie.
	private boolean villePrevue(String ville){
		// On récupère les aéroports enregistrés
		List<Aeroport> aeroportsEnregistres = new ArrayList<>();
		
		try {
			aeroportsEnregistres = dao.getAllAeroports();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// On initialise un tableau de chaînes de caractères de la taille
		// de la liste pour y placer les villes de départ / arrivée prévues
		// par la compagnie.
		String[]villesPrevues = new String[aeroportsEnregistres.size()];
		
		// On parcourt la liste :
		for(int i = 0 ; i < aeroportsEnregistres.size(); i++){
			String villePrevue = aeroportsEnregistres.get(i).getVille(); // on récupère la ville
			// On ajoute la ville dans le tableau :
			villesPrevues[i] = villePrevue;
		}
		// On initialise 1 booléen à false :
		boolean villePresente = false;
		
		// On vérifie si la ville sélectionnée est présente dans le tableau :
		for(String v : villesPrevues){
			if(v.equals(ville)){
				villePresente = true;
			}
		}
		return villePresente;
	}

}
