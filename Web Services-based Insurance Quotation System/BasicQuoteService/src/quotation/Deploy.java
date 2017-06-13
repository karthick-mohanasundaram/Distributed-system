package quotation;

import javax.xml.ws.Endpoint;

import vetting.LocalVettingService;

public class Deploy {
	public static void main(String[] args) throws Exception
	{
		
		  //Creates and publishes an endpoint for the AFQService object at the below address. 
	        Endpoint.publish("http://localhost:9001/QuotationService/generateQuotation", new AFQService());
	      //Creates and publishes an endpoint for the DDQService object at the below address.
	        Endpoint.publish("http://localhost:9002/QuotationService/generateQuotation", new DDQService());
	      //Creates and publishes an endpoint for the GPQService object at the below address.
	        Endpoint.publish("http://localhost:9003/QuotationService/generateQuotation", new GPQService());
	      //Creates and publishes an endpoint for the Vetting Service object at the below address.
	        Endpoint.publish("http://localhost:9000/VettingService/vetClient", new LocalVettingService());
	        
	  }

    }
