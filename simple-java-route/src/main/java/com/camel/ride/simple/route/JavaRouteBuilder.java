/**
 * 
 */
package com.camel.ride.simple.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author meissa
 *Cette Route camel est responsable de l'envoie des fichiers du repertoire in vers la file jms orders
 */
public class JavaRouteBuilder extends RouteBuilder
{

	/* (non-Javadoc)
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception
	{
		from("file:in?noop=true")
		.to("jms:queue:orders");

	}

}
