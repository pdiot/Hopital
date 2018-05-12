package dao;

import java.sql.SQLException;
import java.util.List;

import metier.Visite;

public interface DAOVisite extends DAO<Visite, Integer> {
	public List<Visite> selectByPatient(int id) throws SQLException;
}
