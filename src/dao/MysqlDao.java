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
	public List<Vol> getAllVolsProgrammes() throws SQLException {
		List<Vol> vols = new ArrayList<>();
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on crée et exécute une requête préparée pour récupérer les informations
		// sur le vol et ses employés
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
			
			// pour calculer la durée du vol, on fait la différence entre les 2 timestamp
			long departMillisecondes = dateHeureDepart.getTime();
			long arriveeMillisecondes = dateHeureArrivee.getTime();
			long dureeMillisecondes = arriveeMillisecondes - departMillisecondes;
			
			int duree = (int) (dureeMillisecondes / 60000); // en minutes
			
			float tarif = result1.getFloat("tarif");
			String codePilote = result1.getString("pilote");
			String codeCopilote = result1.getString("copilote");
			String codeHotesseSt1 = result1.getString("hotesse_steward1");
			String codeHotesseSt2 = result1.getString("hotesse_steward2");
			String codeHotesseSt3 = result1.getString("hotesse_steward3");
			
			// on récupère les éléments sur l'aéroport de départ
			String sql2 = "SELECT codeaeroport, pays FROM destination WHERE ville = ?";
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			// on valorise le paramètre
			stmt2.setString(1, villeDepart);
			ResultSet result2 = stmt2.executeQuery();
			// on initialise les variables en dehors de la boucle
			String codeDepart = null;
			String paysDepart = null;
			while (result2.next()) {
				codeDepart = result2.getString("codeaeroport");
				paysDepart = result2.getString("pays");
			}
			
			// on récupère les éléments sur l'aéroport de destination (même requête)
			PreparedStatement stmt3 = connection.prepareStatement(sql2);
			// on valorise le paramètre
			stmt3.setString(1, villeArrivee);
			ResultSet result3 = stmt3.executeQuery();
			// on initialise les variables en dehors de la boucle
			String codeArrivee = null;
			String paysArrivee = null;
			while (result3.next()) {
				codeArrivee = result3.getString("codeaeroport");
				paysArrivee = result3.getString("pays");
			}
			
			// on crée les objet "Aeroport" de départ et d'arrivée avec les informations récupérées
			Aeroport aeroportDepart = new Aeroport(codeDepart, villeDepart, paysDepart);
			Aeroport aeroportArrivee = new Aeroport(codeArrivee, villeArrivee, paysArrivee);
			
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
			// on crée et exécute une requête préparée pour récupérer les informations
			// sur le vol
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
				
				// pour calculer la durée du vol, on fait la différence entre les 2 timestamp
				long departMillisecondes = dateHeureDepart.getTime();
				long arriveeMillisecondes = dateHeureArrivee.getTime();
				long dureeMillisecondes = arriveeMillisecondes - departMillisecondes;
				
				int duree = (int) (dureeMillisecondes / 60000); // en minutes
				
				float tarif = result1.getFloat("tarif");
				
				String codePilote = result1.getString("pilote");
				String codeCopilote = result1.getString("copilote");
				String codeHotesseSt1 = result1.getString("hotesse_steward1");
				String codeHotesseSt2 = result1.getString("hotesse_steward2");
				String codeHotesseSt3 = result1.getString("hotesse_steward3");
				
				// on récupère les éléments sur l'aéroport de départ
				String sql2 = "SELECT codeaeroport, pays FROM destination WHERE ville = ?";
				PreparedStatement stmt2 = connection.prepareStatement(sql2);
				// on valorise le paramètre
				stmt2.setString(1, villeDepart);
				ResultSet result2 = stmt2.executeQuery();
				// on initialise les variables en dehors de la boucle
				String codeDepart = null;
				String paysDepart = null;
				while (result2.next()) {
					codeDepart = result2.getString("codeaeroport");
					paysDepart = result2.getString("pays");
				}
				
				// on récupère les éléments sur l'aéroport de destination (même requête)
				PreparedStatement stmt3 = connection.prepareStatement(sql2);
				// on valorise le paramètre
				stmt3.setString(1, villeArrivee);
				ResultSet result3 = stmt3.executeQuery();
				// on initialise les variables en dehors de la boucle
				String codeArrivee = null;
				String paysArrivee = null;
				while (result3.next()) {
					codeArrivee = result3.getString("codeaeroport");
					paysArrivee = result3.getString("pays");
				}
				
				// on crée les objet "Aeroport" de départ et d'arrivée avec les informations récupérées
				Aeroport aeroportDepart = new Aeroport(codeDepart, villeDepart, paysDepart);
				Aeroport aeroportArrivee = new Aeroport(codeArrivee, villeArrivee, paysArrivee);
				
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
	
	// TODO : à optimiser ?
	// ajoute un nouvel aéroport en base
	public int addNewAeroport(Aeroport a) throws SQLException{
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on regarde si le code aéroport existe déjà en base, avec une requête préparée
		String sql1 = "SELECT * from destination WHERE codeaeroport = ?";
		PreparedStatement stmt1 = connection.prepareStatement(sql1);
		// on valorise le paramètre
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
	
	// ajoute un nouveau vol en base (table vol_tmp)
	public int addNewVol(Vol v) throws SQLException{
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user, password);
		// on crée et exécute une requête préparée pour insérer le vol
		// TODO : voir cmt rentrer uniquement les 6 premières valeurs (fait planter le test !)
		String sql = "INSERT INTO vol_tmp VALUES(?, ?, ?, ?, ?, ?, '', '', '', '', '')";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		// On récupère les numvol de la table vol_tmp.
		String sql2 = "SELECT numvol FROM vol_tmp";
		PreparedStatement stmt2 = connection.prepareStatement(sql2);
		ResultSet result2 = stmt2.executeQuery();
		// On va chercher l'id max de la table. On initialise idMax à zéro.
		int idMax = 0;
		// On parcourt les résultats de la requête.
		while (result2.next()) {
			// On ne prend que la fin de la chaîne. Ex : pour le vol "TMP12", on veut récupérer "12".
			// On récupère donc la chaine à partir du 4ème caractère (on enlève "TMP")
			String numvol = result2.getString("numvol").substring(3);

			// On transforme la chaîne récupérée en int
			int nb = Integer.parseInt(numvol);
			
			// On récupère la plus grande valeur de la liste
			if(nb > idMax){
				idMax = nb;
			}
		};		
		
		// le prochain ID à insérer correspondra à l'idMax + 1
		int prochainId = idMax + 1;
		stmt.setString(1, "TMP" + prochainId); // on ajoute le préfixe "TMP"
		
		stmt.setString(2, v.getAeroportDepart().getVille());
		stmt.setString(3, v.getAeroportArrivee().getVille());
		// on transforme la date util en date SQL. Pour cela, on utilise le timestamp des dates.
		stmt.setDate(4, new java.sql.Date(v.getDateHeureDepart().getTime()));
		stmt.setDate(5, new java.sql.Date(v.getDateHeureArrivee().getTime()));
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
		// on crée un objet Aeroport avec les éléments récupérés :
		return new Aeroport(code, ville, pays);
	}
}
