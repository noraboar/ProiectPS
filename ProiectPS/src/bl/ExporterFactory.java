package bl;

import bl.CsvExporter;
import bl.Exporter;
import bl.JsonExporter;

public class ExporterFactory {
	public Exporter getExporter(String exporterType){
	      if(exporterType == null){
	         return null;
	      }		
	      if(exporterType.equalsIgnoreCase("CSV")){
	         return new CsvExporter();
	         
	      } else if(exporterType.equalsIgnoreCase("JSON")){
	         return new JsonExporter();
	
	      }
	      return null;
	   }

}
