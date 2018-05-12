package metier;

import java.sql.Date;

public class Visite {
	
	
	private String idPatient;
	private String nomMedecin;
	private int cout = 20;
	private Date dateVisite;
	
	public Visite(String idP, String nomM, Date datev) {
		this.idPatient = idP;
		this.nomMedecin = nomM;
		this.dateVisite = datev;
	}

	@Override
	public String toString() {
		return "Visite [idPatient=" + idPatient + ", nomMedecin=" + nomMedecin + ", cout=" + cout + ", dateVisite="
				+ dateVisite + "]";
	}

	public String getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}

	public String getNomMedecin() {
		return nomMedecin;
	}

	public void setNomMedecin(String nomMedecin) {
		this.nomMedecin = nomMedecin;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	public Date getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(Date dateVisite) {
		this.dateVisite = dateVisite;
	}
	
	
}
