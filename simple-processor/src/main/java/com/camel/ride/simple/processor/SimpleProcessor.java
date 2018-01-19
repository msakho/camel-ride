/**
 * 
 */
package com.camel.ride.simple.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * @author meissa
 *
 */
public class SimpleProcessor implements Processor
{

	/* (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception
	{
		String body=exchange.getIn().getBody(String.class);
		body=body.toLowerCase();
		exchange.getIn().setBody(body);
	}

}
