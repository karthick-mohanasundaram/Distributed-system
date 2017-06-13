package quotation;

/**
 * Interface to define the state to be stored in QuotationResponse objects
 */

public class QuotationResponse {
	public QuotationResponse(String reference,String url)
	{
		this.reference=reference;
		this.url=url;
	}
	public QuotationResponse()
	{
	
	}
	public String reference;
	public String url;

}
