package test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Aeroport;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import dao.MysqlDao;

@RunWith(JUnit4.class)
//TODO : à décommenter si nécessaire
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMysqlDao {
	
	@Test
	public void getAllAeroports() throws SQLException{
		MysqlDao dao = new MysqlDao();
		List<Aeroport>aeroports = dao.getAllAeroports();
		// on peut tester que le nombre d'aéroports retournés correspond bien
		// au nombre d'aéroports en base (à réajuster au fur et à mesure) :
		Assert.assertEquals(40, aeroports.size());
		// on teste que le premier élément de la liste est bien une instance d'Aeroport
		Assert.assertTrue(aeroports.get(0) instanceof Aeroport);
	}
}
