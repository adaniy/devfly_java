package util;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import model.Aeroport;
import dao.MysqlDao;

public class Donnees {
	// cette classe contient des méthodes statiques utilisées à plusieurs reprises dans le code
	// source de l'application, par différentes classes.
	
	// Elle regroupe les méthodes qui touchent directement aux données utilisées.
	
	
	// renvoie les villes proposées par la compagnie sous forme
	// d'un tableau de chaînes de caractères trié par ordre alphabétique
	public static String[] getVillesProposees() throws SQLException{
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

	// renvoie la durée du vol en min par rapport aux dates de départ et d'arrivée en paramètres
	public static int getDureeVol(Date dateHeureDepart, Date dateHeureArrivee){
		// on fait la différence entre les timestamps des 2 dates
		long departMillisecondes = dateHeureDepart.getTime();
		long arriveeMillisecondes = dateHeureArrivee.getTime();
		long dureeMillisecondes = arriveeMillisecondes - departMillisecondes;
		
		return (int) (dureeMillisecondes / 60_000); // en minutes
	}
	
	// Prend en paramètre une date sous forme de chaîne de caractères jj/mm/aaaa
	// Renvoie vrai si la date indiquée est dans le futur
	public static boolean futureDate(String laDate){
		// On crée une date à partir de la chaîne récupérée :
		Date laDateFormatee = null;
		try {
			laDateFormatee = new SimpleDateFormat("dd/MM/yyyy").parse(laDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		// On récupère le timestamp de la date indiquée :
		long timeStampLaDate = laDateFormatee.getTime();
		// On récupère le timestamp actuel :
		Date d = new Date();
		long timeStampActuel = d.getTime();
		
		// On fait la différence entre les 2 timeStamp
		if(timeStampLaDate - timeStampActuel > 0){
			return true; // date future
		}else{
			return false;
		}
	}
	
	// vérifie si la ville en paramètre correspond bien
	// à une destination prévue par la compagnie.
	public static boolean isVillePrevue(String ville){
		// On récupère les villes proposées par la compagnie
		String[] villesPrevues = null;
		try {
			villesPrevues = getVillesProposees();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// On initialise 1 booléen à false :
		boolean villePresente = false;
		
		// On vérifie si la ville sélectionnée est présente dans le tableau :
		for(String v : villesPrevues){
			if(v.equals(ville)){
				villePresente = true;
			}
		}
		return villePresente;
	}

}
