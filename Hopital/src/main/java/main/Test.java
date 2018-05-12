package main;
import java.io.IOException;
import java.util.LinkedList;

import metier.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Secretaire sec = new Secretaire("Sam");
		
		Hopital.getInstance().ajouterPatientAttente(new Patient ("123456", "Arthas", "Menethil"));
		Hopital.getInstance().ajouterPatientAttente(new Patient ("1234567", "Jaina", "Proudmoore"));
		Hopital.getInstance().ajouterPatientAttente(new Patient ("12345678", "Anduin", "Wrynn"));
		Hopital.getInstance().ajouterPatientAttente(new Patient ("123456789", "Sylvanas", "Windrunner"));

		System.out.println("Liste d'attente : ");
		System.out.println("------------------");
		LinkedList<Patient> list = Hopital.getInstance().getListeAttente();
		for (Patient plist : list) {
			System.out.println(plist.toString());
		}
		
		try {
			sec.partirEnPause();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Liste d'attente : ");
		System.out.println("------------------");
		list = Hopital.getInstance().getListeAttente();
		for (Patient plist : list) {
			System.out.println(plist.toString());
		}
		
		try {
			sec.revenirDePause();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Liste d'attente : ");
		System.out.println("------------------");
		list = Hopital.getInstance().getListeAttente();
		for (Patient plist : list) {
			System.out.println(plist.toString());
		}
		
	}

}
