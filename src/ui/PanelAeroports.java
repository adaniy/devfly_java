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
		
		// On récupère les aéroports :
		List<Aeroport> aeroports = dao.getAllAeroports();
		
		// On crée le model avec les bonnes données et on le donne à la JTable
		// On utilise pour cela la méthode statique définie dans Aeroport
		Aeroport.TableCreation(aeroports, tableAeroports);
		
		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
		labelMessage.setForeground(Color.RED);
		add(labelMessage, BorderLayout.NORTH);
	}

	public JTable getTableAeroports() {
		return tableAeroports;
	}

}
