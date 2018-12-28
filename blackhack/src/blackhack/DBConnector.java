package blackhack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnector {
	
	final String connString = "jdbc:mysql://thevoid.myftp.org/devops18?user=devops18&password=asdasd123321";
	Connection connect;
	
	public DBConnector() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connect = DriverManager.getConnection(connString);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean registerPlayer(Player p) {

		try {
			Statement s = connect.createStatement();
			ResultSet r = s.executeQuery("Select * from blackhack where username = '"+p.getUsername()+"'");

			if (r.absolute(1) == true) {
				System.out.println("User is already in database");
				return false;
				}
			else {
				s.executeUpdate("Insert into blackhack values (default,'"+p.getUsername()+"','"+p.getPassword()+"', 5000)");
				return true;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean login(Player p) {

		try {

			Statement s = connect.createStatement();
			ResultSet r = s.executeQuery("Select username, password from blackhack where username = '"+p.getUsername()+"' and password = '"+p.getPassword()+"'");

			if (r.absolute(1) == true) 
			{
				System.out.println("Successfull!");
				return false;
			}
			else 
			{
				return false;
			}		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("See error code");
			return false;
		}
	}
	
	public int getPlayerCredit(Player p) {
		int credits = 0;

		try 
		{
			Statement s = connect.createStatement();
			ResultSet r = s.executeQuery("Select credits from blackhack where username = '"+p.getUsername()+"'");
			credits = Integer.parseInt(r.getString("credits"));
			
		} 

		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	
		return credits;
	}
	
	public void updateCredits(Player p, String cred) {

		try 
		{
			Statement s = connect.createStatement();
			s.executeUpdate("Update blackhack set credits = '"+cred+"' where username = '"+p.getUsername()+"'");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Unable to get credits");
		}
		
	}
}
