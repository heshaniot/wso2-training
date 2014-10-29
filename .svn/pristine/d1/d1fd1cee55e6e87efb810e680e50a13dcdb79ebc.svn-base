package DOMParser;

import java.io.File;

//JAXP
import javax.xml.XMLConstants;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

//DOM
import org.w3c.dom.Document;

public class DOMValidator {

  public static void main(String[] args) {
      try {
          if (args.length != 2) {
              System.err.println ("Usage: java DOMValidator [filename.xml filename.xsd]");
              System.exit (1);
          }

          // Get Document Builder Factory
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

          // Leave off validation, and turn off namespaces
          factory.setValidating(false);
          factory.setNamespaceAware(false);

          DocumentBuilder builder = factory.newDocumentBuilder();
          Document document = builder.parse(new File(args[0]));
          
          // Handle validation
          SchemaFactory constraintFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
          Source constraints = new StreamSource(new File(args[1]));
          Schema schema = constraintFactory.newSchema(constraints);
          Validator validator = schema.newValidator();

          // Validate the DOM tree
          try {
              validator.validate(new DOMSource(document));
              System.out.println("Document validates fine.");
          } catch (Exception  e) {
              System.out.println("Validation error: " + e.getMessage());
          }
               
      } catch (ParserConfigurationException e) {
          System.out.println("The underlying parser does not support the requested features.");
      } catch (FactoryConfigurationError e) {
          System.out.println("Error occurred obtaining Document Builder Factory.");
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  
  
}

