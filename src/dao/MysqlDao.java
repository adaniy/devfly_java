package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Aeroport;
import model.Vol;

public class MysqlDao {
	private String datasource = "jdbc:mysql://localhost:3306/DEVFLY";
	private String user = "greta";
	private String password = "gretatest";

	// retourne les vols "programmés" sous forme d'une liste
//	public List<Vol> getAllVolsProgrammes() throws SQLException {
//		List<Vol> vols = new ArrayList<>();
//		// on se connecte à la BDD
//		Connection connection = DriverManager.getConnection(datasource, user,
//				password);
//		// on crée et exécute une requête préparée
//		// TODO : compléter la requête pour avoir les destinations de départ ET d'arrivée
//		// TODO suite : 2 requêtes ?
//		String sql = "SELECT * FROM vol V INNER JOIN travailler T ON V.numvol = T.vol";
//		PreparedStatement stmt = connection.prepareStatement(sql);
//		ResultSet result = stmt.executeQuery();
//		while (result.next()) {
//			String id = result.getString("numvol");
//			String villeDepart = result.getString("lieudep");
//			String villeArrivee = result.getString("villeArrivee");
//			Date dateHeureDepart = result.getDate("dateheuredep");
//			Date dateHeureArrivee = result.getDate("dateheurearrivee");
//			
//			// pour calculer la durée du vol, on récupère d'abord les dates en millisecondes
//			long departMillisecondes = dateHeureDepart.getTime();
//			long arriveeMillisecondes = dateHeureArrivee.getTime();
//			int duree = (int) (arriveeMillisecondes - departMillisecondes);
//			
//			float tarif = result.getFloat("tarif");
//			String codePilote = result.getString("pilote");
//			String codeCopilote = result.getString("copilote");
//			String codeHotesseSt1 = result.getString("hotesse_steward1");
//			String codeHotesseSt2 = result.getString("hotesse_steward2");
//			String codeHotesseSt3 = result.getString("hotesse_steward3");
//			
//			// on crée les objet "Aeroport" de départ et d'arrivée avec les informations récupérées
//			Aeroport aeroportDepart = new Aeroport(codeAeroport, ville, pays);
//			Aeroport aeroportArrivee = new Aeroport(codeAeroport, ville, pays);
//			
//			// on crée un objet Vol avec les informations récupérées
//			Vol v = new Vol(id, aeroportDepart, aeroportArrivee, dateHeureDepart, duree, tarif, codePilote, codeCopilote, codeHotesseSt1, codeHotesseSt2, codeHotesseSt3);
//			// on l'ajoute à la liste
//			vols.add(v);
//		}
//		connection.close();
//		return vols;
//	}

	// retourne les aéroports sous forme d'une liste
	public List<Aeroport> getAllAeroports() throws SQLException {
		List<Aeroport> aeroports = new ArrayList<>();
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user,
				password);
		// on crée et exécute une requête préparée
		String sql = "SELECT * FROM destination";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			String code = result.getString("codeaeroport");
			String ville = result.getString("ville");
			String pays = result.getString("pays");
			// on crée un objet aéroport avec les informations récupérées
			Aeroport a = new Aeroport(code, ville, pays);
			// on l'ajoute à la liste
			aeroports.add(a);
		}
		connection.close();
		return aeroports;
	}
	
	// TODO : à optimiser ?
	// ajoute un nouvel aéroport en base
	public int addNewAeroport(Aeroport a) throws SQLException{
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user,
				password);
		// on regarde si le code aéroport existe déjà en base, avec une requête préparée
		String sql1 = "SELECT * from destination WHERE codeaeroport = ?";
		PreparedStatement stmt1 = connection.prepareStatement(sql1);
		stmt1.setString(1, a.getCodeAeroport());
		ResultSet result1 = stmt1.executeQuery();
		// si l'aéroport existe déjà, on sort de la fonction et on renvoie "2".
		if (result1.next()) {
			String id = result1.getString("codeaeroport");
			if(id.equals(a.getCodeAeroport())){
				return 2;
			}
		}
		
		// s'il n'existe pas déjà, on crée et exécute une requête
		// préparée pour insérer l'aéroport
		String sql2 = "INSERT INTO destination values(?, ?, ?)";
		PreparedStatement stmt2 = connection.prepareStatement(sql2);
		stmt2.setString(1, a.getCodeAeroport());
		stmt2.setString(2, a.getVille());
		stmt2.setString(3, a.getPays());
		int result2 = stmt2.executeUpdate(); // renvoie le nb d'enregistrements impactés
		connection.close();
		return result2; // doit renvoyer "1"
	}
}
