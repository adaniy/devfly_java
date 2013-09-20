package ui;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;

import model.Vol;
import dao.MysqlDao;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelVolsProgrammes extends JPanel {
	private JTable tableVolsProgrammes;
	private JScrollPane scrollPane; // conteneur pour avoir une barre de défilement
	private JLabel labelMessage;
	private MysqlDao dao = new MysqlDao();
	private PanelModifVol panelModifVolProgramme;

	public PanelModifVol getPanelModifVolProgramme() {
		return panelModifVolProgramme;
	}

	public JTable getTableVolsProgrammes() {
		return tableVolsProgrammes;
	}
	

	public PanelVolsProgrammes() throws SQLException {
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tableVolsProgrammes = new JTable();
		tableVolsProgrammes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// on récupère l'endroit où a eu lieu l'événement (= le clic)
				Point p = e.getPoint();
				// on remplit le formulaire avec les données du tableau
				panelModifVolProgramme.fillInForm(panelModifVolProgramme, p, tableVolsProgrammes);
				// on supprime le message éventuellement saisi
				panelModifVolProgramme.getLblMessage().setText("");
			}
		});
		// pour que le tableau ne soit pas redimensionné automatiquement à la longueur de la fenêtre :
		tableVolsProgrammes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// pour trier en cliquant sur les en-têtes
		tableVolsProgrammes.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tableVolsProgrammes);
		
		// pour enlever le fait qu'on puisse déplacer les colonnes :
		tableVolsProgrammes.getTableHeader().setReorderingAllowed(false);
		
		// On récupère les vols programmés :
		List<Vol> volsProgrammes = dao.getAllVolsProgrammes();
		
		// On crée le model avec les bonnes données et on le donne à la JTable
		// On utilise pour cela la méthode statique définie dans Vol
		util.MiseEnForme.TableCreationVols(volsProgrammes, tableVolsProgrammes);
		
		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
		labelMessage.setForeground(Color.RED);
		add(labelMessage, BorderLayout.NORTH);
		
		// On dimensionne les colonnes :
		util.MiseEnForme.columnSizeVols(tableVolsProgrammes);
		
		panelModifVolProgramme = new PanelModifVol();
		panelModifVolProgramme.getBtnSupprimer().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// au clic sur supprimer, on supprime le vol de la base
				// s'il n'y a pas de réservation dessus (la vérif est faite directement dans la
				// méthode du dao)
				
				// On récupère le code du vol à supprimer :
				String numVol = panelModifVolProgramme.getTextFieldNdeVol().getText();
				// On supprime le vol :
				try {
					if(dao.deleteVolProgramme(numVol)){ // si la suppression s'est bien passée
						// On affiche un message :
						panelModifVolProgramme.getLblMessage().setText("Le vol " + numVol + " a bien été supprimé !");
						
						// On vide les champs du formulaire et on rafraichit les données :
						// On récupère la liste des vols programmés à jour :
						List<Vol> listeVolsProgrammes = dao.getAllVolsProgrammes();
						panelModifVolProgramme.rafraichirDonnees(listeVolsProgrammes, tableVolsProgrammes);
						
					}else{
						// En cas d'erreur, on affiche un message (a priori aucun vol n'était sélectionné,
						// ou alors il y avait une réservation sur le vol)
						panelModifVolProgramme.getLblMessage().setText("<html><p>La suppression n'a pas pu être effectuée.<br>"
								+ "Veuillez sélectionner un vol ci-dessus et renouveler l'opération.<br>"
								+ "<u>Attention, les vols ne sont plus supprimables si une place a été réservée dessus !</u></p></html>");
					}
				} catch (SQLException e2) {
					panelModifVolProgramme.getLblMessage().setText(e2.getMessage());
				}
				
			}
		});
		panelModifVolProgramme.getBtnMettreAJour().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Au clic sur valider, on vérifie que le vol est bien "futur" et que le tarif est ok
				// Si tout est correct, on met à jour le tarif du vol
				
				// On vérifie au préalable qu'un vol est sélectionné :
				// On récupère le code du vol en cours de modification
				String id = panelModifVolProgramme.getTextFieldNdeVol().getText();
				if(id.isEmpty()){
					// Si aucun vol n'est sélectionné, on affiche un message et on ne continue pas
					panelModifVolProgramme.getLblMessage().setText("Vous devez sélectionner un vol ci-dessus !");
				}else{
					// Sinon, on récupère également la date du vol + le tarif saisi
					String dateDepart = panelModifVolProgramme.getTextFieldDateDep().getText();
					String tarifRecupere = panelModifVolProgramme.getTextFieldTarif().getText();
					// On remplace l'éventuelle virgule saisie par un point (sera nécessaire pour convertir en float)
					// (-> on autorise indifféremment point et virgule)
					String tarif = tarifRecupere.replace(",", ".");
							
					// On initialise un booléen à vrai. Dès lors qu'un critère n'est pas rempli,
					// on le passe à faux. C'est lui qui déterminera si la mise à jour peut se faire.
					boolean miseAJour = true;
					
					// Le tarif est un nombre décimal (rq : on laisse la possibilité à la compagnie d'indiquer
					// un tarif à zéro pour les événements particuliers).
					String regexTarif = "^[0-9]+\\.[0-9]{2}$";
					
					// On vérifie que la date est dans le futur SI une date est sélectionnée
					// (cf le visiteur pourrait cliquer directement sur "mise à jour" sans choisir un vol
					// ce qui provoquerait une erreur : on essaierait de parser une chaine vide en date)
					if(!dateDepart.isEmpty()){
						if(!util.Donnees.futureDate(dateDepart)){
							panelModifVolProgramme.getLblMessage().setText("Vous ne pouvez pas modifier le tarif des "
									+ "vols en partance ce jour ou dans le passé !");
							miseAJour = false;
						}
					}
					// On vérifie que le tarif est OK
					if(!tarif.matches(regexTarif)){
						panelModifVolProgramme.getLblMessage().setText("Vérifiez le format du tarif (ex : 230,00)");
						miseAJour = false;
					}
					
					if(miseAJour){ // si rien n'a bloqué la mise à jour, on peut la faire !
						// On transforme le tarif récupéré en float
						float tarifFloat = Float.parseFloat(tarif);
						
						try {
							if(dao.updateTarifVolProgramme(id, tarifFloat)){ // renvoie vrai si la mise à jour s'est bien passée
								panelModifVolProgramme.getLblMessage().setText("Le tarif du vol " + id + " a bien été mis à jour !");
							}else{
								panelModifVolProgramme.getLblMessage().setText("Il y a eu un problème lors de la mise à jour du tarif !");
							}
						} catch (SQLException e1) {
							panelModifVolProgramme.getLblMessage().setText(e1.getMessage());
						}
						
						// On vide les champs du formulaire et on rafraichit les données :
						// On récupère la liste des vols programmés à jour :
						List<Vol> listeVolsProgrammes = null;
						try {
							listeVolsProgrammes = dao.getAllVolsProgrammes();
						} catch (SQLException e) {
							panelModifVolProgramme.getLblMessage().setText(e.getMessage());
						}
						
						try {
							panelModifVolProgramme.rafraichirDonnees(listeVolsProgrammes, tableVolsProgrammes);
						} catch (SQLException e) {
							panelModifVolProgramme.getLblMessage().setText(e.getMessage());
						}
					}
				}
			}
		});
		
		// On efface les légendes du formulaire pour les champs que l'utilisateur n'a pas à remplir
		panelModifVolProgramme.getLblLegendeDateDep().setText("");
		panelModifVolProgramme.getLblLegendeHeureDep().setText("");
		panelModifVolProgramme.getLblLegendeDuree().setText("");
		// On rend "non éditables" les champs que l'utilisateur ne peut pas modifier
		panelModifVolProgramme.getTextFieldDateDep().setEditable(false);
		panelModifVolProgramme.getTextFieldHeureDep().setEditable(false);
		panelModifVolProgramme.getTextFieldDuree().setEditable(false);
		panelModifVolProgramme.getJComboBoxVilleDeDepart().setEnabled(false);
		panelModifVolProgramme.getJComboBoxVilleDarrivee().setEnabled(false);
		panelModifVolProgramme.getComboBoxPilote().setEnabled(false);
		panelModifVolProgramme.getComboBoxCopilote().setEnabled(false);
		panelModifVolProgramme.getComboBoxHotesseSt1().setEnabled(false);
		panelModifVolProgramme.getComboBoxHotesseSt2().setEnabled(false);
		panelModifVolProgramme.getComboBoxHotesseSt3().setEnabled(false);
		// pour les combobox désactivées, on leur donne un style plus visible que celui par défaut :
		renduComboBox(panelModifVolProgramme.getJComboBoxVilleDeDepart());
		renduComboBox(panelModifVolProgramme.getJComboBoxVilleDarrivee());
		renduComboBox(panelModifVolProgramme.getComboBoxPilote());
		renduComboBox(panelModifVolProgramme.getComboBoxCopilote());
		renduComboBox(panelModifVolProgramme.getComboBoxHotesseSt1());
		renduComboBox(panelModifVolProgramme.getComboBoxHotesseSt2());
		renduComboBox(panelModifVolProgramme.getComboBoxHotesseSt3());
				
		
		panelModifVolProgramme.getBtnReinitialiser().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// au clic sur "réinitialiser", on réinitalise le(s) champ(s) à leur valeur initiale
				// On récupère le code du vol en cours de modification
				String numVol = panelModifVolProgramme.getTextFieldNdeVol().getText();
				if(numVol.isEmpty()){
					panelModifVolProgramme.getLblMessage().setText("Vous devez sélectionner un vol ci-dessus !");
				}else{
					// On récupère les données du vol correspondant :
					try {
						Vol v = dao.getVolProgrammeById(numVol);
						// On efface l'éventuel message saisi :
						panelModifVolProgramme.getLblMessage().setText("");
						// On réinitialise le(s) champ(s) du formulaire :
						panelModifVolProgramme.formReset(panelModifVolProgramme, v);
						
					} catch (SQLException e) {
						panelModifVolProgramme.getLblMessage().setText(e.getMessage());
					}
				}
			}
		});
		add(panelModifVolProgramme, BorderLayout.SOUTH);
		
	}
	
	// pour modifier le rendu d'une combobox désactivée
	// On passe la couleur de police de gris très clair (peu lisible) à gris foncé
	private void renduComboBox(JComboBox<String> maComboBox){
		maComboBox.setRenderer(new DefaultListCellRenderer() {
	        @Override
	        public void paint(Graphics g) {
	            setForeground(new Color(51, 51, 51));
	            super.paint(g);
	        }
	    });
	}
}
