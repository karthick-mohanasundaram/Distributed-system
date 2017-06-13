package test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import core.ClientInfo;
import core.Quotation;
import core.QuotationService;
import core.VettingService;

public class GPQServiceTest {
	public static void main(String[] args) {
		try 
		{
            Registry registry = LocateRegistry.getRegistry(1099);
            for(String str : registry.list()) 
            {
            	System.out.println(str);
            }
            QuotationService s1 = (QuotationService) registry.lookup("Gpqservice");
            VettingService vs = (VettingService) registry.lookup("Vetting");
            
            
            for (ClientInfo info : Main.clients)
            	{
            	if (vs == null) System.out.println("Ohhh");
            		boolean val=vs.vetClient(info);
            		if(val)
            		{
            			Quotation quotation = s1.generateQuotation(info);
                		
                		System.out.println("Reference: " + quotation.getReference() + " / Price: " + quotation.getPrice());
                		System.out.println();
            		}
            		else
            		{
            			System.out.println("Licence Number:  " +info.getLicenceNumber()+ " / Mismached penality point ");
            			System.out.println();
            			
            		}
            		
            	}        
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
