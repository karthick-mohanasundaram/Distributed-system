package quotation;


import org.restlet.Component;
import org.restlet.data.Protocol;

import vetting.LocalVettingService;

public class QuotationServer {
	public static void main(String[] args) throws Exception {
		Component component = new Component();
		//A Server class, which creates the applications on port 8182
		component.getServers().add(Protocol.HTTP, 8182);
		//routes the path “/vetting/{licenceNumber}” to the VettingRestlet.
		component.getDefaultHost(). attach("/vetting/{licenseNumber}",new VettingRestlet());
		//routes the path “/afq” to the QuotationApplication.
		component.getDefaultHost(). attach("/afq", new QuotationApplication(new AFQService()));
		//routes the path “/ddq” to the QuotationApplication.
		component.getDefaultHost(). attach("/ddq", new QuotationApplication(new DDQService()));
		//routes the path “/gpq” to the QuotationApplication.
		component.getDefaultHost(). attach("/gpq", new QuotationApplication(new GPQService()));
		component.start();
	}
	
}
