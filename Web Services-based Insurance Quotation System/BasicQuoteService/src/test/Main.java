package test;

import core.ClientInfo;
import core.Quotation;
import impl.WSBrokerService;

public class Main 
{
	public static void main(String[] args) 
	{
	
		// Create the broker and run the test data
		WSBrokerService broker;
		broker = new WSBrokerService();
		for (ClientInfo info : clients) {
			System.out.println("Name: " +info.getName());
			for(Quotation quotation : broker.getQuotations(info)) {
				System.out.println("Reference: " + quotation.getReference() + " / Price: " + quotation.getPrice());
			}
		}
		
	}
		
	/**
	 * Test client data
	 */
	public static final ClientInfo[] clients = {
			new ClientInfo("Niki Collier",ClientInfo.MALE,41,0,7,"PQR254/1"),
			new ClientInfo("Old Geeza",ClientInfo.MALE,65,0,2,"ABC123/4"),
			new ClientInfo("Donald Duck",ClientInfo.MALE,35,5,2,"XYZ567/9")
			};
}
