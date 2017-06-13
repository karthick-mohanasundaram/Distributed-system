package test;

import core.ClientInfo;
import core.Quotation;
import impl.DDQService;

public class DDQServiceTest 
{
	public static void main(String[] args) 
	{
		try 
		{
			DDQService service = new DDQService();
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
