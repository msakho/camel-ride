/**
 * 
 */
package com.camel.ride.aggregate;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * @author meissa
 *
 */
public class BodyAggregationStrategy implements AggregationStrategy
{

	/* (non-Javadoc)
	 * @see org.apache.camel.processor.aggregate.AggregationStrategy#aggregate(org.apache.camel.Exchange, org.apache.camel.Exchange)
	 */
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange)
	{
		if(oldExchange==null) return newExchange;
		String oldBody=oldExchange.getIn().getBody(String.class);
		String newBody=newExchange.getIn().getBody(String.class);
		newBody=newBody.concat(oldBody);
		newExchange.getIn().setBody(newBody);
		
		return newExchange;
	}

}
