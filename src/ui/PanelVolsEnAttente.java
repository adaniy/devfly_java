package ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JLabel;

import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

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
				int row = tableVolsEnAttente.rowAtPoint(p); // renvoie la ligne sous le point
				// On convertit les row du tableau en row du modèle pour maintenir la cohérence 
				// entre les cellules de la présentation et les cellules du model (source de données)
				int modelRow = tableVolsEnAttente.convertRowIndexToModel(row);
				TableModel model = tableVolsEnAttente.getModel();
				String numVol = (String) model.getValueAt(modelRow, 0); // String qui représente la valeur récupérée
				String villeDep = (String) model.getValueAt(modelRow, 1);
				String paysDep = (String) model.getValueAt(modelRow, 2);
				String codeDep = (String) model.getValueAt(modelRow, 3);
				String villeArriv = (String) model.getValueAt(modelRow, 4);
				String paysArriv = (String) model.getValueAt(modelRow, 5);
				String codeArriv = (String) model.getValueAt(modelRow, 6);
				String dateHeureDep = (String) model.getValueAt(modelRow, 7);
				String dateHeureArriv = (String) model.getValueAt(modelRow, 8);
				int duree = (int) model.getValueAt(modelRow, 9);
				String tarif = (String) model.getValueAt(modelRow, 10);
				String pilote = (String) model.getValueAt(modelRow, 11);
				String copilote = (String) model.getValueAt(modelRow, 12);
				String hotesseSt1 = (String) model.getValueAt(modelRow, 13);
				String hotesseSt2 = (String) model.getValueAt(modelRow, 14);
				String hotesseSt3 = (String) model.getValueAt(modelRow, 15);
				
				
				// On place les valeurs récupérées dans les champs du formulaire
				
				panelModifVolEnAttente.getTextFieldNdeVol().setText(numVol);
				panelModifVolEnAttente.getTextFieldVilleDeDepart().setText(villeDep);
				panelModifVolEnAttente.getTextFieldPaysDeDepart().setText(paysDep);
				panelModifVolEnAttente.getTextFieldCodeDep().setText(codeDep);
				panelModifVolEnAttente.getTextFieldVilleDarrivee().setText(villeArriv);
				panelModifVolEnAttente.getTextFieldPaysDarrivee().setText(paysArriv);
				panelModifVolEnAttente.getTextFieldCodeArriv().setText(codeArriv);
				// pour avoir la date uniquement, on récupère la sous-chaîne correspondante
				panelModifVolEnAttente.getTextFieldDateDep().setText(dateHeureDep.substring(0,10));
				// pour avoir l'heure uniquement, on commence à l'index 13
				panelModifVolEnAttente.getTextFieldHeureDep().setText(dateHeureDep.substring(13));
				// pour avoir la date uniquement, on récupère la sous-chaîne correspondante
				panelModifVolEnAttente.getTextFieldDateArriv().setText(dateHeureArriv.substring(0,10));
				// pour avoir l'heure uniquement, on commence à l'index 13
				panelModifVolEnAttente.getTextFieldHeureArriv().setText(dateHeureArriv.substring(13));
				// pour la durée, on passe la valeur de l'entier en chaîne de caractères
				panelModifVolEnAttente.getTextFieldDuree().setText(String.valueOf(duree));
				panelModifVolEnAttente.getTextFieldTarif().setText(tarif);
				panelModifVolEnAttente.getTextFieldPilote().setText(pilote);
				panelModifVolEnAttente.getTextFieldCopilote().setText(copilote);
				panelModifVolEnAttente.getTextFieldHotesseSt1().setText(hotesseSt1);
				panelModifVolEnAttente.getTextFieldHotesseSt2().setText(hotesseSt2);
				panelModifVolEnAttente.getTextFieldHotesseSt3().setText(hotesseSt3);
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
						// EN COURS
						Vol v = dao.getVolEnAttenteById(numVol);
						// On réinitialise les champs du formulaire :
						panelModifVolEnAttente.getTextFieldVilleDeDepart().setText(v.getAeroportDepart().getVille());
						panelModifVolEnAttente.getTextFieldPaysDeDepart().setText(v.getAeroportDepart().getPays());
						panelModifVolEnAttente.getTextFieldCodeDep().setText(v.getAeroportDepart().getCodeAeroport());
						panelModifVolEnAttente.getTextFieldVilleDarrivee().setText(v.getAeroportArrivee().getVille());
						panelModifVolEnAttente.getTextFieldPaysDarrivee().setText(v.getAeroportArrivee().getPays());
						panelModifVolEnAttente.getTextFieldCodeArriv().setText(v.getAeroportArrivee().getCodeAeroport());
						
						Date dateHeureDep = v.getDateHeureDepart();
						Date dateHeureArriv = v.getDateHeureArrivee();
						// On passe les dates récupérées en chaînes de caractères
						String dateDepStr = new SimpleDateFormat("dd/MM/yyyy").format(dateHeureDep);
						String heureDepStr = new SimpleDateFormat("HH:mm").format(dateHeureDep);
						String dateArrivStr = new SimpleDateFormat("dd/MM/yyyy").format(dateHeureArriv);
						String heureArrivStr = new SimpleDateFormat("HH:mm").format(dateHeureArriv);
						
						panelModifVolEnAttente.getTextFieldDateDep().setText(dateDepStr);
						// TODO : à corriger (heure)
						panelModifVolEnAttente.getTextFieldHeureDep().setText(heureDepStr);
						panelModifVolEnAttente.getTextFieldDateArriv().setText(dateArrivStr);
						// TODO : à corriger (heure)
						panelModifVolEnAttente.getTextFieldHeureArriv().setText(heureArrivStr);
						// TODO : à corriger (durée)
						// pour la durée, on passe la valeur de l'entier en chaîne de caractères
						panelModifVolEnAttente.getTextFieldDuree().setText(String.valueOf(v.getDuree()));
						// pour le tarif, on passe la valeur du float en chaîne de caractères
						panelModifVolEnAttente.getTextFieldTarif().setText(String.valueOf(v.getTarif()));
						panelModifVolEnAttente.getTextFieldPilote().setText(v.getCodePilote());
						panelModifVolEnAttente.getTextFieldCopilote().setText(v.getCodeCopilote());
						panelModifVolEnAttente.getTextFieldHotesseSt1().setText(v.getCodeHotesseSt1());
						panelModifVolEnAttente.getTextFieldHotesseSt2().setText(v.getCodeHotesseSt2());
						panelModifVolEnAttente.getTextFieldHotesseSt3().setText(v.getCodeHotesseSt3());
						
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
