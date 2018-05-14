package metier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class Secretaire extends Personnel {
	
	private String serPath = "C:\\Users\\pierre\\Desktop\\secretaire.ser";

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
		System.out.println("Patient ajout� : " + pin.toString());
	}
	
	public void partirEnPause() throws IOException {
		FileOutputStream fos = new FileOutputStream(serPath);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(Hopital.getInstance().getListeAttente());
		oos.close();
		fos.close();
		System.out.println("Liste d'attente sauvegardee");
		Hopital.getInstance().viderListeAttente();
		System.out.println("Secretaire parti(e) en pause");
	}
	
	public void revenirDePause() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(serPath);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		@SuppressWarnings("unchecked")
		LinkedList<Patient> list = (LinkedList<Patient>) ois.readObject();
		ois.close();
		fis.close();
		System.out.println("Liste d'attente récupérée");
		Hopital.getInstance().setListeAttente(list);
		System.out.println("Secrétaire revenu(e) de pause");
		
	}

}
