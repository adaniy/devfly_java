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
		
		@SuppressWarnings("serial")
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

<<<<<<< HEAD
=======
	// renvoie les villes proposées par la compagnie sous forme
	// d'un tableau de chaînes de caractères trié par ordre alphabétique
	public static String[] getVillesProposees() throws SQLException{ // méthode appelée depuis ce panel directement + via le panelNouvelAeroport
		// On récupère la liste des objets Aeroport :
		MysqlDao dao = new MysqlDao();
		List<Aeroport> aeroports = dao.getAllAeroports();
		
		// On initialise un tableau de chaînes de caractères de la taille
		// de la liste, on y placera les villes.
		String[]villes = new String[aeroports.size()];
		
		// On parcourt la liste :
		for(int i = 0 ; i < aeroports.size(); i++){
			String ville = aeroports.get(i).getVille(); // on récupère la ville
			// On ajoute la ville dans le tableau :
			villes[i] = ville;
		}
		
		Arrays.sort(villes); // pour trier les villes par ordre alphabétique
		
		return villes;
	}

	// prend en paramètres un tableau de villes (String) et une JComboBox
	// insère les villes dans la comboBox
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void comboBoxCreation(String[]villes, JComboBox maComboBox){ // méthode appelée depuis ce panel directement + via le panelNouvelAeroport
		// on donne le tableau de villes au model :
		DefaultComboBoxModel<String>model = new DefaultComboBoxModel<>(villes);
		// on ajoute le model à la combobox :
		maComboBox.setModel(model);
		// on pourra faire défiler les villes avec la molette de la souris :
		maComboBox.setMaximumRowCount(6); // 6 villes visibles à chaque fois
	}

	// TODO : voir si bien placé ?
>>>>>>> 952ed32876feb7e32b9d31dc233b1889dd72bdef
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
