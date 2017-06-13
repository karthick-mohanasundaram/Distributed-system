package impl;
import java.util.HashMap;
import core.ClientInfo;
import core.VettingService;

public class RMIVettingService implements VettingService
{
	
	@Override
	public boolean vetClient(ClientInfo info)
	{
		// TODO Auto-generated method stub
		
		int point=info.getPoints();
		
		String license=info.getLicenceNumber();
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
	      
	     //Put elements to the map
	     hmap.put("PQR254/1", 0);
	     hmap.put("ABC123/4", 2);
	     hmap.put("XYZ567/9", 5);
	     
	     Integer value = hmap.get(license);
	     return (value != null && value==point);
	     
	}

}
