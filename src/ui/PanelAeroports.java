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
		
		// conteneur pour avoir une barre de défilement
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tableAeroports = new JTable();
//		// pour que le tableau ne soit pas redimensionné automatiquement à la longueur de la fenêtre :
//		tableAeroports.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// pour trier en cliquant sur les en-têtes :
		tableAeroports.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tableAeroports);
		
		// pour enlever le fait qu'on puisse déplacer les colonnes :
		tableAeroports.getTableHeader().setReorderingAllowed(false);
		
		panelBoutonsAeroports = new PanelValiderAnnuler();
		add(panelBoutonsAeroports, BorderLayout.SOUTH);
		
		// On récupère les aéroports :
		List<Aeroport> aeroports = dao.getAllAeroports();
		// Les en-têtes :
		String[]headers = {"code AITA", "ville", "pays"};
		
		// Le contenu, sous forme de tableau d'objets :
		// (on utilise la méthode createTableModel défine plus loin)
		TableModel model = createTableModel(headers, aeroports);
		
		// On donne le model à la table :
		tableAeroports.setModel(model);
		
		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		add(labelMessage, BorderLayout.NORTH);
		
//		// On dimensionne les colonnes :
//		tableAeroports.getColumnModel().getColumn(0).setPreferredWidth(80);
//		tableAeroports.getColumnModel().getColumn(1).setPreferredWidth(120);
//		tableAeroports.getColumnModel().getColumn(2).setPreferredWidth(120);
	}
	
	// méthode private qui ne servira que dans ce panel.
	// On lui passera la liste d'aéroports récupérée du dao :
	TableModel createTableModel(String[]enTete, List<Aeroport> listeAeroports) {
		// le nombre de lignes est égal aux nombres d'aéroports dans la liste,
		// le nombre de colonnes est égal à la taille du tableau d'en-têtes
		final Object[][] myValues = new Object[listeAeroports.size()][enTete.length];
		// on parcourt les lignes :
		for (int i = 0; i < listeAeroports.size(); i++) {
			// on récupère chaque aéroport
			Aeroport a = listeAeroports.get(i);
			// le sous-tableau (code, ville, pays) vient directement de l'aéroport récupéré
			myValues[i] = a.toArray();
		}
		// TODO : reprendre et commenter ici
		DefaultTableModel myModel = new DefaultTableModel(myValues, enTete) {
			@Override
			public Class<?> getColumnClass(int arg0) {
				return myValues[0][arg0].getClass();
			}

			// pour qu'on ne puisse pas éditer les cellules directement :
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		return myModel;
	}

}
