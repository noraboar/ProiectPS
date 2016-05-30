package bl;

import java.util.List;

import bl.Exporter;
import bl.ExporterFactory;
import models.Produs;
import dl.ProdusDAO;


public class ProdusManager {
	
	private ProdusDAO produsDAO=new ProdusDAO();
	private ExporterFactory exporterFactory = new ExporterFactory();
	
	public int addProdus(Produs produs){
		return produsDAO.addProdus(produs);
	}
	
	public List<Produs> getProduse(){
		return produsDAO.getProduse();
	}
	
	public void deleteProdus(Produs produs){
		produsDAO.deleteProdus(produs);
	}
	
	public void updateProdus(Produs produs, int stocNou){
		produsDAO.updateProdus(produs, stocNou);
	}
	
	public void exportBilete(String fileType) {
		List<Produs> listaProduse = getProduse();
		Exporter exporter = exporterFactory.getExporter(fileType);
		exporter.exportProduse(listaProduse);
	}

}
