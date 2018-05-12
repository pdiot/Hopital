package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import metier.Patient;
import metier.Visite;

public class DAOPatientJDBC implements DAOPatient {
	
private Connection connect() throws SQLException {
		
		Properties pt = new Properties();
		pt.setProperty("user", "root");
		pt.setProperty("password", "");
		pt.setProperty("useSSL", "false");
		pt.setProperty("autoReconnect", "true");
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital?serverTimezone=UTC", pt);
		
	}

	public Patient selectById(String key) throws SQLException {
		Connection conn = this.connect();
		Patient patient = null;
		
		PreparedStatement ps=conn.prepareStatement("select * from patient where patient_id=?");
		ps.setString(1, key);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			patient = new Patient(rs.getString("patient_id"), rs.getString("patient_nom"), rs.getString("patient_prenom"));
		}
		conn.close();
		return patient;
	}


	public List<Patient> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Patient obj) throws SQLException {
		Connection conn = this.connect();
		
		PreparedStatement ps = conn.prepareStatement("insert into patient (patient_id, patient_nom, patient_prenom) values (?,?,?)");
		ps.setString(1, obj.getId());
		ps.setString(2, obj.getNom());
		ps.setString(3, obj.getPrenom());
		
		ps.executeUpdate();
		conn.close();
	}

	public Patient selectById(Integer key) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
