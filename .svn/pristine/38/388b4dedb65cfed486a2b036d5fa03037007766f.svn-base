package SAXParser;

import java.io.File;


//JAXP


import javax.xml.XMLConstants;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;


public class SAXValidator {

  public static void main(String[] args) {
      try {
          if (args.length != 2) {
              System.err.println ("Usage: java SAXValidator [filename.xml filename.xsd]");
              System.exit (1);
          }

          // Get SAX Parser Factory
          SAXParserFactory factory = SAXParserFactory.newInstance();
          
          // Leave off validation, and turn off namespaces
          factory.setValidating(false);
          factory.setNamespaceAware(false);
          
                 
          // Handle validation
          SchemaFactory constraintFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                
          Source constraints = new StreamSource(new File(args[1]));
          Schema schema = constraintFactory.newSchema(constraints);
          Validator validator = schema.newValidator();

          // Validate the XML
          try {
              validator.validate(new StreamSource(args[0]));
              System.out.println("Document validates fine.");
          } catch (org.xml.sax.SAXException e) {
              System.out.println("Validation error: " + e.getMessage());
          }
                     
      } catch (FactoryConfigurationError e) {
          System.out.println("Error occurred obtaining SAX Parser Factory.");
      } catch (Exception e) {
          e.printStackTrace();
  }
  }
}

  