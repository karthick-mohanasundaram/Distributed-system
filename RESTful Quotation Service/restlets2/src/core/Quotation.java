package core;

/**
 * Interface to define the data to be stored in Quotation objects.
 */
public class Quotation {
	public Quotation(String reference, ClientInfo clientInfo, double price) {
		this.reference = reference;
		this.clientInfo = clientInfo;
		this.price = price;
		
	}
	public String reference;
	public ClientInfo clientInfo;
	public double price;
}
