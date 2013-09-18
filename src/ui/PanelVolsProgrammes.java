package ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
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

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
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
				PanelModifVol.fillInForm(panelModifVolProgramme, p, tableVolsProgrammes);
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
		Vol.TableCreation(volsProgrammes, tableVolsProgrammes);
		
		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
		labelMessage.setForeground(Color.RED);
		add(labelMessage, BorderLayout.NORTH);
		
		// On dimensionne les colonnes :
		Vol.columnSizeVols(tableVolsProgrammes);
		
		panelModifVolProgramme = new PanelModifVol();
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
						if(!PanelNouveauVol.futureDate(dateDepart)){
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
		
		panelModifVolProgramme.getBtnReinitialiser().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// au clic sur "réinitialiser", on réinitalise les champs à leur valeur initiale
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
						// On réinitialise les champs du formulaire :
						PanelModifVol.formReset(panelModifVolProgramme, v);
						
					} catch (SQLException e) {
						panelModifVolProgramme.getLblMessage().setText(e.getMessage());
					}
				}
			}
		});
		add(panelModifVolProgramme, BorderLayout.SOUTH);
		
	}

}
