package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Vol {
	private String id; // ex : DF1
	private Aeroport aeroportDepart;
	private Aeroport aeroportArrivee;
	private Date dateHeureDepart;
	private Date dateHeureArrivee;
	private int duree; // calculée en minutes
	private float tarif;
	private String codePilote;
	private String codeCopilote;
	private String codeHotesseSt1;
	private String codeHotesseSt2;
	private String codeHotesseSt3;

	// un constructeur complet
	public Vol(String id, Aeroport aeroportDepart, Aeroport aeroportArrivee,
			Date dateHeureDepart, Date dateHeureArrivee, int duree, float tarif, String codePilote,
			String codeCopilote, String codeHotesseSt1, String codeHotesseSt2,
			String codeHotesseSt3) {
		this.id = id;
		this.aeroportDepart = aeroportDepart;
		this.aeroportArrivee = aeroportArrivee;
		this.dateHeureDepart = dateHeureDepart;
		this.dateHeureArrivee = dateHeureArrivee;
		this.duree = duree;
		this.tarif = tarif;
		this.codePilote = codePilote;
		this.codeCopilote = codeCopilote;
		this.codeHotesseSt1 = codeHotesseSt1;
		this.codeHotesseSt2 = codeHotesseSt2;
		this.codeHotesseSt3 = codeHotesseSt3;
	}

	// un constructeur sans les codes "employés" ni l'"id" (pour la création d'un vol "en attente")
	public Vol(Aeroport aeroportDepart, Aeroport aeroportArrivee,
			Date dateHeureDepart, Date dateHeureArrivee, int duree, float tarif) {
		this.aeroportDepart = aeroportDepart;
		this.aeroportArrivee = aeroportArrivee;
		this.dateHeureDepart = dateHeureDepart;
		this.dateHeureArrivee = dateHeureArrivee;
		this.duree = duree;
		this.tarif = tarif;
	}	

	// Remarque : pas de setter sur l'id du vol qui ne doit pas être modifié.

	public String getId() {
		return id;
	}

	public Aeroport getAeroportDepart() {
		return aeroportDepart;
	}

	public void setAeroportDepart(Aeroport aeroportDepart) {
		this.aeroportDepart = aeroportDepart;
	}

	public Aeroport getAeroportArrivee() {
		return aeroportArrivee;
	}

	public void setAeroportArrivee(Aeroport aeroportArrivee) {
		this.aeroportArrivee = aeroportArrivee;
	}

	public Date getDateHeureDepart() {
		return dateHeureDepart;
	}

	public void setDateHeureDepart(Date dateHeureDepart) {
		this.dateHeureDepart = dateHeureDepart;
	}

	public Date getDateHeureArrivee() {
		return dateHeureArrivee;
	}

	public void setDateHeureArrivee(Date dateHeureArrivee) {
		this.dateHeureArrivee = dateHeureArrivee;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public float getTarif() {
		return tarif;
	}

	public void setTarif(int tarif) {
		this.tarif = tarif;
	}

	public String getCodePilote() {
		return codePilote;
	}

	public void setCodePilote(String codePilote) {
		this.codePilote = codePilote;
	}

	public String getCodeCopilote() {
		return codeCopilote;
	}

	public void setCodeCopilote(String codeCopilote) {
		this.codeCopilote = codeCopilote;
	}

	public String getCodeHotesseSt1() {
		return codeHotesseSt1;
	}

	public void setCodeHotesseSt1(String codeHotesseSt1) {
		this.codeHotesseSt1 = codeHotesseSt1;
	}

	public String getCodeHotesseSt2() {
		return codeHotesseSt2;
	}

	public void setCodeHotesseSt2(String codeHotesseSt2) {
		this.codeHotesseSt2 = codeHotesseSt2;
	}

	public String getCodeHotesseSt3() {
		return codeHotesseSt3;
	}

	public void setCodeHotesseSt3(String codeHotesseSt3) {
		this.codeHotesseSt3 = codeHotesseSt3;
	}

	// Retourne un model à donner à la table
	// On lui passera une liste de vols récupérée du dao :
	public static TableModel createTableModelVols(String[]enTete, List<Vol> listeVols) {
		// le nombre de lignes sera égal aux nombres de vols dans la liste,
		// le nombre de colonnes sera égal à la taille du tableau d'en-têtes
		final Object[][] myValues = new Object[listeVols.size()][enTete.length];
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

	// prend en paramètre une liste de vols et une JTable
	// crée le model avec les bonnes données et le donne à la JTable
	public static void TableCreation(List<Vol> listeVols, JTable maJTable){
		// Les en-têtes : (on trouve le N° de vol en début et fin de tableau pour faciliter la lecture)
		String[]headers = {"n°", "ville départ", "pays dép.", "code dép.",
				"ville arrivée", "pays arr.", "code arr.", "date/heure départ",
				"date/heure arrivée", "durée (mn)", "tarif (€)", "pilote", "copilote", "Hôtesse/St1", "Hôtesse/St2", "Hôtesse/St3", "n°"};
		
		// Le model avec les bonnes données (on utilise la méthode statique définie dans la classe Vol)
		TableModel model = createTableModelVols(headers, listeVols);
				
		// On donne le model à la table :
		maJTable.setModel(model);
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

	// renvoie la durée du vol en min par rapport aux dates de départ et d'arrivée en paramètres
	public static int getDureeVol(Date dateHeureDepart, Date dateHeureArrivee){
		// on fait la différence entre les timestamps des 2 dates
		long departMillisecondes = dateHeureDepart.getTime();
		long arriveeMillisecondes = dateHeureArrivee.getTime();
		long dureeMillisecondes = arriveeMillisecondes - departMillisecondes;
		
		return (int) (dureeMillisecondes / 60_000); // en minutes
	}
}
