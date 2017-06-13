package impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import core.BrokerService;
import core.ClientInfo;
import core.Quotation;
import quotation.QuotationService;
import vetting.VettingService;

public class LocalBrokerService implements BrokerService 
{
	VettingService vettingService;
	List<QuotationService> services = new LinkedList<QuotationService>();

	public LocalBrokerService() throws MalformedURLException 
	{
		//creates a Service instance of vetting service
        vettingService = Service.create(
        		new URL("http://localhost:9000/VettingService/vetClient?wsdl"),
        		//Object instance that supports the specified service endpoint interface.
        		new QName("http://qvetting/", "LocalVettingServicee")).getPort(VettingService.class);
        
        //creates a Service instance of AFQ service
        services.add(Service.create(
        		new URL("http://localhost:9001/QuotationService/generateQuotation?wsdl"), 
        		new QName("http://quotation/", "AFQServiceService")).getPort(QuotationService.class));
        
        //creates a Service instance of DDQ service
        services.add(Service.create(
        		new URL("http://localhost:9002/QuotationService/generateQuotation?wsdl"), 
        		new QName("http://quotation/", "DDQServiceService")).getPort(QuotationService.class));
        
        //creates a Service instance of GPQ service
        services.add(Service.create(
        		new URL("http://localhost:9003/QuotationService/generateQuotation?wsdl"), 
        		new QName("http://quotation/", "GPQServiceService")).getPort(QuotationService.class));
    }
	
	@Override
	public List<Quotation> getQuotations(ClientInfo info) {
		List<Quotation> list = new LinkedList<Quotation>();
		if (vettingService.vetClient(info)) {
			for (QuotationService service : services) {
				list.add(service.generateQuotation(info));
			}
		}
		return list;
	}
	
}