package impl;

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

public class WSBrokerService implements BrokerService
{
	
	public List<Quotation> getQuotations(ClientInfo info)
	{
		List<Quotation> quotations=new LinkedList<Quotation>();
		try
		{
		//Creates URL for AFQService
        URL afqUrl = new 
        		URL("http://localhost:9001/QuotationService/generateQuotation?wsdl");
            
        QName qname = new QName("http://quotation/", "AFQServiceService");

        Service service = Service.create(afqUrl, qname);

        QuotationService AFQService = service.getPort(QuotationService.class);

        
        //Creates URL for DDQService
        URL ddqUrl = new
                    URL("http://localhost:9002/QuotationService/generateQuotation?wsdl");
        QName qname2 = new QName("http://quotation/", "DDQServiceService");

        Service service2 = Service.create(ddqUrl, qname2);

        QuotationService DDQService = service2.getPort(QuotationService.class);
        
        
        //Creates URL for GPQService
        URL gpqUrl = new
        		URL("http://localhost:9003/QuotationService/generateQuotation?wsdl");
        
        QName qname3 = new QName("http://quotation/", "GPQServiceService");

        Service service3 = Service.create(gpqUrl, qname3);

        QuotationService GPQService = service3.getPort(QuotationService.class);

        //Creates URL for Vetting Service           
        URL vUrl = new
        		URL("http://localhost:9000/VettingService/vetClient?wsdl");
        
        QName qname0 = new QName("http://vetting/", "LocalVettingServiceService");

        Service service0 = Service.create(vUrl, qname0);

        VettingService LocalVettingService = service0.getPort(VettingService.class);
			
        if(LocalVettingService.vetClient(info))
            {
            	quotations.add(AFQService.generateQuotation(info));
            	quotations.add(DDQService.generateQuotation(info));
            	quotations.add(GPQService.generateQuotation(info));
            }
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return quotations;
	
		}


}
