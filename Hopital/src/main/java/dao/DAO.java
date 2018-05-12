package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T,K> {
	
	public T selectById (K key) throws SQLException;
	
	public List<T> selectAll () throws SQLException;

	public void insert (T obj) throws SQLException;
	
	
}
