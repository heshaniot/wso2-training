package orderprocess.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMText;

public class OrderProcessingService {
	//Create a hash map of orders added	
	private HashMap<String, Item> orderlist = new HashMap<String, Item>(); 
	 
	public static final String OMnamespace = "http://orderprocessingservice.com";
	
	//create an OM factory
	OMFactory factory = OMAbstractFactory.getOMFactory();
	OMNamespace OMNamespaceObj = factory.createOMNamespace(OMnamespace, "ns");
	
	 
	public void addOrder(OMElement element) throws XMLStreamException{
			//build the OM Element
			element.build();
			//Detach the OM Element
	        element.detach();
	        
	        //Create OMElements for item, price and quantity
	        OMElement itemname = element.getFirstChildWithName(new QName(OMnamespace, "itemname"));
	        OMElement price = element.getFirstChildWithName(new QName(OMnamespace, "price"));
	        OMElement quantity = element.getFirstChildWithName(new QName(OMnamespace, "quantity"));
	       
	        Item item = new Item();
	        item.setName(itemname.getText());
	        item.setPrice(Double.parseDouble(price.getText()));
	        item.setQuantity(Integer.parseInt(quantity.getText()));
	        
	        //Enter new order to the hash map
	        orderlist.put(itemname.getText(),item);
	        
	        System.out.println("Order Added. [item=" + itemname.getText() + ", Price=" + price.getText() 
	                           + ", Quantity=" + quantity.getText() +"]");
	      }

	
    public OMElement getPrice(OMElement element)throws XMLStreamException{
    	
    	//build the element
    	element.build();
    	//detach the element
    	element.detach();

       OMElement itemname = element.getFirstChildWithName(new QName(OMnamespace, "itemname"));
       
       String returnPrice = "404.00";
       //search for the key with item and extract it's value
       if (orderlist.containsKey(itemname.getText())){
         Double price = orderlist.get(itemname.getText()).getPrice();
         returnPrice = Double.toString(price);
       }

       OMElement root = factory.createOMElement("returnPrice", OMNamespaceObj);
       OMElement value = factory.createOMElement("value", OMNamespaceObj);
       OMText resultText = factory.createOMText(value, returnPrice);
       
       value.addChild(resultText);
       root.addChild(value);
       
       System.out.println("Price Retrived. [item=" + itemname.getText() + ", Price=" + value.getText() +"]");
       
       return root;
    }
	
	public OMElement getQuantity(OMElement element)throws XMLStreamException{
	    	//build the element
	    	element.build();
	    	//detach the element
	        element.detach();

	        OMElement itemname = element.getFirstChildWithName(new QName(OMnamespace, "itemname"));
	        
	        String returnQuantity = "404";
	        //search for the key with item and extract it's value
	        if (orderlist.containsKey(itemname.getText())){
	          int quantity = orderlist.get(itemname.getText()).getQuantity();
	          returnQuantity = Integer.toString(quantity);
	        }

	        OMElement root = factory.createOMElement("returnQuantity", OMNamespaceObj);
	        OMElement value = factory.createOMElement("value", OMNamespaceObj);
	        OMText resultText = factory.createOMText(value, returnQuantity);
	        
	        value.addChild(resultText);
	        root.addChild(value);
	        
	        System.out.println("Quantity Retrived. [item=" + itemname.getText() + ", Quantity=" + value.getText() +"]");
	        
	        return root;
	     }

	     public OMElement getOrders(OMElement element)throws XMLStreamException{
	    	//build and Detach the OM Element 
	    	element.build();
	        element.detach();
	        
	        //create and iterator to go through the hashmap
	        Iterator<Entry<String, Item>> it = orderlist.entrySet().iterator();
	  
	        OMElement root = factory.createOMElement("OrderQueryResult", OMNamespaceObj);
	  
	        while (it.hasNext()) {
	        	Entry<String, Item> pair = it.next();
	         
	        	OMElement order = factory.createOMElement("Order", OMNamespaceObj);
	        	OMElement itemname = factory.createOMElement("itemname", OMNamespaceObj);
	        	OMElement price = factory.createOMElement("price", OMNamespaceObj);
	        	OMElement quantity = factory.createOMElement("quantity", OMNamespaceObj);
	         
	        	OMText itemnameText = factory.createOMText(itemname, pair.getKey());
	        	OMText priceText = factory.createOMText(quantity, Double.toString(pair.getValue().getPrice()));
	        	OMText quantityText = factory.createOMText(quantity, Double.toString(pair.getValue().getQuantity()));
	         
	        	itemname.addChild(itemnameText);
	        	quantity.addChild(quantityText);
	        	price.addChild(priceText);
	        	order.addChild(itemname);
	        	order.addChild(price);
	        	order.addChild(quantity);
	        	root.addChild(order);
	         
	        	System.out.println("Oder Retrived. [item=" + pair.getKey() + "Price=" + pair.getValue().getPrice()+", Quantity=" + pair.getValue().getQuantity()+"]");
	     }
	  return root;
	 }

	
	

}