package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aeroport;
import model.Vol;

public class MysqlDao {
	private String datasource = "jdbc:mysql://localhost:3306/DEVFLY";
	private String user = "greta";
	private String password = "gretatest";

	// retourne les vols sous forme d'une liste
	public List<Vol> getAllVols() throws SQLException {
		List<Vol> vols = new ArrayList<>();
		// on se connecte à la BDD
		Connection connection = DriverManager.getConnection(datasource, user,
				password);
		// on crée et exécute une requête préparée
		String sql = "SELECT * FROM vol"; // à compléter
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			// à compléter
		}
		connection.close();
		return vols;
	}

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
}
