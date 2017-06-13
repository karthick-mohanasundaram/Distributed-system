package vetting;

import javax.jws.WebMethod;
import javax.jws.WebService;
import core.ClientInfo;

/**
 * define service and operation for vetting service.
 */

@WebService
public interface VettingService {
	@WebMethod public boolean vetClient(ClientInfo info);
}
