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
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMysqlDao {
	
	@Test
	public void getAllAeroports() throws SQLException{
		MysqlDao dao = new MysqlDao();
		List<Aeroport>aeroports = dao.getAllAeroports();
		//Assert.assertEquals(4, aeroports.size());
		Assert.assertTrue(aeroports.get(0) instanceof Aeroport);
	}
}
