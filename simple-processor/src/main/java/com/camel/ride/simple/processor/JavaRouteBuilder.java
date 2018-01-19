/**
 * 
 */
package com.camel.ride.simple.processor;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author meissa
 * Cette Route appelle le processeur qui transforme les fichiers du repertoire in en miniscule avant de les
 * rediriger vers la file outpoutQueue.
 * Le contenu de chaque message est affiché avant et aprés transformation
 *
 */
public class JavaRouteBuilder extends RouteBuilder
{
	private String inputDir;
	private String outputQueue;

	/* (non-Javadoc)
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception
	{
		from("file:"+inputDir)
		.log("**** Processing message: ${body}")
		.process(new SimpleProcessor())
		.log(" ****Sending processed message: ${body}")
		.to("jms:"+outputQueue);

	}

	public void setInputDir(String inputDir)
	{
		this.inputDir = inputDir;
	}

	public void setOutputQueue(String outputQueue)
	{
		this.outputQueue = outputQueue;
	}

}
