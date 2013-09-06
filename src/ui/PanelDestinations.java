package ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;

import dao.MysqlDao;
import model.Aeroport;

public class PanelDestinations extends JPanel {
	private JTable tableDestinations;
	private JScrollPane scrollPane;
	private PanelValiderAnnuler panelBoutonsDestinations;
	private JLabel labelMessage;
	private MysqlDao dao = new MysqlDao();

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public PanelDestinations() throws SQLException {
		// TODO : reprendre et commenter cette page
		
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tableDestinations = new JTable();
		// pour que le tableau ne soit pas redimensionné automatiquement à la longueur de la fenêtre :
		tableDestinations.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableDestinations.setAutoCreateRowSorter(true); // pour trier en cliquant sur les en-têtes
		scrollPane.setViewportView(tableDestinations);
		
		// pour enlever le fait qu'on puisse déplacer les colonnes :
		tableDestinations.getTableHeader().setReorderingAllowed(false);
		
		panelBoutonsDestinations = new PanelValiderAnnuler();
		add(panelBoutonsDestinations, BorderLayout.SOUTH);
		
		// On récupère les aéroports :
		List<Aeroport> aeroports = dao.getAllAeroports();
		// Les en-têtes :
		String[]headers = {"code AITA", "ville", "pays"};
		
		// Le contenu, sous forme de tableau d'objets :
		TableModel model = createTableModel(headers, aeroports);
		
//		DefaultTableModel model = new DefaultTableModel(values, headers)
//		{
//			// pour qu'on ne puisse pas éditer les cellules directement :
//			@Override
//			public boolean isCellEditable(int arg0, int arg1) {
//				return false;
//			}
//		};
		
		// On donne le model à la table :
		tableDestinations.setModel(model);
		
		labelMessage = new JLabel("");
		add(labelMessage, BorderLayout.NORTH);
		
		// On dimensionne les colonnes :
		tableDestinations.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableDestinations.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableDestinations.getColumnModel().getColumn(2).setPreferredWidth(120);
	}
	
	// méthode private qui ne servira que dans ce panel.
	// On lui passera la liste qu'on récupèrera du dao :
	TableModel createTableModel(String[]enTete, List<Aeroport> listeAeroports) {
		// le nombre de lignes est égal aux nombres d'aéroports dans la liste,
		// le nombre de colonnes est égal à la taille du tableau d'en-têtes
		final Object[][] myValues = new Object[listeAeroports.size()][enTete.length];
		for (int i = 0; i < listeAeroports.size(); i++) {
			Aeroport a = listeAeroports.get(i);
			myValues[i] = a.toArray();
		}
		DefaultTableModel myModel = new DefaultTableModel(myValues, enTete) {
			@Override
			public Class<?> getColumnClass(int arg0) {
				return myValues[0][arg0].getClass();
			}

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		return myModel;
	}

}
