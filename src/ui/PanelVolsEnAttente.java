package ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JLabel;

import java.awt.Color;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.SwingConstants;

import model.Aeroport;
import model.Vol;
import dao.MysqlDao;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelVolsEnAttente extends JPanel {
	private JTable tableVolsEnAttente;
	private JScrollPane scrollPane; // conteneur pour avoir une barre de défilement
	private JLabel labelMessage;
	private MysqlDao dao = new MysqlDao();
	private PanelModifVol panelModifVolEnAttente;

	public PanelModifVol getPanelModifVolEnAttente() {
		return panelModifVolEnAttente;
	}
	
	public JTable getTableVolsEnAttente() {
		return tableVolsEnAttente;
	}


	public PanelVolsEnAttente() throws SQLException {
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tableVolsEnAttente = new JTable();
		tableVolsEnAttente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// on récupère l'endroit où a eu lieu l'événement (= le clic)
				Point p = arg0.getPoint();
				// on remplit le formulaire avec les données du tableau
				PanelModifVol.fillInForm(panelModifVolEnAttente, p, tableVolsEnAttente);
				// on supprime le message éventuellement saisi
				panelModifVolEnAttente.getLblMessage().setText("");
			}
		});
		
		// pour que le tableau ne soit pas redimensionné automatiquement à la longueur de la fenêtre :
		tableVolsEnAttente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// pour trier en cliquant sur les en-têtes
		tableVolsEnAttente.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tableVolsEnAttente);
		
		// pour enlever le fait qu'on puisse déplacer les colonnes :
		tableVolsEnAttente.getTableHeader().setReorderingAllowed(false);
		
		// On récupère les vols en attente :
		List<Vol> volsEnAttente = dao.getAllVolsEnAttente();
		
		// On crée le model avec les bonnes données et on le donne à la JTable
		// On utilise pour cela la méthode statique définie dans Vol
		Vol.TableCreation(volsEnAttente, tableVolsEnAttente);
		
		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
		labelMessage.setForeground(Color.RED);
		add(labelMessage, BorderLayout.NORTH);
		
		// On dimensionne les colonnes :
		Vol.columnSizeVols(tableVolsEnAttente);
		
		panelModifVolEnAttente = new PanelModifVol();
		panelModifVolEnAttente.getBtnMettreAJour().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Au clic sur valider, on vérifie la validité des champs
				// Si tout est correct, on met à jour le vol
				
				// On récupère au préalable le code du vol en cours de modification
				// pour s'assurer qu'un vol est bien sélectionné
				String id = panelModifVolEnAttente.getTextFieldNdeVol().getText();
				if(id.isEmpty()){
					// Si aucun vol n'est sélectionné, on affiche un message et on ne continue pas
					panelModifVolEnAttente.getLblMessage().setText("Vous devez sélectionner un vol ci-dessus !");
				}else{
					// Sinon, on récupère les autres données saisies
					String dateDepart = panelModifVolEnAttente.getTextFieldDateDep().getText();
					String heureDepart = panelModifVolEnAttente.getTextFieldHeureDep().getText();
					String duree = panelModifVolEnAttente.getTextFieldDuree().getText();
					String tarifRecupere = panelModifVolEnAttente.getTextFieldTarif().getText();
					// On remplace l'éventuelle virgule saisie par un point (sera nécessaire pour convertir en float)
					// (-> on autorise indifféremment point et virgule)
					String tarif = tarifRecupere.replace(",", ".");
					
					// pour les comboBoxes :
					String villeDepart = (String) panelModifVolEnAttente.getJComboBoxVilleDeDepart().getSelectedItem();
					String villeArrivee = (String) panelModifVolEnAttente.getJComboBoxVilleDarrivee().getSelectedItem();
					String codePilote = (String) panelModifVolEnAttente.getComboBoxPilote().getSelectedItem();
					String codeCopilote = (String) panelModifVolEnAttente.getComboBoxCopilote().getSelectedItem();
					String codeHotesseSt1 = (String) panelModifVolEnAttente.getComboBoxHotesseSt1().getSelectedItem();
					String codeHotesseSt2 = (String) panelModifVolEnAttente.getComboBoxHotesseSt2().getSelectedItem();
					String codeHotesseSt3 = (String) panelModifVolEnAttente.getComboBoxHotesseSt3().getSelectedItem();
					
					// On sait que les éléments suivants sont corrects :
					//- l'id du vol (il n'est pas modifiable)
					//- les villes de départ et d'arrivée sont bien prévues par la compagnie (cf liste déroulante)
					//- les pays et codes aéroports sont générés par rapport à la ville choisie
					//- les codes des employés sont bien ceux de la compagnie (cf listes déroulantes)
					
					// On initialise un booléen à vrai. Dès lors qu'un critère n'est pas rempli,
					// on le passe à faux. C'est lui qui déterminera si la mise à jour peut se faire.
					boolean miseAJour = true;
					
					// On définit les formats voulus pour la date, l'heure, la durée du vol :
					String regexDate = "^(0[1-9]|1[0-9]|2[0-9]|30|31)/(0[1-9]|1[0-2])/[0-9]{4}";
					String regexHeure = "^([0-1][0-9]|2[0-3]):[0-5][0-9]$";
					String regexDuree = "^[1-9][0-9]+$"; // la durée du vol ne peut pas être inférieure à 10 min
					// Le tarif est un nombre décimal (rq : on laisse la possibilité à la compagnie d'indiquer
					// un tarif à zéro pour les événements particuliers).
					String regexTarif = "^[0-9]+\\.[0-9]{2}$";
					
					// On vérifie que les villes de départ et d'arrivée sont différentes
					if(villeDepart.equals(villeArrivee)){
						panelModifVolEnAttente.getLblMessage().setText("Le trajet indiqué n'est pas correct !");
						miseAJour = false;
					}
					// On vérifie que la date est au bon format ET dans le futur
					if(!dateDepart.matches(regexDate) || !PanelNouveauVol.futureDate(dateDepart)){
						// (on ne vérifie que la date est dans le futur que si elle a un format valide)
						panelModifVolEnAttente.getLblMessage().setText("<html><p>Vérifiez le format de la date svp.<br>"
								+ "Attention, la date ne peut pas être antérieure à demain !</p></html>");
						miseAJour = false;
					}
					// On vérifie que l'heure est au bon format
					if(!heureDepart.matches(regexHeure)){
						panelModifVolEnAttente.getLblMessage().setText("Vérifiez le format de l'heure svp !");
						miseAJour = false;
					}
					// On vérifie que la durée est OK
					if(!duree.matches(regexDuree)){
						panelModifVolEnAttente.getLblMessage().setText("Vérifiez la durée du vol svp !");
						miseAJour = false;
					}
					// On vérifie que le tarif est OK
					if(!tarif.matches(regexTarif)){
						panelModifVolEnAttente.getLblMessage().setText("Vérifiez le format du tarif (ex : 230,00)");
						miseAJour = false;
					}
					// On vérifie que les 3 hôtesses / stewards sélectionnés sont différents.
					// La vérification se fait uniquement si l'employé n'a pas la valeur "Choisissez un employé".
					String choixEmploye = "Choisissez un employé";
					if((!codeHotesseSt1.equals(choixEmploye) && codeHotesseSt1.equals(codeHotesseSt2)) ||
							(!codeHotesseSt1.equals(choixEmploye) && codeHotesseSt1.equals(codeHotesseSt3)) ||
							(!codeHotesseSt2.equals(choixEmploye) && codeHotesseSt2.equals(codeHotesseSt3))){
						panelModifVolEnAttente.getLblMessage().setText("Vous devez choisir des hôtesses ou stewards différents.");
						miseAJour = false;
					}
					
					
					if(miseAJour){ // si rien n'a bloqué la mise à jour, on peut la faire !
						// avant de faire la mise à jour, on remplace les éventuels codes employés qui
						// ont pour valeur "Choisissez un employé" par une chaîne vide
						String pilote = employeNonSelectionne(codePilote);
						String copilote = employeNonSelectionne(codeCopilote);
						String hotesseSt1 = employeNonSelectionne(codeHotesseSt1);
						String hotesseSt2 = employeNonSelectionne(codeHotesseSt2);
						String hotesseSt3 = employeNonSelectionne(codeHotesseSt3);
						
						// on récupère les objets Aeroport
						Aeroport aeroportDepart = null;
						try {
							aeroportDepart = dao.getAeroportByVille(villeDepart);
						} catch (SQLException e1) {
							panelModifVolEnAttente.getLblMessage().setText(e1.getMessage());
						}
						Aeroport aeroportArrivee = null;
						try {
							aeroportArrivee = dao.getAeroportByVille(villeArrivee);
						} catch (SQLException e1) {
							panelModifVolEnAttente.getLblMessage().setText(e1.getMessage());
						}
						
						// On concatène la date et l'heure de départ
						String dateHeureDepart = dateDepart + " " + heureDepart;
						
						// On tranforme le résultat de String en Date
						Date dateDeDepart = null;
						try {
							dateDeDepart = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateHeureDepart);
						} catch (ParseException e1) {
							panelModifVolEnAttente.getLblMessage().setText(e1.getMessage());
						}
						
						// On transforme la durée récupérée en int
						int dureeInt = Integer.parseInt(duree); // en minutes
						
						// pour calculer la date d'arrivée, on convertit la date de départ en timestamp
						// et la durée en millisecondes, et on les additionne
						long departMillisecondes = dateDeDepart.getTime();
						long dureeMillisecondes = dureeInt * 60_000;
						
						long arriveeMillisecondes = departMillisecondes + dureeMillisecondes;
						// On transforme le long obtenu en Timestamp
						Timestamp dateDArrivee = new Timestamp(arriveeMillisecondes);
						
						// On transforme le tarif récupéré en float
						float tarifFloat = Float.parseFloat(tarif);
						
						// on crée un objet Vol avec toutes les données récupérées
						Vol vol = new Vol(id, aeroportDepart, aeroportArrivee, dateDeDepart, dateDArrivee, dureeInt, tarifFloat, pilote, copilote, hotesseSt1, hotesseSt2, hotesseSt3);
						
						// Si tous les employés sont renseignés, on passe le vol de "vol en attente" à
						// "vol programmé"
						if(!pilote.isEmpty() && !copilote.isEmpty() && !hotesseSt1.isEmpty() &&
								!hotesseSt2.isEmpty() && !hotesseSt3.isEmpty()){
							// TODO trigger pour supprimer le vol_tmp
							try {
								if(dao.confirmVol(vol)){ // renvoie vrai si ça s'est bien passé
									panelModifVolEnAttente.getLblMessage().setText("Le vol " + id + " a bien été validé !");
								}else{
									panelModifVolEnAttente.getLblMessage().setText("Il y a eu un problème lors de la validation du vol !");
								}
							} catch (SQLException e) {
								panelModifVolEnAttente.getLblMessage().setText(e.getMessage());
							}
						}else{
							// sinon, on modifie simplement le vol "en attente"
							try {
								if(dao.updateVolEnAttente(vol)){ // renvoie vrai si la mise à jour s'est bien passée
									panelModifVolEnAttente.getLblMessage().setText("Le vol " + id + " a bien été mis à jour !");
								}else{
									panelModifVolEnAttente.getLblMessage().setText("Il y a eu un problème lors de la mise à jour !");
								}
								
							} catch (SQLException e) {
								panelModifVolEnAttente.getLblMessage().setText(e.getMessage());
							}
						}
						// Dans tous les cas (vol mis à jour ou vol validé),
						// on vide les champs du formulaire et on rafraichit les données.
						// avec la méthode "rafraichirDonnees".
						// On récupère les listes de vols à jour :
						List<Vol> listeVolsEnAttente = null;
						List<Vol> listeVolsProgrammes = null;
						try {
							listeVolsEnAttente = dao.getAllVolsEnAttente();
						} catch (SQLException e) {
							panelModifVolEnAttente.getLblMessage().setText(e.getMessage());
						}
						try {
							listeVolsProgrammes = dao.getAllVolsProgrammes();
						} catch (SQLException e1) {
							panelModifVolEnAttente.getLblMessage().setText(e1.getMessage());
						}
						try {
							// on rafraichit les données pour les vols en attente
							panelModifVolEnAttente.rafraichirDonnees(listeVolsEnAttente, tableVolsEnAttente);
						} catch (SQLException e) {
							panelModifVolEnAttente.getLblMessage().setText(e.getMessage());
						}
						// pour récupèrer la table des vols programmés, on doit récupèrer la frame principale
						FenetrePrincipale frame = (FenetrePrincipale) SwingUtilities.getRoot(PanelVolsEnAttente.this);
						JTable tableVolsProgrammes = frame.getPanelVolsProgrammes().getTableVolsProgrammes();
						try {
							// on rafraichit les données pour les vols programmés
							panelModifVolEnAttente.rafraichirDonnees(listeVolsProgrammes, tableVolsProgrammes);
						} catch (SQLException e) {
							panelModifVolEnAttente.getLblMessage().setText(e.getMessage());
						}
					}
				}
				
			}

		});
		panelModifVolEnAttente.getBtnSupprimer().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// au clic sur supprimer, on supprime le vol de la base (table vol_tmp)
				
				// On récupère le code du vol à supprimer :
				String numVol = panelModifVolEnAttente.getTextFieldNdeVol().getText();
				// On supprime le vol :
				try {
					if(dao.deleteVolEnAttente(numVol)){ // si la suppression s'est bien passée
						// On affiche un message :
						panelModifVolEnAttente.getLblMessage().setText("Le vol " + numVol + " a bien été supprimé !");
						
						// On vide les champs du formulaire et on rafraichit les données :
						// On récupère la liste des vols en attente à jour :
						List<Vol> listeVolsEnAttente = dao.getAllVolsEnAttente();
						panelModifVolEnAttente.rafraichirDonnees(listeVolsEnAttente, tableVolsEnAttente);
						
					}else{
						// En cas d'erreur, on affiche un message (a priori aucun vol n'était sélectionné)
						panelModifVolEnAttente.getLblMessage().setText("<html><p>La suppression n'a pas pu être effectuée !<br>"
								+ "Veuillez sélectionner un vol ci-dessus et renouveler l'opération.</p></html>");
					}
				} catch (SQLException e2) {
					panelModifVolEnAttente.getLblMessage().setText(e2.getMessage());
				}
				
			}
		});
		panelModifVolEnAttente.getBtnReinitialiser().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// au clic sur "réinitialiser", on réinitalise les champs à leur valeur initiale
				// On récupère le code du vol en cours de modification
				String numVol = panelModifVolEnAttente.getTextFieldNdeVol().getText();
				if(numVol.isEmpty()){
					panelModifVolEnAttente.getLblMessage().setText("Vous devez sélectionner un vol ci-dessus !");
				}else{
					// On récupère les données du vol correspondant :
					try {
						Vol v = dao.getVolEnAttenteById(numVol);
						// On efface l'éventuel message saisi :
						panelModifVolEnAttente.getLblMessage().setText("");
						// On réinitialise les champs du formulaire :
						PanelModifVol.formReset(panelModifVolEnAttente, v);
						
					} catch (SQLException e) {
						panelModifVolEnAttente.getLblMessage().setText(e.getMessage());
					}
				}
			}
		});
		add(panelModifVolEnAttente, BorderLayout.SOUTH);
	}
	
	// pour "transformer" un code employé de "Choisissez un employé" à "" le cas échéant
	private String employeNonSelectionne(String codeEmploye){
		if(codeEmploye.equals("Choisissez un employé")){
			return ""; // on retourne une chaîne vide
		}
		return codeEmploye; // sinon, on retourne le code employé "normal"
	}

}
