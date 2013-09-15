package test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Aeroport;
import model.Vol;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import dao.MysqlDao;

@RunWith(JUnit4.class)
//TODO : à décommenter si nécessaire -> permet l'exécution des tests dans l'ordre alphabétique
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMysqlDao {

	// Décommenter le mot @Test pour lancer le(s) test(s) correspondant(s)

	//@Test
	public void getAllAeroports() throws SQLException{
		MysqlDao dao = new MysqlDao();
		List<Aeroport>aeroports = dao.getAllAeroports();
		// on peut tester que le nombre d'aéroports retournés correspond bien
		// au nombre d'aéroports en base (à réajuster au fur et à mesure) :
		Assert.assertEquals(40, aeroports.size());
		// on teste que le premier élément de la liste est bien une instance d'Aeroport
		Assert.assertTrue(aeroports.get(0) instanceof Aeroport);
	}

	//@Test
	public void addNewAeroport() throws SQLException{
		MysqlDao dao = new MysqlDao();
		// à remodifier à chaque test :
		Aeroport aeroportTest = new Aeroport("SXB", "Strasbourg", "France");
		boolean result = dao.addNewAeroport(aeroportTest);
		Assert.assertTrue(result);
		// à réajuster à chaque test :
		Aeroport a = dao.getAeroportByCode("SXB");
		Assert.assertEquals(aeroportTest.getCodeAeroport(), a.getCodeAeroport());
	}

	//@Test
	public void getAllVolsProgrammes() throws SQLException{
		MysqlDao dao = new MysqlDao();
		List<Vol>vols = dao.getAllVolsProgrammes();
		// on peut tester que le nombre de vols retournés correspond bien
		// au nombre de vols en base (à réajuster au fur et à mesure) :
		Assert.assertEquals(9, vols.size());
		// on teste que le premier élément de la liste est bien une instance de Vol
		Assert.assertTrue(vols.get(0) instanceof Vol);
	}

	//@Test
	public void getAllVolsEnAttente() throws SQLException{
		MysqlDao dao = new MysqlDao();
		List<Vol>vols = dao.getAllVolsEnAttente();
		// on peut tester que le nombre de vols retournés correspond bien
		// au nombre de vols en base (à réajuster au fur et à mesure) :
		Assert.assertEquals(4, vols.size());
		// on teste que le premier élément de la liste est bien une instance de Vol
		Assert.assertTrue(vols.get(0) instanceof Vol);
	}

	//@Test
	public void addNewVol() throws SQLException{
		MysqlDao dao = new MysqlDao();
		
		// On transforme les chaînes de caractères en dates selon le motif.
		// On déclare les dates à l'extérieur du try / catch
		Date dateDepart = null;
		Date dateArrivee = null;
		
		try {
			dateDepart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-03-24 02:20:00");
			dateArrivee = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-03-24 18:34:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Aeroport aeroportTest1 = new Aeroport("CMN", "Casablanca", "Maroc");
		Aeroport aeroportTest2 = new Aeroport("HNL", "Honolulu", "États-Unis");
		
		// (On peut le modifier avant de relancer un test) :
		Vol volTest = new Vol(aeroportTest1, aeroportTest2, dateDepart, dateArrivee, 1540, 974F);
		
		int result = dao.addNewVol(volTest); // doit ajouter le vol en base (table vol_tmp)
		Assert.assertEquals(1, result); // une seule ligne doit être impactée
		//Vol v = dao.getVolById(5);
		//Assert.assertEquals(volTest.getId(), v.getId());
	}


	//@Test
	public void getAeroportByVille() throws SQLException{
		MysqlDao dao = new MysqlDao();
		// doit renvoyer un objet Aeroport correspondant à la ville en paramètre
		Aeroport aeroportRecupere = dao.getAeroportByVille("Berne");
		Aeroport aeroportTest = new Aeroport("BRN", "Berne", "Suisse");
		Assert.assertEquals(aeroportTest.getCodeAeroport(), aeroportRecupere.getCodeAeroport());
	}

	//@Test
	public void getAeroportByCode() throws SQLException{
		MysqlDao dao = new MysqlDao();
		// doit renvoyer un objet Aeroport correspondant au code en paramètre
		Aeroport aeroportRecupere = dao.getAeroportByCode("CMN");
		Aeroport aeroportTest = new Aeroport("CMN", "Casablanca", "Maroc");
		Assert.assertEquals(aeroportTest.getCodeAeroport(), aeroportRecupere.getCodeAeroport());
		Assert.assertEquals(aeroportTest.getVille(), aeroportRecupere.getVille());
	}

	//@Test
	public void getDureeVol(){ // doit renvoyer la durée du vol en min
		Date dateDepart = null;
		Date dateArrivee = null;
		
		try {
			dateDepart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-03-24 02:20:00");
			dateArrivee = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-03-24 04:30:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int duree = Vol.getDureeVol(dateDepart, dateArrivee);
		Assert.assertEquals(130, duree);		
	}

	@Test
	public void doesAirportAlreadyExist() throws SQLException{ // doit renvoyer vrai si l'aéroport existe déjà en base
		MysqlDao dao = new MysqlDao();
		boolean test1 = dao.doesAirportAlreadyExist("BRN");
		boolean test2 = dao.doesAirportAlreadyExist("LLL");
		Assert.assertTrue(test1);
		Assert.assertFalse(test2);
	}

	//@Test
	public void getNextId() throws SQLException{ // doit renvoyer le prochain ID à insérer dans la table vol_tmp
		MysqlDao dao = new MysqlDao();
		String nextId = dao.getNextId();
		// à adapter à chaque test
		Assert.assertEquals("TMP7", nextId);
	}

	//@Test
	public void updateAeroport() throws Exception{
		MysqlDao dao = new MysqlDao();
		// (On peut le modifier avant de relancer un test) :
		Aeroport a = new Aeroport("ANI", "Ailleurs", "Tunisie");
		Aeroport b = new Aeroport("BRN", "Berne", "Suisse"); // cet aéroport déjà utilisé n'est plus modifiable
		Aeroport c = new Aeroport("LLL", "Truc", "Pays"); // Cet aéroport n'existe pas et n'est donc pas modifiable
		boolean result1 = dao.updateAeroport(a);
		boolean result2 = dao.updateAeroport(b);
		boolean result3 = dao.updateAeroport(c);
		Aeroport a1 = dao.getAeroportByCode("ANI"); // on récupère l'aéroport modifié
		Assert.assertTrue(result1); // true
		Assert.assertFalse(result2); // false
		Assert.assertFalse(result3); // false
		Assert.assertEquals(a.getVille(), a1.getVille()); // les villes coïncident
	}

	//@Test
	public void deleteAeroport() throws Exception{ // doit supprimer l'aéroport dont le code est en paramètre
		MysqlDao dao = new MysqlDao();
		// À modifier avant de relancer un test :
		boolean result1 = dao.deleteAeroport("ALG"); // cet aéroport est supprimable
		boolean result2 = dao.deleteAeroport("BRN"); // cet aéroport est déjà utilisé
		boolean result3 = dao.deleteAeroport("LLL"); // cet aéroport n'existe pas
		Assert.assertTrue(result1); // renvoie vrai si l'aéroport a été supprimé
		Assert.assertFalse(result2); // non supprimable
		Assert.assertFalse(result3); // non supprimable
	}

	//@Test
	public void isAirportUsed() throws SQLException{ // doit renvoyer vrai si l'aéroport est utilisé
		MysqlDao dao = new MysqlDao();
		// à réajuster à chaque test :
		boolean test1 = dao.isAirportUsed("BRN");
		boolean test2 = dao.isAirportUsed("ORD");
		Assert.assertTrue(test1);
		Assert.assertFalse(test2);
	}
	
	@Test
	public void getVolEnAttenteById() throws SQLException{ // doit renvoyer un objet Vol correspondant à l'id en paramètre
		MysqlDao dao = new MysqlDao();
		Vol volRecupere = dao.getVolEnAttenteById("TMP9");
		
		Date dateDepart = null;
		Date dateArrivee = null;
		try {
			dateDepart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-03-30 02:00:00");
			dateArrivee = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-03-30 15:15:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Aeroport aeroportDepart = dao.getAeroportByVille("Sydney");
		Aeroport aeroportArrivee = dao.getAeroportByVille("Berne");
		// à réajuster à chaque test :
		Vol volTest = new Vol("TMP9", aeroportDepart, aeroportArrivee, dateDepart, dateArrivee, 795, 1480, "P0001", "C0001", "", "", "");
		Assert.assertEquals(volTest.getId(), volRecupere.getId());
		Assert.assertEquals(volTest.getAeroportDepart().getCodeAeroport(), volRecupere.getAeroportDepart().getCodeAeroport());		
		Assert.assertEquals(volTest.getDuree(), volRecupere.getDuree());
	}

	//@Test
	public void connection() throws Exception{ // doit renvoyer vrai si le couple login + password est correct
		MysqlDao dao = new MysqlDao();
		boolean test1 = dao.connection("admin", "admin");
		boolean test2 = dao.connection("admin", "bidule");
		Assert.assertTrue(test1);
		Assert.assertFalse(test2);
		//Assert.assertEquals(true,test1);
	}
}
