package test;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import core.ClientInfo;
import core.Quotation;
import impl.AFQService;
import impl.DDQService;
import impl.GPQService;
import core.QuotationService;
import core.VettingService;
import impl.LocalBrokerService;
import impl.RMIVettingService;

public class Main
{
	public static void main(String[] args) throws AccessException, RemoteException 
	{
		Registry registry = null;
		try 
		{
			//creating registry
			registry = LocateRegistry.createRegistry(1099);
			System.out.println("RMI Server running on port: 1099");
		} 
		catch (RemoteException re) 
		{
			try 
			{
				registry = LocateRegistry.getRegistry();
				System.out.println("Connected to RMI Server running on port: 1099");
			} catch (RemoteException re2) 
			{
				System.out.println("BROKEN");
			}
		}
		
		
			RMIVettingService vss;
			VettingService vs = (VettingService) UnicastRemoteObject.exportObject(vss=new RMIVettingService(), 0);
			AFQService afqs;
			QuotationService afq = (QuotationService) UnicastRemoteObject.exportObject(afqs=new AFQService(), 0);
			DDQService ddqs;
			QuotationService ddq = (QuotationService) UnicastRemoteObject.exportObject(ddqs=new DDQService(), 0);
			GPQService gpqs;
			QuotationService gpq = (QuotationService) UnicastRemoteObject.exportObject(gpqs=new GPQService(), 0);
			
			
			try 
			{
				if (registry != null) 
				{
					registry.bind("Vetting", vs);
					registry.bind("Afqservice", afq);
					registry.bind("Ddqservice", ddq);
					registry.bind("Gpqservice", gpq);
				}
			}
			catch (AlreadyBoundException e) 
			{
				e.printStackTrace();
			}
			
			LocalBrokerService broker = new LocalBrokerService();
			for (ClientInfo info : clients) 
			{
				for(Quotation quotation : broker.getQuotations(info)) 
				{
					//Iteration of clients
				}
			}
	}

	/**
	 * Test client data
	 */
	public static final ClientInfo[] clients = 
		{
			new ClientInfo() 
			{
				@Override
				public String getName() 
				{
					return "Niki Collier";
				}

				@Override
				public char getSex() 
				{
					return ClientInfo.MALE;
				}

				@Override
				public int getAge() 
				{
					return 41;
				}

				@Override
				public int getPoints() 
				{
					return 0;
				}

				@Override
				public int getNoClaims() 
				{
					return 7;
				}
				
				@Override 
				public String getLicenceNumber() 
				{
					return "PQR254/1";
				}
			},
			new ClientInfo() 
			{
				@Override
				public String getName() 
				{
					return "Old Geeza";
				}

				@Override
				public char getSex() 
				{
					return ClientInfo.MALE;
				}

				@Override
				public int getAge() 
				{
					return 65;
				}

				@Override
				public int getPoints() 
				{
					return 0;
				}

				@Override
				public int getNoClaims() 
				{
					return 2;
				}
				
				@Override 
				public String getLicenceNumber() 
				{
					return "ABC123/4";
				}
			},			
			new ClientInfo() 
			{
				@Override
				public String getName()
				{
					return "Donald Duck";
				}

				@Override
				public char getSex() 
				{
					return ClientInfo.MALE;
				}

				@Override
				public int getAge() 
				{
					return 35;
				}

				@Override
				public int getPoints() 
				{
					return 5;
				}

				@Override
				public int getNoClaims() 
				{
					return 2;
				}

				@Override 
				public String getLicenceNumber() 
				{
					return "XYZ567/9";
				}
			}			
		};
}
