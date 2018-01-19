/**
 * 
 */
package com.camel.ride.cbr;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author meissa
 * Cette route implemente un ContentBasedRouting de base qui depose les fichiers à partir du
 * repertoire in en fonction de leur extension. 
 *
 */
public class JavaRouteBuilder extends RouteBuilder
{

	
	@Override
	public void configure() throws Exception
	{
		from("file:in?noop=true")
		.log("START ROUTING CBR")
		.choice()
			.when(header("CamelFileNameOnly").endsWith(".txt"))
				.to("file:txt")
			.when(header("CamelFileNameOnly").endsWith("xml"))
				.to("file:xml")
			.otherwise()
				.to("file:errors");
					

	}

}
