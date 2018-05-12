package dao;

import java.sql.SQLException;

import metier.Personnel;

public interface DAOPersonnel extends DAO<Personnel, Integer> {
	public Personnel selectByLogin(String login) throws SQLException;
	public String selectRole(String login) throws SQLException;
}
