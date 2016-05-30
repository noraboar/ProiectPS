package bl;

import models.CosCumparaturi;
import dl.CosCumparaturiDAO;


public class CosCumparaturiManager {
	
	private CosCumparaturiDAO cosDAO = new CosCumparaturiDAO();
	
	public int addComanda(CosCumparaturi cos){
		return cosDAO.addComanda(cos);
	}
	
	public void addInTabelaDeLegatura(int id, String numeProdus){
		cosDAO.addInTabelaDeLegatura(id, numeProdus);
	}

}
