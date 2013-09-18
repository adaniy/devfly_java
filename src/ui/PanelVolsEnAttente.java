package ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JLabel;

import java.awt.Color;
import java.sql.SQLException;
import java.util.List;

import javax.swing.SwingConstants;

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

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
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
				
				// On récupère les données saisies
				String id = panelModifVolEnAttente.getTextFieldNdeVol().getText();
				String paysDepart = panelModifVolEnAttente.getTextFieldPaysDeDepart().getText();
				String codeDepart = panelModifVolEnAttente.getTextFieldCodeDep().getText();
				String paysArrivee = panelModifVolEnAttente.getTextFieldPaysDarrivee().getText();
				String codeArrivee = panelModifVolEnAttente.getTextFieldCodeArriv().getText();
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
				if(!codeHotesseSt1.equals(choixEmploye) && codeHotesseSt1.equals(codeHotesseSt2) ||
						!codeHotesseSt1.equals(choixEmploye) && codeHotesseSt1.equals(codeHotesseSt3) ||
						!codeHotesseSt2.equals(choixEmploye) && codeHotesseSt2.equals(codeHotesseSt3)){
					panelModifVolEnAttente.getLblMessage().setText("Vous devez choisir des hôtesses ou stewards différents.");
					miseAJour = false;
				}
			}
			
			// TODO : si tous les champs sont remplis ET que les employés n'ont pas la valeur
			// "Choisissez un employé", on passe le vol "en attente" en vol "confirmé"
			// mise à jour : attention à la valeur choixEmploye

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
						panelModifVolEnAttente.getLblMessage().setText("Le vol a bien été supprimé !");
						
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

	public JTable getTableVolsEnAttente() {
		return tableVolsEnAttente;
	}
}
