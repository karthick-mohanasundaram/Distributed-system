package core;
import java.rmi.RemoteException;

/**
 * Interface to define the behaviour of a quotation service.
 */
public interface QuotationService extends java.rmi.Remote 
{
	public Quotation generateQuotation(ClientInfo info) throws RemoteException;
}
