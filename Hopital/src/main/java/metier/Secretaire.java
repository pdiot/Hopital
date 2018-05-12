package metier;

public class Secretaire extends Personnel {

	public Secretaire(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
	}
	
	public Secretaire(String nom, String pwd) {
		super(nom, pwd);
		// TODO Auto-generated constructor stub
	}
	
	public void ajouterPatient(Patient pin) {
		Hopital.getInstance().ajouterPatientAttente(pin);
	}
	
	
	// partir en pause
	/* Sérialiser la liste d'attente
	 * Mettre à 0 Hopital.listeAttente
	 * 
	 */
	

	/*
	 * revenir de pause
	 * Dé sérialiser la liste
	 * Re remplir Hopital.listeAttente
	 */
}
