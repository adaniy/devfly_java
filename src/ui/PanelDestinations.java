package ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JLabel;

public class PanelDestinations extends JPanel {
	private JTable tableDestinations;
	private JScrollPane scrollPane;
	private PanelValiderAnnuler panelBoutonsDestinations;
	private JLabel labelMessage;

	/**
	 * Create the panel.
	 */
	public PanelDestinations() {
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
		
		// Les en-têtes :
		String[]headers = {"code AITA", "ville", "pays"};
		
		// Le contenu, sous forme de tableau d'objets :
		final Object[][]values = {
				// chaque sous-tableau correspond à une ligne
				{"BRN", "Berne", "Suisse"},
				{"MAD", "Madrid", "Espagne"}
		};
		
		DefaultTableModel model = new DefaultTableModel(values, headers)
		{
			// pour qu'on ne puisse pas éditer les cellules directement :
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		
		// On donne le model à la table :
		tableDestinations.setModel(model);
		
		labelMessage = new JLabel("");
		add(labelMessage, BorderLayout.NORTH);
		
		// On dimensionne les colonnes :
		tableDestinations.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableDestinations.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableDestinations.getColumnModel().getColumn(2).setPreferredWidth(120);
	}

}
