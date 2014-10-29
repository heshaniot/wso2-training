package DOMParser;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

//JAXP
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

//DOM
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class MenuDOMParsing {

  public static void main(String[] args) {
      try {
          if (args.length != 1) {
              System.err.println ("Usage: java MenuDOMParsing [filename]");
              System.exit (1);
          }

          // Get Document Builder Factory
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

          // Leave off validation, and turn off namespaces
          factory.setValidating(false);
          factory.setNamespaceAware(false);

          DocumentBuilder builder = factory.newDocumentBuilder();
          Document document = builder.parse(new File(args[0]));
          
                  
          // Print the document from the DOM tree
          printNode(document);

      } catch (ParserConfigurationException e) {
          System.out.println("The underlying parser does not support the requested features.");
      } catch (FactoryConfigurationError e) {
          System.out.println("Error occurred obtaining Document Builder Factory.");
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  private static void printNode(Document document)  {
	  
	  //Create a list of Food Items
	  List<FoodItem> foodList = new ArrayList<>();
	  FoodItem food = null;
	  
	  //get the root node
	  String root = null;
	  root = document.getDocumentElement().getNodeName();
	  System.out.println(root+"\n");
	  
	  //create a list of food nodes
	  NodeList nodelist = document.getElementsByTagName("food");
	  	  
	  for(int i=0;i<nodelist.getLength();i++){
		  food = new FoodItem();
		  Node node = nodelist.item(i);
		  
		  //Create a list of information in one food Item
		  NodeList foodInfo = node.getChildNodes();
		  
		  for(int j=0; j<foodInfo.getLength() ; j++ ){
			  
			 Node info= foodInfo.item(j);
			 			     
			  if(info.getNodeType() == Node.ELEMENT_NODE){
				  
				  Element infoElem= (Element) info;
				  				    
				  //put the information in food to the foodItem object
				  switch(infoElem.getNodeName()){
				  
				  case "name":
					  food.setName(infoElem.getTextContent());
					  break;
					  
				  case "price":
					  food.setPrice(infoElem.getTextContent());
					  break;
					  
				  case "description":
					  food.setDescription(infoElem.getTextContent());
					  break;
					  
				  case "calories":
					  food.setCalories(Integer.parseInt(infoElem.getTextContent()));
					  break;
				  }
				  
			  }

		  }
		  //Add the foodItem to the foodList
		  foodList.add(food);
		       
	  }
	  //Print the food list
	  for(FoodItem food1 : foodList)
          System.out.println(food1);  
  }
  
}
