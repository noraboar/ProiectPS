package dl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dl.DBConnection;
import models.CosCumparaturi;

public class CosCumparaturiDAO {
	
	public int addComanda(CosCumparaturi cos){
		int nr=0;
		try{
			final Connection c = (Connection) DBConnection.getDBConnection();
			final Statement s = c.createStatement();
			ResultSet rezi= s.executeQuery("SELECT MAX(id) FROM cos");
            if(rezi.next()){
            	nr=rezi.getInt("MAX(id)");
            }
			String q="INSERT INTO cos(id,pretTotal) VALUES ('"
					+(nr+1)+"','"+cos.getPretTotal()+"');";
			Statement stmt = c.createStatement();
		     stmt.executeUpdate(q);
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
		} catch(SQLException e) {
            e.printStackTrace();
        }
		return nr+1;
	}
	
	public void addInTabelaDeLegatura(int id, String numeProdus){
		try{
			final Connection c = (Connection) DBConnection.getDBConnection();
			String q="INSERT INTO produs_has_cos(produs_nume,cos_id) VALUES ('"
					+numeProdus+"','"+id+"');";
			Statement stmt = c.createStatement();
		     stmt.executeUpdate(q);
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
		} catch(SQLException e) {
            e.printStackTrace();
        }
	}

}
