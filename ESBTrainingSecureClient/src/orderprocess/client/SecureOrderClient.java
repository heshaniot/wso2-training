package orderprocess.client;


import java.util.Iterator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.neethi.Policy;
import org.apache.neethi.PolicyEngine;
import org.apache.rampart.RampartMessageData;

import java.io.File;

public class SecureOrderClient {
	
	private static EndpointReference targetEPR = 
			//new EndpointReference("http://localhost:9000/services/OrderProcessingService");
			new EndpointReference("http://heshani-ThinkPad-T540p:8280/services/SecOrderProxy");
	static OMFactory fac = OMAbstractFactory.getOMFactory();
    static OMNamespace omNs = fac.createOMNamespace("http://orderprocessingservice.com","ns");
    

    	public static OMElement addOrderPayload(String itemname, double price, int quantity) {
	        
	        OMElement method = fac.createOMElement("addOrder", omNs);
	        
	        OMElement value1 = fac.createOMElement("itemname", omNs);
	        value1.addChild(fac.createOMText(value1, itemname));
	        method.addChild(value1);
	        
	        OMElement value2 = fac.createOMElement("price", omNs);
	        value2.addChild(fac.createOMText(value2, Double.toString(price)));
	        method.addChild(value2);

	        OMElement value3 = fac.createOMElement("quantity", omNs);
	        value3.addChild(fac.createOMText(value3,Integer.toString(quantity)));
	        method.addChild(value3);
	        
	        System.out.println(method);
	        
	        return method;
	    }
        
        public static OMElement getPricePayload(String itemname) {
	        OMElement method = fac.createOMElement("getPrice", omNs);
	        OMElement value = fac.createOMElement("itemname", omNs);
	        value.addChild(fac.createOMText(value, itemname));
	        method.addChild(value);
	        System.out.println(method);
	        return method;
	    }
        
        public static OMElement getQuantityPayload(String itemname) {
	        OMElement method = fac.createOMElement("getQuantity", omNs);
	        OMElement value = fac.createOMElement("itemname", omNs);
	        value.addChild(fac.createOMText(value, itemname));
	        method.addChild(value);
	        System.out.println(method);
	        return method;
	    }
        
        public static OMElement getOrdersPayload(String itemname) {
	        OMElement method = fac.createOMElement("getOrders", omNs);
	        OMElement value = fac.createOMElement("itemname", omNs);
	        value.addChild(fac.createOMText(value, itemname));
	        method.addChild(value);
	        System.out.println(method);
	        return method;
	    }
        
        private static Policy loadPolicy(String xmlPath) throws Exception {
            StAXOMBuilder builder = new StAXOMBuilder(xmlPath);
            return PolicyEngine.getPolicy(builder.getDocumentElement());
        }

	    public static void main(String[] args) {
	        try {
	        	
	        	String[] orderType = {"Books","Pencils","Pens","Rulers","Markers","Chalk"};
	        	Double[] orderPrice = {23.50, 5.75, 7.25, 5.90, 12.50, 2.50};
	        	int[] orderQty = {123, 357, 450, 234,345,679};
       	
       	        Options options = new Options();
	            options.setTo(targetEPR);
	            options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
          
	            ServiceClient sender = new ServiceClient();
	          
	            
	            ConfigurationContext configContext = null;
	            
	            String svcPolicy ="policy.xml";  
	            String repo = "client_repo";

	            //////////////////////////////////////////////////////////////
	        	if (repo != null && !"null".equals(repo)) {
	                configContext =
	                        ConfigurationContextFactory.
	                                createConfigurationContextFromFileSystem(repo,
	                                        repo + File.separator + "conf" + File.separator + "axis2.xml");
	                sender = new ServiceClient(configContext, null);
	            } else {
	                sender = new ServiceClient();
	            }
	        	
	        	sender.setOptions(options);
	            
	            
	        	///////////////////////////////////////////////////////////////////
	            // apply any service policies if any
	            if (svcPolicy != null && !"null".equals(svcPolicy) && svcPolicy.length() > 0) {
	                System.out.println("Using WS-Security");
	                sender.engageModule("addressing");
	                sender.engageModule("rampart");
	                options.setProperty(
	                        RampartMessageData.KEY_RAMPART_POLICY, loadPolicy(svcPolicy));
	            }
	            
	            ////////////////////////////////////////////////////////////////////////////
	            
	            
	        	for(int i=0 ; i<6 ; i++){
	        		OMElement addOrderPayload = addOrderPayload(orderType[i],orderPrice[i],orderQty[i]);
	        		
	        		sender.fireAndForget(addOrderPayload);
	  	            System.err.println("Order "+ (i+1) + " added");
	  	            Thread.sleep(3000);
	        	}
	        	//OMElement addOrderPayload1 = addOrderPayload("Books", 123);
	        	//OMElement addOrderPayload2 = addOrderPayload("Pens", 550);
	        	//OMElement addOrderPayload3 = addOrderPayload("Pencils", 450);
	        	OMElement getQuantityPayload = getQuantityPayload("Books");
	        	OMElement getPricePayload = getPricePayload("Books");
	        	OMElement getOrdersPayload = getOrdersPayload("Pencils");
	            

	           // sender.fireAndForget(addOrderPayload1);
	            //System.err.println("Order1 added");
	            //Thread.sleep(3000);
	        	OMElement result0 = sender.sendReceive(getPricePayload);
	            String response0 = result0.getFirstElement().getText();
	            System.err.println("Price of Books: " + response0);
	           	            
	            OMElement result1 = sender.sendReceive(getQuantityPayload);
	            String response1 = result1.getFirstElement().getText();
	            System.err.println("Quantity from Books: " + response1);
	            
	            OMElement result2 = sender.sendReceive(getOrdersPayload);
	            
	            System.err.println("Current Orders:");
	            System.err.println("Type        Price        Quantity"); 
	            	            
	            
				@SuppressWarnings("rawtypes")
				Iterator it = result2.getChildElements();
	            //OMElement order = result2.getFirstElement(); 
	            while(it.hasNext()){
	            	OMElement order = (OMElement) it.next();
	            	
	            	//OMElement child = order.getFirstElement();
	            	//System.err.println(child.getText());
	            	//OMElement symbol = order.getFirstChildWithName(new QName(omNs,"symbol"));
	            	//OMElement quantity = order.getFirstChildWithName(new QName(omNs,"quantity"));
	    	       	 
	            	@SuppressWarnings("rawtypes")
					Iterator it2 = order.getChildElements();
	            	
	            	while(it2.hasNext()){
	            		OMElement element = (OMElement) it2.next();
	            		System.err.print(element.getText()+"        ");
	            		
	            	}
	            	System.err.print("\n");
	            }
	            
	            
	            
	            //String response2 = result2.getFirstElement().getText();
	            //System.err.println("Current Orders: " + response2);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
}