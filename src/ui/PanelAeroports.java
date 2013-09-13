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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;

public class PanelAeroports extends JPanel {
	private JTable tableAeroports;
	private JScrollPane scrollPane; // conteneur pour avoir une barre de défilement
	private JLabel labelMessage;
	private MysqlDao dao = new MysqlDao();
	private PanelFormAeroport panelFormAeroport;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public PanelAeroports() throws SQLException {
				
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tableAeroports = new JTable();
		tableAeroports.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
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
		// On utilise pour cela la méthode statique définie dans Aeroport
		Aeroport.TableCreation(aeroports, tableAeroports);
		
		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
		labelMessage.setForeground(Color.RED);
		add(labelMessage, BorderLayout.NORTH);
		
		panelFormAeroport = new PanelFormAeroport();
		GridBagLayout gridBagLayout = (GridBagLayout) panelFormAeroport.getLayout();
		gridBagLayout.rowHeights = new int[]{24, 21, 0, 0, 0, 0, 34};
		gridBagLayout.columnWidths = new int[]{21, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		add(panelFormAeroport, BorderLayout.SOUTH);
	}

	public JTable getTableAeroports() {
		return tableAeroports;
	}

}
