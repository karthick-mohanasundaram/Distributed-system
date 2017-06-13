package quotation;

import javax.jws.WebMethod;
import javax.jws.WebService;

import core.ClientInfo;
import core.Quotation;

/**
 * define service and operation provided for quotation service.
 */

@WebService
public interface QuotationService {
	@WebMethod public Quotation generateQuotation(ClientInfo info);
	
	
	}
