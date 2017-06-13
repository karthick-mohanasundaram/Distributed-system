package quotation;

// VettingRestlet class that implements the vetting service as a RESTful service

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Status;

import com.google.gson.Gson;

public class VettingRestlet extends Restlet {
	Map<String , Integer> pointsDB=new HashMap<String,Integer>();
	{
		pointsDB.put("PQR254/1", 0);
		pointsDB.put("ABC123/4", 0);
		pointsDB.put("XYZ567/9", 5);
	}
private Gson gson;
public VettingRestlet(){
	gson=new Gson();
	
}


@Override
public void handle(Request request,Response response)
{
	//doing a url encoding of the license number
	String licence_no=URLDecoder.decode((String)request.getAttributes().get("licenseNumber"));
	System.out.println("licence_no"+licence_no);
	if (pointsDB.containsKey(licence_no)){
		response.setStatus(Status.SUCCESS_OK);
		if(request.getMethod().equals(Method.GET))
		{
			response.setEntity(gson.toJson(new Points(licence_no,pointsDB.get(licence_no))),MediaType.APPLICATION_ALL_JSON);
		}
		else
		{
			response.setStatus(Status.CLIENT_ERROR_FORBIDDEN);
		}
	}
}
	

}
