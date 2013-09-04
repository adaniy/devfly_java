package ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

public class PanelVols extends JPanel {
	private JTable tableVols;
	private JScrollPane scrollPane;
	private PanelValiderAnnuler panelBoutonsVols;
	private JLabel labelMessage;

	/**
	 * Create the panel.
	 */
	public PanelVols() {
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
		
		tableVols = new JTable();
		// pour que le tableau ne soit pas redimensionné automatiquement à la longueur de la fenêtre :
		tableVols.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(tableVols);
		
		// pour enlever le fait qu'on puisse déplacer les colonnes :
		tableVols.getTableHeader().setReorderingAllowed(false);
		
		panelBoutonsVols = new PanelValiderAnnuler();
		add(panelBoutonsVols, BorderLayout.SOUTH);
		
		// Les en-têtes : (on trouve le N° de vol en début et fin de tableau pour faciliter la lecture)
		String[]headers = {"n°", "ville départ", "code AITA (pays)",
				"ville arrivée", "code AITA (pays)", "date/heure départ",
				"date/heure arrivée", "durée (mn)", "tarif (€)", "pilote", "copilote", "Hôtesse/St1", "Hôtesse/St2", "Hôtesse/St3", "n°"};
		
		// Le contenu, sous forme de tableau d'objets :
		final Object[][]values = {
				// chaque sous-tableau correspond à une ligne
				{"DF5", "Berne", "BRN (Suisse)", "Sydney", "SYD (Australie)", "07/12/2013 06:50", "07/12/2013 20:05", 795, 1472, "P0005", "C0002", "H0004", "H0008", "H0013", "DF5"},
				{"DF6", "Berne", "BRN (Suisse)", "Sydney", "SYD (Australie)", "14/12/2013 06:50", "14/12/2013 20:05", 795, 1472, "P0005", "C0002", "H0004", "H0008", "H0013", "DF6"}
		};
		
		DefaultTableModel model = new DefaultTableModel(values, headers)
		{
			// pour qu'on ne puisse pas éditer les cellules directement :
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
			
//			@Override
//			public Class<?> getColumnClass(int arg0){
//				// va indiquer la classe des éléments contenus dans chaque colonne de la première ligne :
//				return values[0][arg0].getClass();
//			}
		};
		
		// On donne le model à la table :
		tableVols.setModel(model);
		
		labelMessage = new JLabel("");
		add(labelMessage, BorderLayout.NORTH);
		
		// On dimensionne les colonnes :
		tableVols.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableVols.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableVols.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableVols.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableVols.getColumnModel().getColumn(4).setPreferredWidth(120);
		tableVols.getColumnModel().getColumn(5).setPreferredWidth(140);
		tableVols.getColumnModel().getColumn(6).setPreferredWidth(140);
		tableVols.getColumnModel().getColumn(7).setPreferredWidth(85);
		tableVols.getColumnModel().getColumn(8).setPreferredWidth(60);
		tableVols.getColumnModel().getColumn(9).setPreferredWidth(90);
		tableVols.getColumnModel().getColumn(10).setPreferredWidth(90);
		tableVols.getColumnModel().getColumn(11).setPreferredWidth(90);
		tableVols.getColumnModel().getColumn(12).setPreferredWidth(90);
		tableVols.getColumnModel().getColumn(13).setPreferredWidth(90);
		tableVols.getColumnModel().getColumn(14).setPreferredWidth(60);
		
	}

}
