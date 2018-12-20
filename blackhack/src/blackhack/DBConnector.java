package blackhack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	
	final String conn = "jdbc:mysql://thevoid.myftp.org/devops18?user=devops18&password=asdasd123321";

	
	int registerPlayer(Player p) {
		String username = p.getUsername();
		String password = p.getPassword();
		int status = 0;
		Statement s = null;
		ResultSet r = null;
		try (Connection connect = DriverManager.getConnection(conn);){
			s = connect.createStatement();
			r = s.executeQuery("Select * from blackhack where name = "+username);
			if (username.equals(r.getString("name"))) {
				r = s.executeQuery("Insert into blackhack values (default,"+username+","+password+", 5000)");
				r.close();
				status = 1;
				}
			else {
				status = 0;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
}
