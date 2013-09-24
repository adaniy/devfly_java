package ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Point;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;

import dao.MysqlDao;
import model.Aeroport;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public class PanelAeroports extends JPanel {
	private JTable tableAeroports;
	private JScrollPane scrollPane; // conteneur pour avoir une barre de défilement
	private JLabel labelMessage;
	private MysqlDao dao = new MysqlDao();
	private PanelModifAeroport panelModifAeroport;

	
	public PanelAeroports() throws SQLException {
				
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tableAeroports = new JTable();
		tableAeroports.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// on récupère l'endroit où a eu lieu l'événement (= le clic)
				Point p = arg0.getPoint();
				int row = tableAeroports.rowAtPoint(p); // renvoie la ligne sous le point
				// On convertit les row du tableau en row du modèle pour maintenir la cohérence 
				// entre les cellules de la présentation et les cellules du model (source de données)
				int modelRow = tableAeroports.convertRowIndexToModel(row);
				TableModel model = tableAeroports.getModel();
				// String qui représentent les valeurs récupérées :
				String code = (String) model.getValueAt(modelRow, 0);
				String ville = (String) model.getValueAt(modelRow, 1);
				String pays = (String) model.getValueAt(modelRow, 2);
				
				// On place les valeurs récupérées dans les champs du formulaire
				panelModifAeroport.getTextFieldCode().setText(code);
				panelModifAeroport.getTextFieldVille().setText(ville);
				panelModifAeroport.getTextFieldPays().setText(pays);
				
				// on supprime le message éventuellement saisi
				panelModifAeroport.getLblMessage().setText("");
			}
		});

		// pour trier en cliquant sur les en-têtes :
		tableAeroports.setAutoCreateRowSorter(true);
		
		scrollPane.setViewportView(tableAeroports);
		
		// pour enlever le fait qu'on puisse déplacer les colonnes :
		tableAeroports.getTableHeader().setReorderingAllowed(false);
		
		// On récupère les aéroports :
		List<Aeroport> aeroports = dao.getAllAeroports();
		
		// On crée le model avec les bonnes données et on le donne à la JTable
		// On utilise pour cela la méthode statique définie dans "util" :
		util.MiseEnForme.TableCreationAeroports(aeroports, tableAeroports);
		
		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
		labelMessage.setForeground(Color.RED);
		add(labelMessage, BorderLayout.NORTH);
		
		panelModifAeroport = new PanelModifAeroport();
		GridBagLayout gridBagLayout = (GridBagLayout) panelModifAeroport.getLayout();
		gridBagLayout.rowHeights = new int[]{24, 21, 0, 0, 0, 0, 34};
		gridBagLayout.columnWidths = new int[]{21, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		add(panelModifAeroport, BorderLayout.SOUTH);	
	}

	public JTable getTableAeroports() {
		return tableAeroports;
	}

}
