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
			p1 = daoP.selectByLogin(login);
			if(p1 != null) {
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
		

	}
	
	public static void afficheMenuSecretaire() throws SQLException
	{
		boolean isConnected = true;
		while(isConnected)
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
				System.out.println("numero de s&eacutecurit&eacute sociale :");
				String numeroSecu = in.nextLine();
				DAOPatientJDBC daoP = new DAOPatientJDBC();
				Patient p = daoP.selectById(numeroSecu);
				System.out.println("Nom du patient : "+p.getNom());
				if (p != null)
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
				System.out.println("Numero de secu :");
				String numSec = in.nextLine();
				
				DAOVisiteJDBC daoV = new DAOVisiteJDBC();
				ArrayList<Visite> listePatient=daoV.selectByPatient(Integer.parseInt(numSec));
				for (Visite visite : listePatient) {
					System.out.println(visite.toString());
				}
				break;
			case "d":
			try {
				p2.partirEnPause();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "e":
			try {
				p2.revenirDePause();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			case "f":
				isConnected = false;
				System.out.println("\n"+p1.getNom()+" au revoir !\n");
			default:
				break;

			}
		}
	}
	
	public static void afficheMenuMedecin()
	{
		Medecin p2 = (Medecin) p1;
		boolean isConnected = true;
		while(isConnected)
		{
			System.out.println("A. Afficher la liste d'attente");
			System.out.println("B. Accueillir patient");
			System.out.println("C. Liberer salle");
			System.out.println("D. Partir en pause");
			//Appeler peutPartir(); qui renvoi un boolean autorisant ou non la pause(patient en cours) 
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
				p2.accueillirPatient();
				break;
			case "c":
				//TODO Liberer salle
				try {
					p2.terminerVisite();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "d":
				//TODO Partir en pause
				if (p2.peutPartir()) {
					try {
						p2.partirEnPause();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					System.out.println("Le médecin ne peut pas partir en pause, il est encore en train de traiter un patient !");
				}
				break;
			case "e":	
				isConnected = false;
				System.out.println("\n"+p1.getNom()+" au revoir !\n");
				try {
					p2.libererSalle();
					if(p2.getListeVisites().size() > 0)
					{
						p2.enregisterVisites();
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}	
			
		}
	}

}
