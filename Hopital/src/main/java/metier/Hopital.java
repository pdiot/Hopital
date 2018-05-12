package metier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import dao.DAOVisiteJDBC;

public class Hopital {
	private LinkedList<Patient> listeAttente;
	private static Hopital _instance = null;
	protected Hopital() {
		listeAttente = new LinkedList<Patient>();
	}
	
	public static Hopital getInstance() {
		if (_instance == null) {
			_instance = new Hopital();
		}
		return _instance;
	}
	
	public LinkedList<Patient> getListeAttente() {
		return this.listeAttente;
	}
	
	public void viderListeAttente() {
		this.listeAttente = new LinkedList<Patient>();
	}
	
	public void setListeAttente(LinkedList<Patient> list) {
		this.listeAttente = list;
	}
	
	public Patient getPatientATraiter() {		
		Patient tmp = listeAttente.getFirst();
		listeAttente.removeFirst();
		return tmp;
	}
	
	public void ajouterPatientAttente(Patient pin) {
		listeAttente.add(pin);
	}
	
	
	
}
