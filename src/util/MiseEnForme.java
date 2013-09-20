package util;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Aeroport;
import model.Vol;

public class MiseEnForme {
	// cette classe contient des méthodes statiques utilisées à plusieurs reprises dans le code
	// source de l'application, par différentes classes.
	
	// Elle regroupe les méthodes qui ont un lien avec les éléments affichés.
	
	
	// passe la première lettre de la chaîne en majuscule (utilisé pour la ville et le pays d'un nouvel aéroport)
	public static String UpperCaseFirstLetter(String chaine){
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
	public static void tableColumnWidths(JTable maTable){
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
	
	// Retourne un model à donner à la table
	// On lui passera une liste de vols récupérée du dao :
	public static TableModel createTableModelVols(String[]enTete, List<Vol> listeVols) {
		// le nombre de lignes sera égal aux nombres de vols dans la liste,
		// le nombre de colonnes sera égal à la taille du tableau d'en-têtes
		Object[][] myValues = new Object[listeVols.size()][enTete.length];
		// on parcourt les lignes :
		for (int i = 0; i < listeVols.size(); i++) {
			// on récupère chaque vol
			Vol v = listeVols.get(i);
			// le sous-tableau vient directement du vol récupéré
			myValues[i][0] = v.getId();
			myValues[i][1] = v.getAeroportDepart().getVille();
			myValues[i][2] = v.getAeroportDepart().getPays();
			myValues[i][3] = v.getAeroportDepart().getCodeAeroport();
			myValues[i][4] = v.getAeroportArrivee().getVille();
			myValues[i][5] = v.getAeroportArrivee().getPays();
			myValues[i][6] = v.getAeroportArrivee().getCodeAeroport();
			// on formate les dates pour les afficher correctement :
			myValues[i][7] = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(v.getDateHeureDepart());
			myValues[i][8] = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(v.getDateHeureArrivee());
			myValues[i][9] = v.getDuree();
			// on formate le tarif pour avoir 2 chiffres après la virgule :
			myValues[i][10] = String.format("%.2f",v.getTarif());
			myValues[i][11] = v.getCodePilote();
			myValues[i][12] = v.getCodeCopilote();
			myValues[i][13] = v.getCodeHotesseSt1();
			myValues[i][14] = v.getCodeHotesseSt2();
			myValues[i][15] = v.getCodeHotesseSt3();
			// on répète l'id du vol en fin de tableau pour plus de lisibilité
			myValues[i][16] = v.getId();
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
	
	// prend en paramètre une liste de vols et une JTable
	// crée le model avec les bonnes données et le donne à la JTable
	public static void TableCreationVols(List<Vol> listeVols, JTable maJTable){
		// Les en-têtes : (on trouve le N° de vol en début et fin de tableau pour faciliter la lecture)
		String[]headers = {"n°", "ville départ", "pays dép.", "code dép.",
				"ville arrivée", "pays arr.", "code arr.", "date/heure départ",
				"date/heure arrivée", "durée (mn)", "tarif (€)", "pilote", "copilote", "Hôtesse/St1", "Hôtesse/St2", "Hôtesse/St3", "n°"};
		
		// Le model avec les bonnes données (on utilise la méthode statique définie dans util)
		TableModel model = util.MiseEnForme.createTableModelVols(headers, listeVols);
				
		// On donne le model à la table :
		maJTable.setModel(model);
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
	public static void TableCreationAeroports(List<Aeroport> listeAeroports, JTable maJTable){
		// Les en-têtes :
		String[]headers = {"code AITA", "ville", "pays"};
		
		// Le model avec les bonnes données (on utilise la méthode statique définie dans la classe Aeroport)
		TableModel model = util.MiseEnForme.createTableModelAeroports(headers, listeAeroports);
		
		// On donne le model à la table :
		maJTable.setModel(model);
	}
}
