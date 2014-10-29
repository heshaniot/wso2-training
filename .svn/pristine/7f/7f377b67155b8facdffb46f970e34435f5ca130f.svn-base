package SAXParser;


import java.util.List;
import java.util.ArrayList;
import java.io.File;

// JAXP
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;


// SAX
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MenuSAXParsing {
	public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.err.println ("Usage: java MenuSAXParsing [filename]");
                System.exit (1);
            }
            
            // Get SAX Parser Factory
            SAXParserFactory factory = SAXParserFactory.newInstance();
            
            // Turn on validation, and turn off namespaces
            factory.setValidating(true);
            factory.setNamespaceAware(false);
            
            //Get SAX Parser
            SAXParser parser = factory.newSAXParser();
            
            //get the handler
            MyHandler handler = new MyHandler();
            
            //parse the document            
            parser.parse(new File(args[0]), handler);
            
            //Get Food list
            List<FoodItem> foodList = handler.getFoodList();
            
            //print Menu
            System.out.println("\n\n~~~~~~~~~~~MENU~~~~~~~~~~\n\n");
            //print food information
            for(FoodItem food : foodList)
                System.out.println(food);
            
        } catch (ParserConfigurationException e) {
            System.out.println("The underlying parser does not support " +
                               " the requested features.");
        } catch (FactoryConfigurationError e) {
            System.out.println("Error occurred obtaining SAX Parser Factory.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyHandler extends DefaultHandler {
    // SAX callback implementations from DocumentHandler, ErrorHandler, etc.
    
	//List to hold FoodItem object
    private List<FoodItem> foodList = null;
    private FoodItem food = null;
 
     //getter method FoodItem list
    public List<FoodItem> getFoodList() {
        return foodList;
    }
 
    //create boolean values for fields, will be used in setting Food variable
    boolean bName = false;
    boolean bPrice = false;
    boolean bDescription = false;
    boolean bCalories = false;
    
    @Override
    public void startDocument() throws SAXException {
    	System.out.println("Parsing Begins..");
    	
    }
 
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
        if (qName.equalsIgnoreCase("food")) {

        	//create a new FoodItem and put it in Map
            food = new FoodItem();
           
            //initialize list
            if (foodList == null)
                foodList = new ArrayList<>();
        	} else if (qName.equalsIgnoreCase("name")) {
            //set boolean values for fields, will be used in setting Food variables
        		bName = true;
        	} else if (qName.equalsIgnoreCase("price")) {
        		bPrice = true;
        	} else if (qName.equalsIgnoreCase("description")) {
        		bDescription = true;
        	} else if (qName.equalsIgnoreCase("calories")) {
        		bCalories = true;
        	}
    }
 
 
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Food")) {
            //add FoodItem object to list
            foodList.add(food);
        }
    }
 
 
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
 
    	if (bName) {
            food.setName(new String(ch, start, length));
            bName = false;
        } else if (bPrice) {
            //price element, set price of the food
            food.setPrice(new String(ch, start, length));
            bPrice = false;
        } else if (bDescription) {
            food.setDescription(new String(ch, start, length));
            bDescription = false;
        } else if (bCalories) {
            food.setCalories(Integer.parseInt(new String(ch, start, length)));
            bCalories = false;
        }
    }
    
    @Override
    public void endDocument() throws SAXException {
    	System.out.println("Parsing End..");
    	
    }
}

 


