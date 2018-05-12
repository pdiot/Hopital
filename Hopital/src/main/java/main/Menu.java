package main;
import metier.*;

import java.sql.SQLException;
import java.util.*;

import dao.*;

public class Menu {
	
	static Personnel p1;

	public static void main(String[] args) throws SQLException {
		// Se connecter en tant que secrétaire ou medecin
		while (true)
		{

			System.out.println("Connection");

			System.out.println("Votre login :");
			Scanner in = new Scanner(System.in);
			String login = in.nextLine();

			System.out.println("Votre mot de passe :");
			Scanner in2 = new Scanner(System.in);
			String mdp = in2.nextLine();

			//Afficher le menu selon le role
			
			DAOPersonnelJDBC daoP= new DAOPersonnelJDBC ();
			p1=daoP.selectByLogin(login);
			
			System.out.println(p1.getNom());
			
			if (p1.getPassword().equals(mdp))
			{
				
				if (p1 instanceof Secretaire) 
				{
					afficheMenuSecretaire(); 
				}
				else
				{
					afficheMenuMedecin();
				}
			}
		
		}
		

	}
	
	public static void afficheMenuSecretaire() throws SQLException
	{
		System.out.println("A. Recevoir nouveau patient");
		System.out.println("B. Afficher la liste d'attente");
		System.out.println("C. Afficher la liste des visites d'un patient");
		System.out.println("D. Partir en pause");
		System.out.println("E. Revenir de pause");
		System.out.println("F. Deconnection");
		
		Scanner in = new Scanner(System.in);
		String choixMenu = in.nextLine();
		
		switch (choixMenu.toLowerCase()) {
		case "a":
			System.out.println("numero de sécurité sociale :");
			String numeroSecu = in.nextLine();
			Patient p = Patient.getPatient(Integer.parseInt(numeroSecu));
			if (p!=null)
			{
				//Ajouter le patient à la liste
				Hopital.getInstance().ajouterPatientAttente(p);
			}
			else
			{
				//Creer nouveau patient
				System.out.println("nom du patient :");
				String nom = in.nextLine();
				System.out.println("prenom du patient :");
				String prenom = in.nextLine();
				Patient newPatient= new Patient(numeroSecu, nom, prenom);
				
				//Ajouter le patient à la BDD
				DAOPatientJDBC daoP = new DAOPatientJDBC();
				daoP.insert(newPatient);
				
				//Ajouter le patient dans la liste d'attente
				Hopital.getInstance().ajouterPatientAttente(newPatient);
			}
			break;
		case "b":
			//Liste d'attente de l'hopital
			LinkedList<Patient> l1 = p1.getListeAttente();
			for (Patient patient : l1 ) {
				System.out.println(patient.toString());
			}
			break;
		case "c":
			//Affiche liste des visites d'un patient
			DAOVisiteJDBC daoV = new DAOVisiteJDBC();
			ArrayList<Visite> listePatient=daoV.selectAll();
			for (Visite visite : listePatient) {
				System.out.println(visite.toString());
			}
			break;
		case "d":
			//TODO Partir en pause
			break;
		case "e":
			//TODO Revenir en pause
			break;
		case "f":
			//TODO Deconnection
		default:
			break;
			
		}
	}
	
	public static void afficheMenuMedecin()
	{
		while(true)
		{
			System.out.println("A. Afficher la liste d'attente");
			System.out.println("B. Liberer salle");
			System.out.println("C. Partir en pause");
			//Appeler peutPartir(); qui renvoi un boolean autorisant ou non la pause(patient en cours) 
			System.out.println("D. Revenir de pause");
			System.out.println("E. Deconnection");

			Scanner in = new Scanner(System.in);
			String choixMenu = in.nextLine();

			switch (choixMenu.toLowerCase()) {
			case "a":
				//Liste d'attente de l'hopital
				LinkedList<Patient> l1 = p1.getListeAttente();
				for (Patient patient : l1 ) {
					System.out.println(patient.toString());
				}
				break;
			case "b":
				//TODO Liberer salle
				break;
			case "c":
				//TODO Partir en pause
				break;
			case "d":
				//TODO Revenir en pause
				break;
			case "e":	
				//TODO Deconnecter
				break;
			default:
				break;
			}	
			
		}
	}

}
