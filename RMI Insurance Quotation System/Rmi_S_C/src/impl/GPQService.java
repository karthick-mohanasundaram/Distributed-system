package impl;

import java.rmi.RemoteException;
import core.AbstractQuotationService;
import core.ClientInfo;
import core.Quotation;
import core.QuotationService;
//import core.VettingService;

import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/**
 * Implementation of the Girl Power insurance quotation service.
 */
public class GPQService extends AbstractQuotationService implements QuotationService 
{	
	static QuotationService gpq;
	static Registry registry = null;
	public static void main(String args[]) throws RemoteException
	{
	
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
		GPQService gpqs;
		gpq = (QuotationService) UnicastRemoteObject.exportObject(gpqs=new GPQService(), 0);
		try 
		{
			if (registry != null) 
			{
				//advertising the service
				registry.bind("Gpqservice", gpq);
		
			}
		} 
		catch (AlreadyBoundException e) 
		{
			e.printStackTrace();
		}

	}


	// All references are to be prefixed with an DD (e.g. DD001000)
	public static final String PREFIX = "GP";
	
	/**
	 * Implementation of the Quotation interface for the Girl Power Service
	 */
	public static class GPQuotation implements Quotation {
		String reference;
		ClientInfo info;
		double price;
		
		@Override
		public String getReference() {
			return reference;
		}

		@Override
		public ClientInfo getClientInfo() {
			return info;
		}

		@Override
		public double getPrice() {
			return price;
		}
	}
	
	/**
	 * Quote generation:
	 * 50% discount for being female
	 * 20% discount for no penalty points
	 * 15% discount for < 3 penalty points
	 * no discount for 3-5 penalty points
	 * 100% penalty for > 5 penalty points
	 * 5% discount per year no claims
	 */
	@Override
	public Quotation generateQuotation(ClientInfo info)  {
		// Create an initial quotation between 600 and 1000
		double price = generatePrice(600, 400);
		
		// Automatic 50% discount for being female
		int discount = (info.getSex() == ClientInfo.FEMALE) ? 50:0;
		
		// Add a points discount
		discount += getPointsDiscount(info);
		
		// Add a no claims discount
		discount += getNoClaimsDiscount(info);
		
		// Generate the quotation and send it back
		GPQuotation quotation = new GPQuotation();
		
		// Use the reference generator to create a reference
		quotation.reference = generateReference(PREFIX);
		quotation.info = info;
		quotation.price = (price * (100-discount)) / 100;
		
		return quotation;
	}

	private int getNoClaimsDiscount(ClientInfo info) {
		return 5*info.getNoClaims();
	}

	private int getPointsDiscount(ClientInfo info) 
	{
		if (info.getPoints() == 0) return 20;
		if (info.getPoints() < 3) return 15;
		if (info.getPoints() < 6) return 0;
		return -100;
	}

}
