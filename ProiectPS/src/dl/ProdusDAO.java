package dl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Produs;
import dl.DBConnection;

public class ProdusDAO {
	
	public final ArrayList<Produs> getProduse() {
        final ArrayList<Produs> produse = new ArrayList<Produs>();
        try {
            final Connection c = (Connection) DBConnection.getDBConnection();
            final Statement s = c.createStatement();
            final ResultSet rs = s.executeQuery("select * from produs");
            while (rs.next()) {
                Produs produs = new Produs();
                
                // Luam informatiile utilizatorului din baza de date
                produs.setNume(rs.getString("nume"));
                produs.setPret(rs.getInt("pret"));
                produs.setStoc(rs.getInt("stoc"));
                
                produse.add(produs);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produse;
    }

	public int addProdus(Produs produs) {
		List<Produs> listaProduse= getProduse();
		int existaDeja=0;
		for(Produs p : listaProduse){
			if(p.getNume().equals(produs.getNume())){
				existaDeja = 1;
			}
		}
		if(existaDeja==0){
		try{
			final Connection c = (Connection) DBConnection.getDBConnection();
			String q="INSERT INTO produs(nume,pret,stoc) VALUES ('"
					+produs.getNume()+"','"+produs.getPret()+"','"+produs.getStoc()+"');";
			Statement stmt = c.createStatement();
		     stmt.executeUpdate(q);
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
		} catch(SQLException e) {
            e.printStackTrace();
        }
		}
		return existaDeja;
	}
	
	public void deleteProdus(Produs produs){
		try{
			final Connection c = (Connection) DBConnection.getDBConnection();
			String sb="DELETE FROM produs_has_cos WHERE produs_nume='"+produs.getNume()+"';";
			String s="DELETE FROM produs WHERE nume='"+produs.getNume()+"';";
			Statement stmt = c.createStatement();
			stmt.executeUpdate(sb);
		     stmt.executeUpdate(s);
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
		} catch(SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void updateProdus(Produs produs,int stocNou){
		try{
			final Connection c = (Connection) DBConnection.getDBConnection();
			String s="UPDATE produs SET stoc='"+stocNou+"' WHERE nume='"+produs.getNume()+"';";
			Statement stmt = c.createStatement();
		     stmt.executeUpdate(s);
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
		} catch(SQLException e) {
            e.printStackTrace();
        }
	}
}
