package impl;

import java.rmi.RemoteException;

import core.AbstractQuotationService;
import core.ClientInfo;
import core.Quotation;
import core.QuotationService;
import core.VettingService;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementation of Quotation Service for Dodgy Drivers Insurance Company
 */
public class DDQService extends AbstractQuotationService implements QuotationService 
{
	static VettingService vs;
	static QuotationService ddq;
	static Registry registry = null;
	
	public static void main(String args[]) throws RemoteException
	{
	
		try {
			registry = LocateRegistry.createRegistry(1099);
			System.out.println("RMI Server running on port: 1099");
		} catch (RemoteException re) {
			try {
				registry = LocateRegistry.getRegistry();
				System.out.println("Connected to RMI Server running on port: 1099");
			} catch (RemoteException re2) {
				System.out.println("BROKEN");
			}
		}
		
		RMIVettingService rvs;
		vs = (VettingService) UnicastRemoteObject.exportObject(rvs=new RMIVettingService(), 0);
		ddq = (QuotationService) UnicastRemoteObject.exportObject(new DDQService(), 0);
			
			//advertising the service
		try {
			if (registry != null) {
				registry.bind("Ddqservice", ddq);
				registry.bind("Vetting", vs);
			}
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		
	}
	
	// All references are to be prefixed with an DD (e.g. DD001000)
	public static final String PREFIX = "DD";
	
	/**
	 * Implementation of the Quotation interface for the DodgyDrivers Service
	 */
	public static class DDQuotation implements Quotation {
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
	 * 5% discount per penalty point (3 points required for qualification)
	 * 50% penalty for <= 3 penalty points
	 * 10% discount per year no claims
	 */
	@Override
	public Quotation generateQuotation(ClientInfo info)  
	{
		// Create an initial quotation between 800 and 1000
		double price = generatePrice(800, 200);
		
		// 5% discount per penalty point (3 points required for qualification)
		int discount = (info.getPoints() > 3) ? 5*info.getPoints():-50;
		
		// Add a no claims discount
		discount += getNoClaimsDiscount(info);
		
		// Generate the quotation and send it back
		DDQuotation quotation = new DDQuotation();
		
		// Use the reference generator to create a reference
		quotation.reference = generateReference(PREFIX);
		quotation.info = info;
		quotation.price = (price * (100-discount)) / 100;
		
		return quotation;
	}

	private int getNoClaimsDiscount(ClientInfo info) {
		return 10*info.getNoClaims();
	}

}
