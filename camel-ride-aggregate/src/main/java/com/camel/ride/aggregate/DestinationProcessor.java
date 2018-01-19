/**
 * 
 */
package com.camel.ride.aggregate;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * @author meissa
 * Ce processor doit placer dans le hearder l'identifiant de correlateur des message en fonction
 * du nom du fichier.
 */
public class DestinationProcessor implements Processor
{

	/* (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception
	{
		String fileName=(String)exchange.getIn().getHeader("CamelFileNameOnly");
		String customer=null;
		if (fileName.startsWith("cars"))
		{
			customer="cars";
		}
		
		else if (fileName.startsWith("motorcycles"))
		{
			customer="motorcycles";
		}
		
		
		exchange.getIn().getHeaders().put("customer", customer);

	}

}
