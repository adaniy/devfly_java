package model;

import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.*;

import java.sql.SQLException;
import java.util.Arrays;
import dao.MysqlDao;

public class Aeroport {
	private String codeAeroport; // code AITA
	private String ville;
	private String pays;

	public Aeroport(String codeAeroport, String ville, String pays) {
		this.codeAeroport = codeAeroport;
		this.ville = ville;
		this.pays = pays;
	}

	public String getCodeAeroport() {
		return codeAeroport;
	}

	public String getVille() {
		return ville;
	}

	public String getPays() {
		return pays;
	}

	// retourne un tableau de chaines de caractères avec le
	// code aéroport, la ville et le pays
	private String[] toArray(){ // utilisée uniquement dans ce panel
		return new String[]{codeAeroport,ville,pays};
	}

	// Retourne un model à donner à la table
	// On lui passera la liste d'aéroports récupérée du dao :
	public static TableModel createTableModelAeroports(String[]enTete, List<Aeroport> listeAeroports) {
		// le nombre de lignes sera égal aux nombres d'aéroports dans la liste,
		// le nombre de colonnes sera égal à la taille du tableau d'en-têtes
		Object[][] myValues = new Object[listeAeroports.size()][enTete.length];
		// on parcourt les lignes :
		for (int i = 0; i < listeAeroports.size(); i++) {
			// on récupère chaque aéroport
			Aeroport a = listeAeroports.get(i);
			// le sous-tableau (code, ville, pays) vient directement de l'aéroport récupéré
			myValues[i] = a.toArray();
		}
		
		@SuppressWarnings("serial")
		DefaultTableModel myModel = new DefaultTableModel(myValues, enTete) {
			// pour qu'on ne puisse pas éditer les cellules directement :
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		return myModel;
	}

	// prend en paramètre une liste d'aéroports et une JTable
	// crée le model avec les bonnes données et le donne à la JTable
	public static void TableCreation(List<Aeroport> listeAeroports, JTable maJTable){
		// Les en-têtes :
		String[]headers = {"code AITA", "ville", "pays"};
		
		// Le model avec les bonnes données (on utilise la méthode statique définie dans la classe Aeroport)
		TableModel model = createTableModelAeroports(headers, listeAeroports);
		
		// On donne le model à la table :
		maJTable.setModel(model);
	}
}
