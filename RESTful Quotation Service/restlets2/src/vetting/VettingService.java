package vetting;

import core.ClientInfo;
import core.Service;

/**
 * Interface defining the expected behavour of a vetting service.
 */
public interface VettingService extends Service {
	public boolean vetClient(ClientInfo info);
}
