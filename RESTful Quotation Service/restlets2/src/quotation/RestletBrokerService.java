package quotation;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import com.google.gson.JsonSyntaxException;

import java.io.ByteArrayOutputStream;
import com.google.gson.Gson;
import core.Quotation;
import core.ClientInfo;
import core.BrokerService;

public class RestletBrokerService implements BrokerService{
	static Gson gson=new Gson();
	static String[] sURLS=new String[]{
			"http://localhost:8182/afq/",
			"http://localhost:8182/ddq/",
			"http://localhost:8182/gpq/",
			
		};
	
	static String vettingURL="http://localhost:8182/vetting/";
	public List<Quotation> getQuotations(ClientInfo info)
	{
	List<Quotation> q=new LinkedList<Quotation>();
		if(vetClient(info))
		{
			for(String url:sURLS)
			{
		
					QuotationResponse response;
					try {
						response = gson.fromJson(post(url+"quotation",gson.toJson(info)),QuotationResponse.class);
						//gives unique url to access quotation generated for each client 
						System.out.println("response_url: " + response.url);
						String json = get(response.url);
						//prints generated quotation with url on terminal
						System.out.println("Json: " + json);
						q.add(gson.fromJson(json,Quotation.class));
					} catch (JsonSyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ResourceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		
			}
		}
	
		return q;
	}
	private String post(String url,String json)throws ResourceException, IOException
	{
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		new ClientResource(url).post(json).write(out);
		return out.toString();
	}
	private String get(String url)throws ResourceException, IOException
	{
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		new ClientResource(url).get().write(out);
		return out.toString();
	}
	private boolean vetClient(ClientInfo info) throws JsonSyntaxException, ResourceException
	{
		Points points;
		try {
			points = gson.fromJson(get(vettingURL+URLEncoder.encode(info.licenseNumber)),Points.class);
			return (points.points==info.points);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}	
}

