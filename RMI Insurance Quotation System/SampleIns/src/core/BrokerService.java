package core;

//import java.rmi.RemoteException;
//import java.io.Serializable;
import java.util.List;

/**
 * Interface for defining the behaviours of the broker service
 */
public interface BrokerService
{
	public List<Quotation> getQuotations(ClientInfo info);
}
