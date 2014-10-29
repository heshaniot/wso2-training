

/**
 * OrderProcessor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

    package org.wso2.carbon.order.process.ui;

    /*
     *  OrderProcessor java interface
     */

    public interface OrderProcessor {
          

        /**
          * Auto generated method signature
          * 
                    * @param getOrders0
                
         */

         
                     public org.wso2.carbon.order.process.data.xsd.Item[] getOrders(

                        )
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getOrders0
            
          */
        public void startgetOrders(

            

            final org.wso2.carbon.order.process.ui.OrderProcessorCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
                 * @throws org.wso2.carbon.order.process.ui.OrderProcessorExceptionException : 
         */
        public void  deleteOrder(
         java.lang.String itemName4

        ) throws java.rmi.RemoteException
        
        
               ,org.wso2.carbon.order.process.ui.OrderProcessorExceptionException;

        
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
                 * @throws org.wso2.carbon.order.process.ui.OrderProcessorExceptionException : 
         */
        public void  addOrder(
         org.wso2.carbon.order.process.data.xsd.Item item6

        ) throws java.rmi.RemoteException
        
        
               ,org.wso2.carbon.order.process.ui.OrderProcessorExceptionException;

        

        
       //
       }
    