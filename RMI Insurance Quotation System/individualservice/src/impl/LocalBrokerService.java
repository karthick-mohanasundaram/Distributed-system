package impl;

import java.util.LinkedList;
import java.util.List;
import core.BrokerService;
import core.ClientInfo;
import core.Quotation;
import core.QuotationService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * Implementation of the broker service that uses the RMI.
 */
public class LocalBrokerService implements BrokerService 
{
	public List<Quotation> getQuotations(ClientInfo info) 
	{
		List<Quotation> quotations = new LinkedList<Quotation>();
		try 
		{
            Registry registry = LocateRegistry.getRegistry();
            for (String s : registry.list())
            	{
            	//System.out.println(s);
            	}
            	QuotationService s1 = (QuotationService) registry.lookup("Afqservice");
            	
            	quotations.add(s1.generateQuotation(info));
            	
            	QuotationService s2 = (QuotationService) registry.lookup("Ddqservice");
            	quotations.add(s2.generateQuotation(info));
            	
            	QuotationService s3 = (QuotationService) registry.lookup("Gposervice");
            	quotations.add(s3.generateQuotation(info));
            		
		} 
		catch (Exception e) 
		{
			System.out.println("Local broker Trouble: " + e);
			e.printStackTrace();
		}
		return quotations;
		}
}
