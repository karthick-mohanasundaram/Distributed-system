package test;

import core.ClientInfo;
import core.Quotation;
import impl.GPQService;

public class GPQServiceTest {
	public static void main(String[] args) {
		try 
		{
		GPQService service = new GPQService();
		for (ClientInfo info : Main.clients) 
		{
			Quotation quotation = service.generateQuotation(info);
			System.out.println("Reference: " + quotation.getReference() + " / Price: " + quotation.getPrice());
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
