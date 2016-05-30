package bl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import models.Produs;

public class JsonExporter implements Exporter {

	@Override
	public void exportProduse(List<Produs> listaProduse) {
		// TODO Auto-generated method stub
		try{
			FileWriter writer = new FileWriter("Produse.json");
			for(Produs produs: listaProduse){
			     writer.append(produs.getNume());
			     writer.append(" Pret: ");
			     writer.append(String.valueOf(produs.getPret()));
			     writer.append(" Stoc: ");
			     writer.append(String.valueOf(produs.getStoc()));
			     writer.append("\n");
			}
		    writer.flush();
		    writer.close();
		}catch(IOException e)
		{
		     e.printStackTrace();
		}
		
	}

}
