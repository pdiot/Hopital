package metier;

import java.io.Serializable;

public class Patient implements Serializable{
	private String id;
	private String nom, prenom, tel, adresse;
	
	public Patient(String id, String nom, String prenom, String tel, String adresse)
	{
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.tel=tel;
		this.adresse=adresse;
			
	}

	public Patient(String id, String nom, String prenom)
	{
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;	
		this.tel="";
		this.adresse="";
	}
	
	public static Patient getPatient(int numeroSecu)
	{
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", adresse=" + adresse
				+ "]";
	}
	
	
	
}
