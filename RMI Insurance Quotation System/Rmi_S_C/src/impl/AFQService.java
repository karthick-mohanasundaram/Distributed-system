package impl;

import core.AbstractQuotationService;
import core.ClientInfo;
import core.Quotation;
import core.QuotationService;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



/**
 * Implementation of the AuldFellas insurance quotation service.
 */

public class AFQService extends AbstractQuotationService implements QuotationService 
{
	static QuotationService afq;
	static Registry registry = null;
	public static void main(String args[]) throws RemoteException
	{
	
		Registry registry = null;
		try 
		{
			registry = LocateRegistry.createRegistry(1099);
			System.out.println("RMI Server running on port: 1099");
			
		}
		catch (RemoteException re) 
		{
			
			try 
			{
				registry = LocateRegistry.getRegistry();
				System.out.println("RMI Server running on port: 1099");
			} 
			catch (RemoteException re2) 
			{
				System.out.println("BROKEN");
			}
		}
			//creating instance of remote service
			AFQService afqs;
			afq = (QuotationService) UnicastRemoteObject.exportObject(afqs=new AFQService(), 0);
			
			try 
			{
				if (registry != null) registry.bind("Afqservice", afq); //advertising the service
			} 
			catch (AlreadyBoundException e) 
			{
				e.printStackTrace();
			}
		
	}
			
	// All references are to be prefixed with an AF (e.g. AF001000)
	public static final String PREFIX = "AF";
	
	/**
	 * Implementation of the Quotation interface for the AuldFellas Service
	 */
	public static class AFQuotation implements Quotation 
	{
		String reference;
		ClientInfo info;
		double price;
		
		@Override
		public String getReference() 
		{
			return reference;
		}

		@Override
		public ClientInfo getClientInfo() 
		{
			return info;
		}

		@Override
		public double getPrice() 
		{
			return price;
		}
	}
	
	/**
	 * Quote generation:
	 * 30% discount for being male
	 * 2% discount per year over 60
	 * 20% discount for less than 3 penalty points
	 * 50% penalty (i.e. reduction in discount) for more than 60 penalty points 
	 */
	@Override
	
	public Quotation generateQuotation(ClientInfo info)
	{
		//Create an initial quotation between 600 and 1200
		double price = generatePrice(600, 600);
		
		// Automatic 30% discount for being male
		int discount = (info.getSex() == ClientInfo.MALE) ? 30:0;
		
		// Automatic 2% discount per year over 60...
		discount += (info.getAge() > 60) ? (2*(info.getAge()-60)) : 0;
		
		// Add a points discount
		discount += getPointsDiscount(info);
		
		// Generate the quotation and send it back
		AFQuotation quotation = new AFQuotation();
		// Use the reference generator to create a reference
		quotation.reference = generateReference(PREFIX);
		quotation.info = info;
		quotation.price = (price * (100-discount)) / 100;
		
		return quotation;
	}

	private int getPointsDiscount(ClientInfo info)
	{
		if (info.getPoints() < 3) return 20;
		if (info.getPoints() <= 6) return 0;
		return -50;
		
	}
	
	

}