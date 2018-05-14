package metier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

public class Personnel {
	
	String nom;
	String password;
	
	public Personnel (String nom) {
		this.nom = nom;
	}
	
	public Personnel (String nom, String pwd) {
		this.nom = nom;
		this.password = pwd;
	}
	
	public static boolean connection(String login, String mdp) {
		return true; 
		}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Personnel [nom=" + nom + ", password=" + password + ", " + this.getClass() + "]";
	}
	
	public LinkedList<Patient> getListeAttente() {
		return Hopital.getInstance().getListeAttente();
	}
	

}