package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import metier.Visite;

public class DAOVisiteJDBC implements DAOVisite {
	
	private Connection connect() throws SQLException {
		
		Properties pt = new Properties();
		pt.setProperty("user", "root");
		pt.setProperty("password", "");
		pt.setProperty("useSSL", "false");
		pt.setProperty("autoReconnect", "true");
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital?serverTimezone=UTC", pt);
		
	}

	public Visite selectById(Integer key) throws SQLException {
		Connection conn = this.connect();
		Visite visite = null;
		
		PreparedStatement ps=conn.prepareStatement("select * from visite where visite_id=?");
		ps.setInt(1, key);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			visite = new Visite(rs.getString("patient_id"), rs.getString("personnel_nom"), rs.getDate("visite_date"));
		}
		conn.close();
		return visite;
	}

	public ArrayList<Visite> selectAll() throws SQLException {
		Connection conn = this.connect();
		ArrayList<Visite> visites = new ArrayList<Visite>();
		
		PreparedStatement ps=conn.prepareStatement("select * from visite");
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			visites.add(new Visite(rs.getString("patient_id"), rs.getString("personnel_nom"), rs.getDate("visite_date")));
		}
		conn.close();
		return visites;
	}

	public void insert(Visite obj) throws SQLException {
		Connection conn = this.connect();
		
		PreparedStatement ps = conn.prepareStatement("insert into visite (patient_id, personnel_nom, visite_date) values (?,?,?)");
		ps.setString(1, obj.getIdPatient());
		ps.setString(2, obj.getNomMedecin());
		ps.setDate(3, obj.getDateVisite());
		
		ps.executeUpdate();
		conn.close();
		
	}

	public ArrayList<Visite> selectByPatient(int id) throws SQLException {
		Connection conn = this.connect();
		ArrayList<Visite> visites = new ArrayList<Visite>();
		
		PreparedStatement ps=conn.prepareStatement("select * from visite where patient_id=?");
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			visites.add(new Visite(rs.getString("patient_id"), rs.getString("personnel_nom"), rs.getDate("visite_date")));
		}
		
		conn.close();
		return visites;
	}

	

}
