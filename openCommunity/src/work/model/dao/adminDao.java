/**
 * 
 */
package work.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * ## DAO Pattern
 * => DataSource(FactoryDao)에게 Connection, close 사용
 * 
 * @author limjinha
 */
public class adminDao {

	private FactoryDao factory = FactoryDao.getInstance();
	
	public Connection getConnection() {
		return factory.getConnection();
	}
	
	public String selectNoticeList() {
		return null;
	}
	
	public String selectDisagreeNoticeByDate() {
		return null;
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	


}
