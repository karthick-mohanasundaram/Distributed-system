package impl;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import core.AbstractQuotationService;
import core.ClientInfo;
import core.Quotation;
import core.QuotationService;
import core.VettingService;

public class RMIVettingService implements VettingService
{
	
	@Override
	public boolean vetClient(ClientInfo info)
	//public void vetClient(ClientInfo info)
	{
		// TODO Auto-generated method stub
		
		int point=info.getPoints();
		
		String license=info.getLicenceNumber();
		
		//System.out.println("inside vetting");
		//String d="PQR254/1";
		//Map<Integer, String> simplePair = new HashMap<>(42, "Second");
		
		 //Create a hash map
	     HashMap<String, Integer> hmap = new HashMap<String, Integer>();
	      
	     //Put elements to the map
	     hmap.put("PQR254/1", 0);
	     hmap.put("ABC123/4", 2);
	     hmap.put("XYZ567/9", 5);
	     
	     Integer value = hmap.get(license);
	     //int discount = (info.getSex() == ClientInfo.MALE) ? 30:0;
	     return (value != null && value==point);
	     
	     //{
	    	 //System.out.println("true");
	    	// return true;
	     //}
	    // else
	    // {
	    	 //System.out.println("culprite caught");
	    	 //System.out.println("Licence number:" + license);
	    	 //System.out.println("Real penality point" + value);
	    	 //System.out.println("Entered penality point" + info.getPoints());
	    	 //System.out.println("false");
	    	// return false;
	     //}
	     
	     //Set set = hmap.entrySet();
	     //Iterator iterator = set.iterator();
	     
	     //while(iterator.hasNext()) 
	   //  {
	    //     Map.Entry mentry = (Map.Entry)iterator.next();
	     //    if(mentry.getKey()==info.getLicenceNumber())
	    	// {
	        	 
	        //	 if(mentry.getValue()==point)
	       // 	 {
	       // 	 }
	        //	 else
	        	// {
	     
	    // String value = hmap.get(license);
//
	//        		 System.out.println("culprite caught");
	  //      		 System.out.println("Real penality point" + mentry.getValue());
	    //    		 System.out.println("Entered penality point" + info.getPoints());
	   //     	 }
	        	 
	     //   	 }
	    		 
	   // 	 }
	     //    System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	       //  System.out.println(mentry.getValue());
	     // }
	      
	    //  	 if(hm.get("Zara")==info.getPoints())
	    	// {
	    //		 
	  //  	 }
	//    	 else
//	    	 {
	    	//	 
	    //		 System.out.println("culprit has been caught");
	  //  		 System.out.println("Original penality point" +);
	    		// System.out.println(info.getLicenceNumber());
	    	 //}
	    	  //}
	      
	      
	      
		
		//return false;
	}

}
