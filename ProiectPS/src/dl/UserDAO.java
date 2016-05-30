package dl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dl.DBConnection;
import models.User;

public class UserDAO {
	public final ArrayList<User> getUser() {
        final ArrayList<User> accounts = new ArrayList<User>();
        try {
            final Connection c = (Connection) DBConnection.getDBConnection();
            final Statement s = c.createStatement();
            final ResultSet rs = s.executeQuery("select * from user");
            while (rs.next()) {
                User utilizator = new User();
                
                // Luam informatiile utilizatorului din baza de date
                utilizator.setNume(rs.getString("Name"));
                utilizator.setUsername(rs.getString("userName"));
                utilizator.setPassword(rs.getString("password"));
                utilizator.setTip(rs.getString("type"));
                accounts.add(utilizator);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
	
	public void addUser(User user){
		try{
			final Connection c = (Connection) DBConnection.getDBConnection();
			String s="INSERT INTO user(Name,userName,password,type) VALUES ('"+user.getNume()
					+"','"+user.getUsername()+"','"+user.getPassword()+"','"+"USER"+"');";
			Statement stmt = c.createStatement();
		     stmt.executeUpdate(s);
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
		} catch(SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void updatePassword(String username,String password){
		try{
			final Connection c = (Connection) DBConnection.getDBConnection();
			String s="UPDATE user SET password='"+password+"' WHERE userName='"+username+"';";
			Statement stmt = c.createStatement();
		     stmt.executeUpdate(s);
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
		} catch(SQLException e) {
            e.printStackTrace();
        }
		
	}
}
