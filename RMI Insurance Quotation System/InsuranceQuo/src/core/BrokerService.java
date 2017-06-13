package core;

import java.util.List;

/**
 * Interface for defining the behaviours of the broker service
 */
public interface BrokerService
{
	//public static final boolean array;
	//boolean result (ClientInfo info); //throws Exception;
	public List<Quotation> getQuotations(ClientInfo info);
	
}
