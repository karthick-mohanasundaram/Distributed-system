package core;
import java.rmi.RemoteException;

/**
 * Interface defining the expected behaviour of a vetting service.
 */
public interface VettingService extends java.rmi.Remote

{
	public boolean vetClient(ClientInfo info) throws RemoteException;
	
}
