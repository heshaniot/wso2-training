package StAXParser;

import java.io.File;
import java.io.FileInputStream;


//JAXP
import javax.xml.XMLConstants;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;


public class StAXValidator {

  public static void main(String[] args) {
      try {
          if (args.length != 2) {
              System.err.println ("Usage: java StAXValidator [filename.xml filename.xsd]");
              System.exit (1);
          }

          // Get StAX Parser Factory and reader
	      XMLStreamReader reader =XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(args[0]));
          
	      SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	      Schema schema = factory.newSchema(new File(args[1]));
	      
	      //Get validator
	      Validator validator = schema.newValidator();
	      
          // Validate the XML
          try {
        	  validator.validate(new StAXSource(reader));
              System.out.println("Document validates fine.");
          } catch (org.xml.sax.SAXException e) {
              System.out.println("Validation error: " + e.getMessage());
          }
                     
      } catch (FactoryConfigurationError e) {
          System.out.println("Error occurred obtaining StAX Parser Factory.");
      } catch (Exception e) {
          e.printStackTrace();
  }
  }
}

  