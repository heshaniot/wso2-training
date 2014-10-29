package StAXParser;


import java.util.List;
import java.util.ArrayList;
//import java.io.File;
import java.io.FileInputStream;


// JAXP
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class MenuStAXParsing {
	public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.err.println ("Usage: java MenuStAXParsing [filename]");
                System.exit (1);
            }
	    
            //create Food list
            List<FoodItem> foodList = null;
	    	FoodItem currfood = null;
	    	
	    	//to extract tag content
	    	String tagContent = null;
	    	
            // Get StAX Parser Factory
	    	XMLInputFactory factory = XMLInputFactory.newInstance();
	    	XMLStreamReader reader =factory.createXMLStreamReader(new FileInputStream(args[0]));
	    	
	    	while(reader.hasNext()){
	    		int event = reader.next();
	    		switch(event){
	    		
	    		case XMLStreamConstants.START_ELEMENT:
	    			if ("food".equals(reader.getLocalName())){
	    				currfood = new FoodItem();
	    				}
	    			if("breakfast_menu".equals(reader.getLocalName())){
	    				foodList = new ArrayList<>();
	    				}
	    			break;
		
	    		case XMLStreamConstants.CHARACTERS:
	    			tagContent = reader.getText().trim();
	    			break;

	    		case XMLStreamConstants.END_ELEMENT:
	    			switch(reader.getLocalName()){
	    			
	    			case "food":
	    				foodList.add(currfood);
	    				break;

	    			case "name":
	    				currfood.setName(tagContent);;
	    				break;
			
	    			case "price":
	    				currfood.setPrice(tagContent);
	    				break;
				
	    			case "description":
	    				currfood.setDescription(tagContent);
	    				break;
				
	    			case "calories":
	    				currfood.setCalories(Integer.parseInt(tagContent));
	    				break;
	    			}
					
	    		}
	    	}
                                   
            // Turn on validation, and turn off namespaces
            //factory.setValidating(true);
            //factory.setNamespaceAware(false);
                                  
            //print Menu
            System.out.println("\n\n~~~~~~~~~~~MENU~~~~~~~~~~\n\n");
            //print food information
            for(FoodItem food : foodList)
                System.out.println(food);
            
        } catch (FactoryConfigurationError e) {
            System.out.println("Error occurred obtaining StAX Parser Factory.");
        } catch (Exception e) {
            e.printStackTrace();
        }
 }
	}






