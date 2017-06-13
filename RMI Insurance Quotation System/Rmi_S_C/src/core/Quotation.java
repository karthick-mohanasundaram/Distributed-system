package core;
import java.io.Serializable;
/**
 * Interface to define the data to be stored in Quotation objects
 */
public interface Quotation extends Serializable
{
	public String getReference();
	public ClientInfo getClientInfo();
	public double getPrice();
}
