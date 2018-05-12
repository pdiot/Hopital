package dao;

import java.sql.SQLException;

import metier.*;

public interface DAOPatient extends DAO<Patient, Integer> {

	Patient selectById(String key) throws SQLException;

}
