/**
 * 
 */
package com.camel.ride.recipient;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * @author meissa
 * Ce processor intercepte le nom du fichier pour determiner le bon destinataire du fichier.
 * La liste des destinataires qui ser transmis dans le header du message.
 *
 */
public class DestinationProcessor implements Processor
{

	/* (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception
	{
		System.out.println("Starting the Processor");
		String fileName=(String)exchange.getIn().getHeader("CamelFileNameOnly");
		String[] destination=fileName.split("-");
		String recipientList="";
		
		if ("cars".equals(destination[0]))
		{
			recipientList=recipientList.concat("file:cars");
			
		}
		else if ("motorcycles".equals(destination[0]))
		{
			recipientList=recipientList.concat("file:motorcycles");
			
		}
		
		else
		{
			recipientList=recipientList.concat("file:cars,file:motorcycles");
			
		}
		System.out.println("DESTINATIONS="+recipientList);
		exchange.getIn().getHeaders().put("destinations",recipientList);
			

	}

}
