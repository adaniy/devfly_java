package util;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class MiseEnForme {
	// cette classe contient des méthodes statiques utilisées à plusieurs reprises dans le code
	// source de l'application, par différentes classes.
	
	// Elle regroupe les méthodes qui ont un lien avec les éléments affichés.
	
	
	// passe la première lettre de la chaîne en majuscule (utilisé pour la ville et le pays d'un nouvel aéroport)
	public static String UpperFirstLetter(String chaine){
		char[] charTable = chaine.toCharArray(); // on récupère un tableau de caractères
		charTable[0] = Character.toUpperCase(charTable[0]); // on passe le premier en majuscule
		return new String(charTable); // on construit 1 String à partir du tableau
	}
	
	// prend en paramètres un tableau de String (villes, codes employés...) et une JComboBox
	// insère les villes / codes employés(...) dans la comboBox
	public static void comboBoxCreation(String[]donnees, JComboBox<String> maComboBox){
		// on donne le tableau de données au model :
		DefaultComboBoxModel<String>model = new DefaultComboBoxModel<>(donnees);
		// on ajoute le model à la combobox :
		maComboBox.setModel(model);
		// on pourra faire défiler les données avec la molette de la souris :
		maComboBox.setMaximumRowCount(6); // 6 données visibles à chaque fois
	}
	
	// dimensionne les colonnes à la bonne taille pour présenter les vols
	public static void columnSizeVols(JTable maTable){
		maTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		maTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		maTable.getColumnModel().getColumn(2).setPreferredWidth(75);
		maTable.getColumnModel().getColumn(3).setPreferredWidth(70);
		maTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		maTable.getColumnModel().getColumn(5).setPreferredWidth(75);
		maTable.getColumnModel().getColumn(6).setPreferredWidth(70);
		maTable.getColumnModel().getColumn(7).setPreferredWidth(130);
		maTable.getColumnModel().getColumn(8).setPreferredWidth(130);
		maTable.getColumnModel().getColumn(9).setPreferredWidth(85);
		maTable.getColumnModel().getColumn(10).setPreferredWidth(60);
		maTable.getColumnModel().getColumn(11).setPreferredWidth(90);
		maTable.getColumnModel().getColumn(12).setPreferredWidth(90);
		maTable.getColumnModel().getColumn(13).setPreferredWidth(90);
		maTable.getColumnModel().getColumn(14).setPreferredWidth(90);
		maTable.getColumnModel().getColumn(15).setPreferredWidth(90);
		maTable.getColumnModel().getColumn(16).setPreferredWidth(60);
	}

}
