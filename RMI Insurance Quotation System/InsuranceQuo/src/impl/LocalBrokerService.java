package impl;

import java.util.LinkedList;
import java.util.List;
import core.BrokerService;
import core.ClientInfo;
import core.Quotation;
import core.QuotationService;
import core.VettingService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * Implementation of the broker service that uses RMI.
 *
 */

public class LocalBrokerService implements BrokerService 
{
	public List<Quotation> getQuotations(ClientInfo info) 
	{
		List<Quotation> quotations = new LinkedList<Quotation>();
		try 
		{
            Registry registry = LocateRegistry.getRegistry();
            QuotationService s1 = (QuotationService) registry.lookup("Afqservice");
            QuotationService s2 = (QuotationService) registry.lookup("Ddqservice");
            QuotationService s3 = (QuotationService) registry.lookup("Gpqservice");
            VettingService vs1 = (VettingService) registry.lookup("Vetting");
            
           
            if (vs1 == null) System.out.println("Ohhh");
        	boolean val1=vs1.vetClient(info);
           	if(val1)
           		{
        			Quotation quotation1 = s1.generateQuotation(info);
            		System.out.println("Reference: " + quotation1.getReference() + "    Name: " + info.getName() + " / Price: " + quotation1.getPrice());
            		System.out.println();
            		
            		Quotation quotation2 = s2.generateQuotation(info);
            		System.out.println("Reference: " + quotation2.getReference() + "    Name: " + info.getName() + " / Price: " + quotation2.getPrice());
            		System.out.println();
            		
            		Quotation quotation3 = s3.generateQuotation(info);
            		System.out.println("Reference: " + quotation3.getReference() + "    Name: " + info.getName() + " / Price: " + quotation3.getPrice());
            		System.out.println();
        		}
        		else
        		{
        			System.out.println("Name: " + info.getName() +"   Licence Number:  " +info.getLicenceNumber()+ "   / Mismached penality point ");
        			System.out.println();
        			
        		}
           		
           		
      	}
            catch (Exception e) 
    		{
    			e.printStackTrace();
    		}
            
		return quotations;
	}
}
