package util;

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

}
