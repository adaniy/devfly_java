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
