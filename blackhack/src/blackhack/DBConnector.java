package blackhack;

import java.sql.Connection;
import java.sql.DriverManager;
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
				status = 0;
				}
			else {
				r = s.executeQuery("Insert into blackhack values (default,"+username+","+password+", 5000)");
				r.close();
				status = 1;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	int login(Player p) {
		String username = p.getUsername();
		String password = p.getPassword();
		int status = 0;
		Statement s = null;
		ResultSet r = null;
		try (Connection connect = DriverManager.getConnection(conn);){
			s = connect.createStatement();
			r = s.executeQuery("Select username, password from blackhack where username = '"+username+"' and password = '"+password+"'");
			if (r.absolute(1) == true) {
				status = 1;
				}
			else {
				status = 0;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(status);
		return status;
	}
	
	int showCredits(Player p) {
		String username = p.getUsername();
		int credits = 0;
		Statement s = null;
		ResultSet r = null;
		try (Connection connect = DriverManager.getConnection(conn);){
			s = connect.createStatement();
			r = s.executeQuery("Select credits from blackhack where username = '"+username+"'");
			credits = Integer.parseInt(r.getString("credits"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return credits;
	}
	
	void updateCredits(Player p, String cred) {
		String username = p.getUsername();
		Statement s = null;
		ResultSet r = null;
		try (Connection connect = DriverManager.getConnection(conn);){
			s = connect.createStatement();
			r = s.executeQuery("Update blackhack set credits = '"+cred+"' where username = '"+username+"'");
			r.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
