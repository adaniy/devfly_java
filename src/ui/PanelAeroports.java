package ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;

import dao.MysqlDao;
import model.Aeroport;
import java.awt.Color;
import javax.swing.SwingConstants;

public class PanelAeroports extends JPanel {
	private JTable tableAeroports;
	private JScrollPane scrollPane; // conteneur pour avoir une barre de défilement
	private PanelValiderAnnuler panelBoutonsAeroports;
	private JLabel labelMessage;
	private MysqlDao dao = new MysqlDao();

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public PanelAeroports() throws SQLException {
				
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tableAeroports = new JTable();

		// pour trier en cliquant sur les en-têtes :
		tableAeroports.setAutoCreateRowSorter(true);
		
		scrollPane.setViewportView(tableAeroports);
		
		// pour enlever le fait qu'on puisse déplacer les colonnes :
		tableAeroports.getTableHeader().setReorderingAllowed(false);
		
		panelBoutonsAeroports = new PanelValiderAnnuler();
		add(panelBoutonsAeroports, BorderLayout.SOUTH);
		
		//TODO test à effacer
//		List<Aeroport> aeroports = null;
		
		// On récupère les aéroports :
		List<Aeroport> aeroports = dao.getAllAeroports();
		// Les en-têtes :
		String[]headers = {"code AITA", "ville", "pays"};
		
		// Le contenu (on utilise la méthode statique définie dans la classe Aeroport)
		TableModel model = Aeroport.createTableModelAeroports(headers, aeroports);
		
		// On donne le model à la table :
		tableAeroports.setModel(model);
		
		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
		labelMessage.setForeground(Color.RED);
		add(labelMessage, BorderLayout.NORTH);
	}
}
