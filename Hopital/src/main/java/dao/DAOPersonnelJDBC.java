package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import metier.Medecin;
import metier.Personnel;
import metier.Secretaire;
import metier.Visite;

public class DAOPersonnelJDBC implements DAOPersonnel {
	
	private Connection connect() throws SQLException {
		Properties pt = new Properties();
		pt.setProperty("user", "root");
		pt.setProperty("password", "ajcformation");
		pt.setProperty("useSSL", "false");
		pt.setProperty("autoReconnect", "true");
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital?serverTimezone=UTC", pt);
	}

	public Personnel selectById(Integer key) throws SQLException {
		Connection conn = this.connect();		
		Personnel personnel = null;
		
		PreparedStatement ps=conn.prepareStatement("select * from personnel where personnel_id=?");
		ps.setInt(1, key);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			personnel = new Personnel(rs.getString("personnel_nom"), rs.getString("personnel_pwd"));
		}
		conn.close();
		return personnel;
	}
	


	public Personnel selectByLogin(String login) throws SQLException {
		Connection conn = this.connect();		
		Personnel personnel = null;
		
		PreparedStatement ps=conn.prepareStatement("select * from personnel where personnel_nom=?");
		ps.setString(1, login);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if(rs.getString("personnel_role").equals("medecin")) {
				personnel = new Medecin(rs.getString("personnel_nom"), rs.getString("personnel_pwd"));
			}
			else
			{
			personnel = new Secretaire(rs.getString("personnel_nom"), rs.getString("personnel_pwd"));
			}
		}
		conn.close();
		return personnel;
	}
	

	public String selectRole(String login) throws SQLException {
		Connection conn = this.connect();		
		String role = "";
		
		PreparedStatement ps=conn.prepareStatement("select * from personnel where personnel_nom=?");
		ps.setString(1, login);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			role = rs.getString("personnel_role");
		}
		conn.close();
		return role;
	}

	public List<Personnel> selectAll() throws SQLException {
		Connection conn = this.connect();		
		ArrayList<Personnel> personnels = new ArrayList<Personnel>();
		
		PreparedStatement ps=conn.prepareStatement("select * from personnel");

		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getString("personnel_role").equals("medecin")) {
				personnels.add(new Medecin(rs.getString("personnel_nom"), rs.getString("personnel_pwd")));
			} else {
				personnels.add(new Secretaire(rs.getString("personnel_nom"), rs.getString("personnel_pwd")));
			}
		}
		conn.close();
		return personnels;
	}

	public void insert(Personnel obj) throws SQLException {
		Connection conn = this.connect();
		
		PreparedStatement ps = conn.prepareStatement("insert into personnel (personnel_nom, personnel_pwd, role) values (?,?,?)");
		ps.setString(1, obj.getNom());
		ps.setString(2, obj.getPassword());
		if (obj instanceof Medecin) {
			ps.setString(3, "medecin");
		} else {
			ps.setString(3, "secretaire");			
		}
		
		ps.executeUpdate();

	}


}
