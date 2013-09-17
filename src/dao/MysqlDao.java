package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.binary.StringUtils;


import model.Aeroport;
import model.Vol;

public class MysqlDao {
	private String datasource = "jdbc:mysql://localhost:3306/DEVFLY";
	private String user = "greta";
	private String password = "gretatest";

	// retourne les vols "programmés" sous forme d'une liste
	public List<Vol> getAllVolsProgrammes() throws SQLException {
		List<Vol> vols = new ArrayList<>();
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on crée et exécute une requête préparée pour récupérer
		// les informations sur le vol et ses employés
		String sql1 = "SELECT V.numvol, V.lieudep, V.lieuarriv, V.dateheuredep,"
				+ "V.dateheurearrivee, V.tarif, T.pilote, T.copilote,"
				+ "T.hotesse_steward1, T.hotesse_steward2, T.hotesse_steward3 FROM vol V INNER JOIN travailler T ON V.numvol = T.vol";
		PreparedStatement stmt1 = connection.prepareStatement(sql1);
		ResultSet result1 = stmt1.executeQuery();
		while (result1.next()) {
			String id = result1.getString("numvol");
			String villeDepart = result1.getString("lieudep");
			String villeArrivee = result1.getString("lieuarriv");
			
			// On récupère les dates sous forme de timestamp. On formatera à l'affichage.
			Date dateHeureDepart = result1.getTimestamp("dateheuredep");
			Date dateHeureArrivee = result1.getTimestamp("dateheurearrivee");
			
			// on calcule la durée du vol
			int duree = Vol.getDureeVol(dateHeureDepart, dateHeureArrivee);
			
			float tarif = result1.getFloat("tarif");
			String codePilote = result1.getString("pilote");
			String codeCopilote = result1.getString("copilote");
			String codeHotesseSt1 = result1.getString("hotesse_steward1");
			String codeHotesseSt2 = result1.getString("hotesse_steward2");
			String codeHotesseSt3 = result1.getString("hotesse_steward3");
			
			// on récupère les aéroports de départ et d'arrivée
			Aeroport aeroportDepart = getAeroportByVille(villeDepart);
			Aeroport aeroportArrivee = getAeroportByVille(villeArrivee);
			
			// on crée un objet Vol avec les informations récupérées
			Vol v = new Vol(id, aeroportDepart, aeroportArrivee, dateHeureDepart, dateHeureArrivee, duree, tarif, codePilote, codeCopilote, codeHotesseSt1, codeHotesseSt2, codeHotesseSt3);
			// on l'ajoute à la liste
			vols.add(v);
		}
		connection.close();
		return vols;
	}

	// retourne les vols "en attente" sous forme d'une liste
	public List<Vol> getAllVolsEnAttente() throws SQLException {
		List<Vol> vols = new ArrayList<>();
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on crée et exécute une requête préparée pour récupérer les informations sur le vol
		String sql1 = "SELECT * FROM vol_tmp";
		PreparedStatement stmt1 = connection.prepareStatement(sql1);
		ResultSet result1 = stmt1.executeQuery();
		while (result1.next()) {
			String id = result1.getString("numvol");
			String villeDepart = result1.getString("lieudep");
			String villeArrivee = result1.getString("lieuarriv");
			
			// On récupère les dates sous forme de timestamp. On formatera à l'affichage.
			Date dateHeureDepart = result1.getTimestamp("dateheuredep");
			Date dateHeureArrivee = result1.getTimestamp("dateheurearrivee");
			
			// on calcule la durée du vol
			int duree = Vol.getDureeVol(dateHeureDepart, dateHeureArrivee);
			
			float tarif = result1.getFloat("tarif");
			
			String codePilote = result1.getString("pilote");
			String codeCopilote = result1.getString("copilote");
			String codeHotesseSt1 = result1.getString("hotesse_steward1");
			String codeHotesseSt2 = result1.getString("hotesse_steward2");
			String codeHotesseSt3 = result1.getString("hotesse_steward3");
			
			// on récupère les aéroports de départ et d'arrivée
			Aeroport aeroportDepart = getAeroportByVille(villeDepart);
			Aeroport aeroportArrivee = getAeroportByVille(villeArrivee);
			
			// on crée un objet Vol avec les informations récupérées
			Vol v = new Vol(id, aeroportDepart, aeroportArrivee, dateHeureDepart, dateHeureArrivee, duree, tarif,
					codePilote, codeCopilote, codeHotesseSt1, codeHotesseSt2, codeHotesseSt3);
			// on l'ajoute à la liste
			vols.add(v);
		}
		connection.close();
		return vols;
	}

	// retourne les aéroports sous forme d'une liste
	public List<Aeroport> getAllAeroports() throws SQLException {
		List<Aeroport> aeroports = new ArrayList<>();
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
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

	// ajoute un nouvel aéroport en base et renvoie "vrai" si l'insertion s'est bien passée
	public boolean addNewAeroport(Aeroport a) throws SQLException{
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on regarde si le code aéroport existe déjà en base
		if(doesAirportAlreadyExist(a.getCodeAeroport())){
			connection.close();
			return false; // s'il existe déjà, l'ajout ne se fait pas, on renvoie "false"
		}else{
			// sinon, on crée et exécute une requête préparée pour insérer l'aéroport
			String sql = "INSERT INTO destination values(?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, a.getCodeAeroport());
			stmt.setString(2, a.getVille());
			stmt.setString(3, a.getPays());
			if(stmt.executeUpdate() == 1){ // si le nombre d'enregistrements impactés est "1", c'est ok
				connection.close();
				return true;
			}
			// S'il y a eu un problème quelconque
			connection.close();
			return false;
		}
	}

	// ajoute un nouveau vol en base (table vol_tmp)
	public int addNewVol(Vol v) throws SQLException{
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on crée et exécute une requête préparée pour insérer le vol
		String sql = "INSERT INTO vol_tmp VALUES(?, ?, ?, ?, ?, ?, '', '', '', '', '')";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		// On récupère la valeur du prochain id à insérer dans la table vol_tmp.
		stmt.setString(1, getNextId());
		
		stmt.setString(2, v.getAeroportDepart().getVille());
		stmt.setString(3, v.getAeroportArrivee().getVille());
		// on transforme la date util en timestamp SQL. Pour cela, on utilise le timestamp des dates.
		// (rq : avec une java.sql.Date, on ne récupèrerait pas les heures et minutes)
		stmt.setTimestamp(4, new java.sql.Timestamp(v.getDateHeureDepart().getTime()));
		stmt.setTimestamp(5, new java.sql.Timestamp(v.getDateHeureArrivee().getTime()));
		
		stmt.setFloat(6, v.getTarif());
		
		int result = stmt.executeUpdate(); // renvoie le nb d'enregistrements impactés
		connection.close();
		return result; // doit renvoyer "1"
	}

	// renvoie un objet Aeroport correspondant à la ville en paramètre
	public Aeroport getAeroportByVille(String ville) throws SQLException{
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on crée et exécute une requête préparée
		String sql = "SELECT * FROM destination WHERE ville = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		// on valorise le paramètre
		stmt.setString(1, ville);
		ResultSet result = stmt.executeQuery();
		// On récupère le résultat de la requête
		result.next();
		String code = result.getString("codeaeroport");
		String pays = result.getString("pays");
		connection.close();
		// on crée un objet Aeroport avec les éléments récupérés :
		return new Aeroport(code, ville, pays);
	}

	// renvoie un objet Aeroport correspondant au code AITA en paramètre
	public Aeroport getAeroportByCode(String code) throws SQLException{
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on crée et exécute une requête préparée
		String sql = "SELECT * FROM destination WHERE codeaeroport = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		// on valorise le paramètre
		stmt.setString(1, code);
		ResultSet result = stmt.executeQuery();
		// On récupère le résultat de la requête
		result.next();
		String ville = result.getString("ville");
		String pays = result.getString("pays");
		connection.close();
		// on crée un objet Aeroport avec les éléments récupérés :
		return new Aeroport(code, ville, pays);
	}

	// renvoie vrai si l'aéroport existe déjà en base
	public boolean doesAirportAlreadyExist(String codeAeroport) throws SQLException{
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on cherche l'aéroport, avec une requête préparée (rappel = le code aéroport est la clé primaire)
		String sql = "SELECT * from destination WHERE codeaeroport = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		// on valorise le paramètre
		stmt.setString(1, codeAeroport);
		ResultSet result = stmt.executeQuery();
		// si l'aéroport existe déjà, on renvoie vrai.
		if (result.next()) {
			connection.close();
			return true;
		}
		connection.close();
		return false;
	}

	// renvoie le prochain ID à insérer dans la table vol_tmp
	public String getNextId() throws SQLException{
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// On récupère les numvol de la table vol_tmp.
		String sql = "SELECT numvol FROM vol_tmp";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		// On va chercher l'id max de la table. On initialise idMax à zéro.
		int idMax = 0;
		// On parcourt les résultats de la requête.
		while (result.next()) {
			// On ne prend que la fin de la chaîne. Ex : pour le vol "TMP12", on veut récupérer "12".
			// On récupère donc la chaine à partir du 4ème caractère (on enlève "TMP")
			String numvol = result.getString("numvol").substring(3);

			// On transforme la chaîne récupérée en int
			int nb = Integer.parseInt(numvol);
			
			// On récupère la plus grande valeur de la liste
			if(nb > idMax){
				idMax = nb;
			}
		};		
		
		// le prochain ID à insérer correspondra à l'idMax + 1
		int prochainId = idMax + 1;
		String prochainIdString = "TMP" + prochainId; // on ajoute le préfixe "TMP"
		connection.close();
		return prochainIdString;
	}

	// met à jour l'aéroport en paramètre
	// renvoie "vrai" si la mise à jour s'est bien passée
	public boolean updateAeroport(Aeroport a) throws SQLException {
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource,user,password);
		// On vérifie que l'aéroport n'est pas déjà utilisé
		if(isAirportUsed(a.getCodeAeroport())){ // si c'est le cas, on empêche sa mise à jour
			return false;
		}
		// requête SQL pour mettre à jour l'aéroport :
		String sql = "UPDATE destination SET ville=?, pays=? WHERE codeaeroport=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, a.getVille());
		stmt.setString(2, a.getPays());
		stmt.setString(3, a.getCodeAeroport());
		int result = stmt.executeUpdate(); // retourne le nb d'enregistrements impactés
		connection.close();
		if(result == 1){ // la mise à jour s'est bien passée
			return true;
		}
		return false; 
	}

	// supprime l'aéroport dont le code est passé en paramètre
	// renvoie vrai si la suppression s'est bien passée
	public boolean deleteAeroport(String code) throws SQLException {
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource,user,password);
		// On vérifie que l'aéroport n'est pas déjà utilisé
		if(isAirportUsed(code)){ // si c'est le cas, on empêche sa suppression
			return false;
		}
		// requête SQL pour supprimer l'aéroport :
		String sql = "DELETE FROM destination WHERE codeaeroport=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		// on valorise le paramètre
		stmt.setString(1, code);
		int result = stmt.executeUpdate(); // retourne le nb d'enregistrements impactés
		connection.close();
		if(result == 1){ // la suppression s'est bien passée
			return true;
		}		
		return false; 
	}

	// renvoie vrai si un aéroport est utilisé au moins 1 fois (dans les vols programmés ou en attente)
	public boolean isAirportUsed(String codeAeroport) throws SQLException{
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on cherche l'aéroport dans les 2 tables
		String sql1 = "SELECT numvol FROM vol V INNER JOIN destination D ON V.lieudep = D.ville WHERE D.codeaeroport = ?";
		String sql2 = "SELECT numvol FROM vol_tmp V INNER JOIN destination D ON V.lieudep = D.ville WHERE D.codeaeroport = ?";
		PreparedStatement stmt1 = connection.prepareStatement(sql1);
		PreparedStatement stmt2 = connection.prepareStatement(sql2);
		// on valorise le paramètre
		stmt1.setString(1, codeAeroport);
		stmt2.setString(1, codeAeroport);
		// On exécute les 2 requêtes :
		ResultSet result1 = stmt1.executeQuery();
		ResultSet result2 = stmt2.executeQuery();
		// si l'aéroport est utilisé, on renvoie vrai.
		if (result1.next()) {
			connection.close();
			return true;
		}else if (result2.next()){
			connection.close();
			return true;
		}else{
			connection.close();
			return false;
		}
	}
	
	// renvoie l'objet Vol correspondant à l'id en paramètre, pour un vol "en attente"
	public Vol getVolEnAttenteById(String id) throws SQLException{
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on crée et exécute une requête préparée
		String sql = "SELECT * FROM vol_tmp WHERE numvol = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		// on valorise le paramètre
		stmt.setString(1, id);
		ResultSet result = stmt.executeQuery();
		// On récupère le résultat de la requête
		result.next();
		String villeDepart = result.getString("lieudep");
		// On récupère l'objet Aeroport correspondant à la ville de départ :
		Aeroport aeroportDepart = getAeroportByVille(villeDepart);
		String villeArrivee = result.getString("lieuarriv");
		// On récupère l'objet Aeroport correspondant à la ville d'arrivée :
		Aeroport aeroportArrivee = getAeroportByVille(villeArrivee);
		// On récupère les dates sous forme de timestamp pour avoir les minutes et secondes
		// (on formatera à l'affichage)
		Date dateHeureDepart = result.getTimestamp("dateheuredep");
		Date dateHeureArrivee = result.getTimestamp("dateheurearrivee");
		// on calcule la durée du vol
		int duree = Vol.getDureeVol(dateHeureDepart, dateHeureArrivee);
		float tarif = result.getFloat("tarif");
		String codePilote = result.getString("pilote");
		String codeCopilote = result.getString("copilote");
		String codeHotesseSt1 = result.getString("hotesse_steward1");
		String codeHotesseSt2 = result.getString("hotesse_steward2");
		String codeHotesseSt3 =	result.getString("hotesse_steward3");			
		
		connection.close();
		// on crée un objet Vol avec les éléments récupérés :
		return new Vol(id, aeroportDepart, aeroportArrivee, dateHeureDepart, dateHeureArrivee, duree, tarif, codePilote, codeCopilote, codeHotesseSt1, codeHotesseSt2, codeHotesseSt3);
	}
	
	// renvoie l'objet Vol correspondant à l'id en paramètre, pour un vol "programmé"
	public Vol getVolProgrammeById(String id) throws SQLException{
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on crée et exécute une requête préparée
		String sql = "SELECT * FROM vol V INNER JOIN travailler T ON V.numvol = T.vol WHERE V.numvol = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		// on valorise le paramètre
		stmt.setString(1, id);
		ResultSet result = stmt.executeQuery();
		// On récupère le résultat de la requête
		result.next();
		String villeDepart = result.getString("lieudep");
		// On récupère l'objet Aeroport correspondant à la ville de départ :
		Aeroport aeroportDepart = getAeroportByVille(villeDepart);
		String villeArrivee = result.getString("lieuarriv");
		// On récupère l'objet Aeroport correspondant à la ville d'arrivée :
		Aeroport aeroportArrivee = getAeroportByVille(villeArrivee);
		// On récupère les dates sous forme de timestamp pour avoir les minutes et secondes
		// (on formatera à l'affichage)
		Date dateHeureDepart = result.getTimestamp("dateheuredep");
		Date dateHeureArrivee = result.getTimestamp("dateheurearrivee");
		// on calcule la durée du vol
		int duree = Vol.getDureeVol(dateHeureDepart, dateHeureArrivee);
		float tarif = result.getFloat("tarif");
		String codePilote = result.getString("pilote");
		String codeCopilote = result.getString("copilote");
		String codeHotesseSt1 = result.getString("hotesse_steward1");
		String codeHotesseSt2 = result.getString("hotesse_steward2");
		String codeHotesseSt3 =	result.getString("hotesse_steward3");			
		
		connection.close();
		// on crée un objet Vol avec les éléments récupérés :
		return new Vol(id, aeroportDepart, aeroportArrivee, dateHeureDepart, dateHeureArrivee, duree, tarif, codePilote, codeCopilote, codeHotesseSt1, codeHotesseSt2, codeHotesseSt3);
	}
	
	// supprime le vol "en attente" dont le code est passé en paramètre
	// renvoie vrai si la suppression s'est bien passée
	public boolean deleteVolEnAttente(String code) throws SQLException {
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource,user,password);
		// requête SQL pour supprimer le vol "en attente" :
		String sql = "DELETE FROM vol_tmp WHERE numvol=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		// on valorise le paramètre
		stmt.setString(1, code);
		int result = stmt.executeUpdate(); // retourne le nb d'enregistrements impactés
		connection.close();
		if(result == 1){ // la suppression s'est bien passée
			return true;
		}		
		return false; 
	}

	// renvoie "true" si le couple login + mdp est correct, "false" sinon
	public boolean connection(String identifiant, String mdp) throws Exception{
		String chaineSalt = "ABCDEFGHIJKLM";
		String mdpChiffre = null;
		String sql = "SELECT login FROM user WHERE login=? and password=?";

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(chaineSalt.getBytes());	// salt
		byte[] input = digest.digest(mdp.getBytes());
		for (int i = 0; i < 4999; i++) {
			//digest.reset();
			//digest.update(chaineSalt.getBytes());	// salt
			input = digest.digest(input);
		}
		//mdpChiffre = new String(Base64.encodeBase64(input));

		/*
		StringBuffer sb = new StringBuffer();
		for(byte b : input) {
			sb.append(String.format("%02x", b));
		}
		mdpChiffre = sb.toString();
		*/


		System.out.print("mdp : "); // test pour débug
		System.out.println(mdp); // test pour débug
		System.out.print("mdp chiffré : "); // test pour débug
		System.out.println(mdpChiffre); // test pour débug

		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, identifiant);
		stmt.setString(2, mdpChiffre);
		ResultSet result = stmt.executeQuery();
		// si la requête renvoie un résultat, les données saisies sont OK, on renvoie vrai.
		if (result.next()) {
			connection.close();
			return true;
		}
		connection.close();
		return false; // sinon, on renvoie faux, et l'utilisateur ne sera pas connecté
	}
}
