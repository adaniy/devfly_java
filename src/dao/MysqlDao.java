package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Vol;

public class MysqlDao {
	private String datasource = "jdbc:mysql://localhost:3306/devfly";
	private String user = "greta";
	private String password = "gretatest";

	public List<Vol> getAllVols() throws SQLException {
		List<Vol> vols = new ArrayList<>();
		Connection connection = DriverManager.getConnection(datasource, user,
				password);
		String sql = "SELECT * FROM vol"; // à compléter
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			// à compléter
		}
		connection.close();
		return vols;
	}
}
