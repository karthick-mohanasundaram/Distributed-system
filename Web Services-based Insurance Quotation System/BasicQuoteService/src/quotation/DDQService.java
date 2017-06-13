package quotation;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import core.AbstractQuotationService;
import core.ClientInfo;
import core.Quotation;

/**
 * Implementation of Quotation Service for Dodgy Drivers Insurance Company
 */

//Creating DDQ Web service
@WebService(name="QuotationService")
@SOAPBinding(style = Style.RPC, use=Use.LITERAL)

public class DDQService extends AbstractQuotationService implements QuotationService 
{
	
	// All references are to be prefixed with an DD (e.g. DD001000)
	public static final String PREFIX = "DD";
	
	/**
	 * Implementation of the Quotation interface for the DodgyDrivers Service
	 */
		
	/**
	 * Quote generation:
	 * 5% discount per penalty point (3 points required for qualification)
	 * 50% penalty for <= 3 penalty points
	 * 10% discount per year no claims
	 */
	@Override
	public Quotation generateQuotation(ClientInfo info) {
		// Create an initial quotation between 800 and 1000
		double price = generatePrice(800, 200);
		
		// 5% discount per penalty point (3 points required for qualification)
		int discount = (info.getPoints() > 3) ? 5*info.getPoints():-50;
		
		// Add a no claims discount
		discount += getNoClaimsDiscount(info);
		
		// Generate the quotation and send it back
		Quotation q = new Quotation();
		
		// Use the reference generator to create a reference
		q.setReference(generateReference(PREFIX));
		q.setInfo(info);
		q.setPrice((price * (100-discount)) / 100);
		
		return q;
	}

	private int getNoClaimsDiscount(ClientInfo info) {
		return 10*info.getNoClaims();
	}

}
