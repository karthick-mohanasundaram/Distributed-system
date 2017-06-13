package core;

/**
 * Interface defining the expected behavour of a vetting service.
 */
public interface VettingService {
	public boolean vetClient(ClientInfo info);
}
