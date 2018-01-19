/**
 * 
 */
package com.camel.ride.recipient;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author meissa
 * Cette route est une simple implementation du pattern Recipient. Elle envoie le message à plusieurs
 * destinataires en tenant compte des conditions de routage géré au niveau du processeur et encapsulé dans 
 * le header du message.
 *
 */
public class RecipientRouteBuilder extends RouteBuilder
{

	/* (non-Javadoc)
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception
	{
		
		from("file:in?noop=true")
		.log("STARTING ROUTING IN RecipientRouteBuilder")
		.process(new DestinationProcessor())
		.log("Receiving ****message: ${body}")
		.recipientList(header("destinations")).parallelProcessing();

	}

}
