package test;

import core.ClientInfo;
import core.Quotation;
import impl.AFQService;

public class AFQServiceTest 
{
	public static void main(String[] args) 
	{
		try 
		{
			AFQService service = new AFQService();
			for (ClientInfo info : Main.clients)
				{
					Quotation quotation = service.generateQuotation(info);
					System.out.println("Reference: " + quotation.getReference() + " / Price: " + quotation.getPrice());
				}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
