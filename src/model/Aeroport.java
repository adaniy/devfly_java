package model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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

	public void setCodeAeroport(String codeAeroport) {
		this.codeAeroport = codeAeroport;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	// retourne un tableau de chaines de caractères avec le
	// code aéroport, la ville et le pays
	private String[] toArray(){ // utilisée uniquement dans ce panel
		return new String[]{codeAeroport,ville,pays};
	}

	// TODO : voir si bien placé ?
	// Retourne un model à donner à la table
	// On lui passera la liste d'aéroports récupérée du dao :
	public static TableModel createTableModelAeroports(String[]enTete, List<Aeroport> listeAeroports) {
		// le nombre de lignes sera égal aux nombres d'aéroports dans la liste,
		// le nombre de colonnes sera égal à la taille du tableau d'en-têtes
		final Object[][] myValues = new Object[listeAeroports.size()][enTete.length];
		// on parcourt les lignes :
		for (int i = 0; i < listeAeroports.size(); i++) {
			// on récupère chaque aéroport
			Aeroport a = listeAeroports.get(i);
			// le sous-tableau (code, ville, pays) vient directement de l'aéroport récupéré
			myValues[i] = a.toArray();
		}
		
		DefaultTableModel myModel = new DefaultTableModel(myValues, enTete) {
			// pour renseigner la JTable avec le type exact contenu dans la colonne
			// pour avoir un tri cohérent
			// Principe : on prend la première ligne, et on lui donne le type des éléments
			// contenus dans chaque colonne.
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

	// TODO : voir si bien placé ?
	// prend en paramètre une liste d'aéroports et une JTable
	// crée le model avec les bonnes données et le donne à la JTable
	public static void TableCreation(List<Aeroport> listeAeroports, JTable maJTable){
		// Les en-têtes :
		String[]headers = {"code AITA", "ville", "pays"};
		
		// Le model avec les bonnes données (on utilise la méthode statique définie dans la classe Aeroport)
		TableModel model = Aeroport.createTableModelAeroports(headers, listeAeroports);
		
		// On donne le model à la table :
		maJTable.setModel(model);
	}
}
