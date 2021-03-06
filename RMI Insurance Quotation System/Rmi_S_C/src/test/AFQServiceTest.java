package test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import core.ClientInfo;
import core.Quotation;
import core.QuotationService;

public class AFQServiceTest 
{
	public static void main(String[] args) 
	{
		try 
		{
            Registry registry = LocateRegistry.getRegistry(1099);
            QuotationService s1 = (QuotationService) registry.lookup("Afqservice");
            for (ClientInfo info : Main.clients)
            	{
            		Quotation quotation = s1.generateQuotation(info);
            		System.out.println("Reference: " + quotation.getReference() + " / Price: " + quotation.getPrice());
            	}
           
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
