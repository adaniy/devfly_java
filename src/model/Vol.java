package model;

import java.util.Date;

public class Vol {
	private String id; // ex : DF1
	private Aeroport aeroportDepart;
	private Aeroport aeroportArrivee;
	private Date dateHeureDepart;
	private Date dateHeureArrivee;
	private int duree; // calculée en minutes
	private float tarif;
	private String codePilote;
	private String codeCopilote;
	private String codeHotesseSt1;
	private String codeHotesseSt2;
	private String codeHotesseSt3;

	// un constructeur complet
	public Vol(String id, Aeroport aeroportDepart, Aeroport aeroportArrivee,
			Date dateHeureDepart, Date dateHeureArrivee, int duree, float tarif, String codePilote,
			String codeCopilote, String codeHotesseSt1, String codeHotesseSt2,
			String codeHotesseSt3) {
		this.id = id;
		this.aeroportDepart = aeroportDepart;
		this.aeroportArrivee = aeroportArrivee;
		this.dateHeureDepart = dateHeureDepart;
		this.dateHeureArrivee = dateHeureArrivee;
		this.duree = duree;
		this.tarif = tarif;
		this.codePilote = codePilote;
		this.codeCopilote = codeCopilote;
		this.codeHotesseSt1 = codeHotesseSt1;
		this.codeHotesseSt2 = codeHotesseSt2;
		this.codeHotesseSt3 = codeHotesseSt3;
	}

	// un constructeur sans les codes "employés" ni l'"id" (pour la création d'un vol "en attente")
	public Vol(Aeroport aeroportDepart, Aeroport aeroportArrivee,
			Date dateHeureDepart, Date dateHeureArrivee, int duree, float tarif) {
		this.aeroportDepart = aeroportDepart;
		this.aeroportArrivee = aeroportArrivee;
		this.dateHeureDepart = dateHeureDepart;
		this.dateHeureArrivee = dateHeureArrivee;
		this.duree = duree;
		this.tarif = tarif;
	}
	
	// un constructeur sans l'"id", pour insérer un nouveau vol dont l'id sera généré automatiquement
	public Vol(Aeroport aeroportDepart, Aeroport aeroportArrivee,
			Date dateHeureDepart, Date dateHeureArrivee, int duree, float tarif, String codePilote,
			String codeCopilote, String codeHotesseSt1, String codeHotesseSt2,
			String codeHotesseSt3) {
		this.aeroportDepart = aeroportDepart;
		this.aeroportArrivee = aeroportArrivee;
		this.dateHeureDepart = dateHeureDepart;
		this.dateHeureArrivee = dateHeureArrivee;
		this.duree = duree;
		this.tarif = tarif;
		this.codePilote = codePilote;
		this.codeCopilote = codeCopilote;
		this.codeHotesseSt1 = codeHotesseSt1;
		this.codeHotesseSt2 = codeHotesseSt2;
		this.codeHotesseSt3 = codeHotesseSt3;
	}

	// Remarque : pas de setter sur l'id du vol qui ne doit pas être modifié.

	public String getId() {
		return id;
	}

	public Aeroport getAeroportDepart() {
		return aeroportDepart;
	}

	public Aeroport getAeroportArrivee() {
		return aeroportArrivee;
	}

	public Date getDateHeureDepart() {
		return dateHeureDepart;
	}

	public Date getDateHeureArrivee() {
		return dateHeureArrivee;
	}

	public int getDuree() {
		return duree;
	}

	public float getTarif() {
		return tarif;
	}

	public String getCodePilote() {
		return codePilote;
	}

	public String getCodeCopilote() {
		return codeCopilote;
	}

	public String getCodeHotesseSt1() {
		return codeHotesseSt1;
	}

	public String getCodeHotesseSt2() {
		return codeHotesseSt2;
	}

	public String getCodeHotesseSt3() {
		return codeHotesseSt3;
	}
}
