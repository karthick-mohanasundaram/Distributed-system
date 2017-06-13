package core;
import java.rmi.RemoteException;
//import java.rmi.AlreadyBoundException;
//import java.io.Serializable;

/**
 * Interface defining the expected behaviour of a vetting service.
 */
public interface VettingService extends java.rmi.Remote
//public interface VettingService extends Serializable
{
	public boolean vetClient(ClientInfo info) throws RemoteException;
	//public void vetClient(ClientInfo info) throws RemoteException;
	//public boolean vetClient(ClientInfo info);
}
