package model;

import java.util.Date;

public class Vol {
	private String id; // ex : DF1
	private Aeroport aeroportDepart;
	private Aeroport aeroportArrivee;
	private Date dateHeureDepart;
	private int duree; // en minutes
	private float tarif;
	private String codePilote;
	private String codeCopilote;
	private String codeHotesseSt1;
	private String codeHotesseSt2;
	private String codeHotesseSt3;
	
	// un constructeur complet
	public Vol(String id, Aeroport aeroportDepart, Aeroport aeroportArrivee,
			Date dateHeureDepart, int duree, float tarif, String codePilote,
			String codeCopilote, String codeHotesseSt1, String codeHotesseSt2,
			String codeHotesseSt3) {
		this.id = id;
		this.aeroportDepart = aeroportDepart;
		this.aeroportArrivee = aeroportArrivee;
		this.dateHeureDepart = dateHeureDepart;
		this.duree = duree;
		this.tarif = tarif;
		this.codePilote = codePilote;
		this.codeCopilote = codeCopilote;
		this.codeHotesseSt1 = codeHotesseSt1;
		this.codeHotesseSt2 = codeHotesseSt2;
		this.codeHotesseSt3 = codeHotesseSt3;
	}

	// un constructeur sans les codes "employés"
	public Vol(String id, Aeroport aeroportDepart, Aeroport aeroportArrivee,
			Date dateHeureDepart, int duree, float tarif) {
		this.id = id;
		this.aeroportDepart = aeroportDepart;
		this.aeroportArrivee = aeroportArrivee;
		this.dateHeureDepart = dateHeureDepart;
		this.duree = duree;
		this.tarif = tarif;
	}
	
	// Remarque : pas de setter sur l'id du vol qui ne doit pas être modifié.

	public String getId() {
		return id;
	}

	public Aeroport getAeroportDepart() {
		return aeroportDepart;
	}

	public void setAeroportDepart(Aeroport aeroportDepart) {
		this.aeroportDepart = aeroportDepart;
	}

	public Aeroport getAeroportArrivee() {
		return aeroportArrivee;
	}

	public void setAeroportArrivee(Aeroport aeroportArrivee) {
		this.aeroportArrivee = aeroportArrivee;
	}

	public Date getDateHeureDepart() {
		return dateHeureDepart;
	}

	public void setDateHeureDepart(Date dateHeureDepart) {
		this.dateHeureDepart = dateHeureDepart;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public float getTarif() {
		return tarif;
	}

	public void setTarif(int tarif) {
		this.tarif = tarif;
	}

	public String getCodePilote() {
		return codePilote;
	}

	public void setCodePilote(String codePilote) {
		this.codePilote = codePilote;
	}

	public String getCodeCopilote() {
		return codeCopilote;
	}

	public void setCodeCopilote(String codeCopilote) {
		this.codeCopilote = codeCopilote;
	}

	public String getCodeHotesseSt1() {
		return codeHotesseSt1;
	}

	public void setCodeHotesseSt1(String codeHotesseSt1) {
		this.codeHotesseSt1 = codeHotesseSt1;
	}

	public String getCodeHotesseSt2() {
		return codeHotesseSt2;
	}

	public void setCodeHotesseSt2(String codeHotesseSt2) {
		this.codeHotesseSt2 = codeHotesseSt2;
	}

	public String getCodeHotesseSt3() {
		return codeHotesseSt3;
	}

	public void setCodeHotesseSt3(String codeHotesseSt3) {
		this.codeHotesseSt3 = codeHotesseSt3;
	}
	
	// TODO : à compléter si utilisé
//	// retourne un tableau d'objets avec ...
//	public Object[] toArray(){
//		return new Object[]{id,aeroportDepart ... };
//	}
}
