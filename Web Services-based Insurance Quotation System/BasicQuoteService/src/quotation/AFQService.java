package quotation;

import javax.jws.WebService;
import core.AbstractQuotationService;
import core.ClientInfo;
import core.Quotation;

/**
 * Implementation of the AuldFellas insurance quotation service.
 */

//Creating AFQ Web service
@WebService(name="QuotationService")
public class AFQService extends AbstractQuotationService implements QuotationService 
{
	// All references are to be prefixed with an AF (e.g. AF001000)
	public static final String PREFIX = "AF";
	
	/**
	 * Quote generation:
	 * 30% discount for being male
	 * 2% discount per year over 60
	 * 20% discount for less than 3 penalty points
	 * 50% penalty (i.e. reduction in discount) for more than 60 penalty points 
	 */
	
	public Quotation generateQuotation(ClientInfo info) 
	{
		// Create an initial quotation between 600 and 1200
		double price = generatePrice(600, 600);
		
		// Automatic 30% discount for being male
		int discount = (info.getSex() == ClientInfo.MALE) ? 30:0;
		
		// Automatic 2% discount per year over 60...
		discount += (info.getAge() > 60) ? (2*(info.getAge()-60)) : 0;
		
		// Add a points discount
		discount += getPointsDiscount(info);
		
		// Generate the quotation and send it back
		Quotation q=new Quotation();
		q.setReference(generateReference(PREFIX));
		q.setInfo(info);
		q.setPrice((price * (100-discount)) / 100);
				
		return q;
	}

	private int getPointsDiscount(ClientInfo info) 
	{
		if (info.getPoints() < 3) return 20;
		if (info.getPoints() <= 6) return 0;
		return -50;
		
	}

}
