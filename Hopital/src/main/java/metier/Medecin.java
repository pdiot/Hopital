package metier;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOVisiteJDBC;

public class Medecin extends Personnel {
	
	private Patient patientEnCours = null;
	private ArrayList<Visite> listeVisites;

	public Medecin(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
	}
	
	public Medecin(String nom, String pwd) {
		super(nom, pwd);
		// TODO Auto-generated constructor stub
	}
	
	public void accueillirPatient() {
		if (Hopital.getInstance().restePatientEnAttente()) {
			this.patientEnCours = Hopital.getInstance().getPatientATraiter();
			System.out.println("Patient accueuilli : " + patientEnCours.toString());
		} else {
			System.out.println("Aucun patient Ã  traiter");
		}
	}

	public Patient libererSalle() throws SQLException {
		terminerVisite();
		return Hopital.getInstance().getPatientATraiter();
	}
	
	@SuppressWarnings("deprecation")
	public void terminerVisite() throws SQLException {
		System.out.println("Visite terminée");
		ajouterVisite(new Visite(this.patientEnCours.getId(), nom, new Date(118, 5, 11)));
		this.patientEnCours = null;
	}
	
	public void ajouterVisite(Visite visite) throws SQLException {
		listeVisites.add(visite);
		if (listeVisites.size() >= 10) {
			enregisterVisites();
		}
	}

	public void enregisterVisites() throws SQLException {
		DAOVisiteJDBC dao = new DAOVisiteJDBC();
		for (Visite vis : listeVisites) {
			dao.insert(vis);
		}
		listeVisites = new ArrayList<Visite>();
		System.out.println("Visites enregistrées en base");
	}
	
	public boolean peutPartir() {
		if (this.patientEnCours == null) {
			return true;
		} 
		else {
			return false;
		}
	}
	
	public void partirEnPause() throws SQLException {
		enregisterVisites();
		System.out.println("Le médecin est parti. Bonne soirée !");
	}
	

}
