package ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;

import model.Vol;
import dao.MysqlDao;

public class PanelVolsProgrammes extends JPanel {
	private JTable tableVolsProgrammes;
	private JScrollPane scrollPane; // conteneur pour avoir une barre de défilement
	private PanelValiderAnnuler panelBoutonsVolsProgrammes;
	private JLabel labelMessage;
	private MysqlDao dao = new MysqlDao();

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public PanelVolsProgrammes() throws SQLException {
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tableVolsProgrammes = new JTable();
		// pour que le tableau ne soit pas redimensionné automatiquement à la longueur de la fenêtre :
		tableVolsProgrammes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// pour trier en cliquant sur les en-têtes
		tableVolsProgrammes.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tableVolsProgrammes);
		
		// pour enlever le fait qu'on puisse déplacer les colonnes :
		tableVolsProgrammes.getTableHeader().setReorderingAllowed(false);
		
		panelBoutonsVolsProgrammes = new PanelValiderAnnuler();
		add(panelBoutonsVolsProgrammes, BorderLayout.SOUTH);
		
		// On récupère les vols programmés :
		List<Vol> volsProgrammes = dao.getAllVolsProgrammes();
		// Les en-têtes : (on trouve le N° de vol en début et fin de tableau pour faciliter la lecture)
		String[]headers = {"n°", "ville départ", "pays dép.", "code dép.",
				"ville arrivée", "pays arr.", "code arr.", "date/heure départ",
				"date/heure arrivée", "durée (mn)", "tarif (€)", "pilote", "copilote", "Hôtesse/St1", "Hôtesse/St2", "Hôtesse/St3", "n°"};
		
		// Le contenu (on utilise la méthode statique définie dans la classe Vol)
		TableModel model = Vol.createTableModelVols(headers, volsProgrammes);
				
		// On donne le model à la table :
		tableVolsProgrammes.setModel(model);
		
		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
		labelMessage.setForeground(Color.RED);
		add(labelMessage, BorderLayout.NORTH);
		
		// On dimensionne les colonnes :
		tableVolsProgrammes.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableVolsProgrammes.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableVolsProgrammes.getColumnModel().getColumn(2).setPreferredWidth(75);
		tableVolsProgrammes.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableVolsProgrammes.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableVolsProgrammes.getColumnModel().getColumn(5).setPreferredWidth(75);
		tableVolsProgrammes.getColumnModel().getColumn(6).setPreferredWidth(70);
		tableVolsProgrammes.getColumnModel().getColumn(7).setPreferredWidth(130);
		tableVolsProgrammes.getColumnModel().getColumn(8).setPreferredWidth(130);
		tableVolsProgrammes.getColumnModel().getColumn(9).setPreferredWidth(85);
		tableVolsProgrammes.getColumnModel().getColumn(10).setPreferredWidth(60);
		tableVolsProgrammes.getColumnModel().getColumn(11).setPreferredWidth(90);
		tableVolsProgrammes.getColumnModel().getColumn(12).setPreferredWidth(90);
		tableVolsProgrammes.getColumnModel().getColumn(13).setPreferredWidth(90);
		tableVolsProgrammes.getColumnModel().getColumn(14).setPreferredWidth(90);
		tableVolsProgrammes.getColumnModel().getColumn(15).setPreferredWidth(90);
		tableVolsProgrammes.getColumnModel().getColumn(16).setPreferredWidth(60);
		
	}

}
