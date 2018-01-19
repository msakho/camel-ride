/**
 * 
 */
package com.camel.ride.aggregate;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author meissa
 * Cette route est une implementation du pattern Aggregator. Il s'appuie sur la valeur du header pout idention le correlateur des messages.
 * Ce header est valorisé par le Processor passé en paramétre. La stratégie d'aggrégation est effectuée par la classe BodyAggregationStrategy
 */
public class AggregatorRouteBuilder extends RouteBuilder
{

	@Override
	public void configure() throws Exception
	{
		from("file:in?noop=true")
		.log("**** STARTING AggregatorRouteBuilder: ${body}")
		.to("log:com.camel.ride.aggregate?showAll=true")
		.process(new DestinationProcessor())
		.aggregate(header("customer"), new BodyAggregationStrategy())
		.completionSize(5)
		.to("file:out");
		
	}

}
