package quotation;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.data.Status;
import org.restlet.routing.Router;
import com.google.gson.Gson;

import core.ClientInfo;
import core.Quotation;
import quotation.QuotationService;


public class QuotationApplication extends Application
{
	//a Map whose keys are quotation reference numbers and  whose values are quotations
	private Map<String, Quotation>quotations=new HashMap<String,Quotation>();
	private QuotationService service;
	private Gson gson;
	public QuotationApplication(QuotationService service)
	{
		this.service=service;
		//Create an instance of the Gson parser.
		gson=new Gson();
	}

	public Restlet createInboundRoot() 
	{
		Router router = new Router(getContext());
		// handle quotation creation
		router.attach("/quotation", new Restlet() {
			@Override
			public void handle(Request request, Response response) {
				
				if (request.getMethod().equals(Method.POST)) 
				{
					//converting Json into java object using gson
					ClientInfo info=gson.fromJson(request.getEntityAsText(), ClientInfo.class);
					//response from the POST /quotation operation call generateQuotation()
					Quotation quotation=service.generateQuotation(info);
					//store the created quotation in  the Map, using the reference as the key
					quotations.put(quotation.reference,quotation);
					//get the part of the URL that identifies a restlet.
					String url=request.getRootRef().toString()+"/quotation/"+quotation.reference;
					//return both the key and the URL required to retrieve the quotation
					response.setEntity(gson.toJson(new QuotationResponse(quotation.reference,url)),MediaType.APPLICATION_JSON);
					
				} 
				else {
					response.setStatus(Status.CLIENT_ERROR_FORBIDDEN);
				}
			}
		});
		
		// handle quotation retrieval
		router.attach("/quotation/{reference}", new Restlet() 
		{
			@Override
			public void handle(Request request, Response response) 
			{
				//getting reference associated with url
				String reference = (String) request.getAttributes().get("reference");
				//check whether retrieved reference is in map key.
				if (quotations.containsKey(reference)) 
				{
					response.setStatus(Status.SUCCESS_OK);
					
					if (request.getMethod().equals(Method.GET)) 
					{
						//return the URL required to access the generated quotation.
						response.setEntity(gson.toJson(quotations.get(reference)), MediaType.APPLICATION_JSON);
					}  
					else 
					{
					response.setStatus(Status.CLIENT_ERROR_NOT_FOUND);
					}
				}
			}
			
			});
		return router;
	
	}
}