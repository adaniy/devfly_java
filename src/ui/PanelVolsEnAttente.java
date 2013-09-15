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
