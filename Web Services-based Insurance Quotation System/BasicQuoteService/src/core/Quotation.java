package core;

/**
 * Data Beans class for quotation object
 */

public class Quotation 
{
		
	private String reference;
	private ClientInfo info;
	private double price;
	
	//constructor with arguments is used for initialising a newly created Quotation object. 
	public Quotation(String reference, ClientInfo info, double price)
	{
		this.reference=reference;
		this.info=info;
		this.price=price;
	}
	
	public Quotation()
	{
	}
	
	
	
	public void setReference(String reference)
	{
		this.reference = reference;
	}
	
	public String getReference()
	{
		return reference;
	}
	
	public void setInfo(ClientInfo info)
	{
		this.info = info;
	}
	
	public ClientInfo getInfo()
	{
		return info;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public double getPrice()
	{
		return price;
	}
}
