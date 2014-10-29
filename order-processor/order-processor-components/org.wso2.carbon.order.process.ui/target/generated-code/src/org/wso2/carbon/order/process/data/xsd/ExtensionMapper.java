
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Apr 16, 2014 (01:16:09 UTC)
 */

        
            package org.wso2.carbon.order.process.data.xsd;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://process.order.carbon.wso2.org".equals(namespaceURI) &&
                  "Exception".equals(typeName)){
                   
                            return  org.wso2.carbon.order.process.ui.Exception.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://data.process.order.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "Item".equals(typeName)){
                   
                            return  org.wso2.carbon.order.process.data.xsd.Item.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    