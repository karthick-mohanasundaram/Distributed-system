package impl;

import java.util.LinkedList;
import java.util.List;

import core.BrokerService;
import core.ClientInfo;
import core.Quotation;
import quotation.QuotationService;
import registry.ServiceRegistry;
import vetting.VettingService;

/**
 * Implementation of the broker service that uses the Service Registry.
 */
public class LocalBrokerService implements BrokerService {
	VettingService vettingService = (VettingService) ServiceRegistry.lookup("vs-VettingService");
	
	public List<Quotation> getQuotations(ClientInfo info) {
		List<Quotation> quotations = new LinkedList<Quotation>();
		
		if (vettingService.vetClient(info)) {
			for (String name : ServiceRegistry.list()) {
				if (name.startsWith("qs-")) {
					QuotationService service = (QuotationService) ServiceRegistry.lookup(name);
					quotations.add(service.generateQuotation(info));
				}
			}
		}
		return quotations;
	}
}
