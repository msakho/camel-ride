/**
 * 
 */
package com.camel.ride.simple.blueprint.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author meissa
 *
 */
public class JavaRouteBuilder extends RouteBuilder
{
	private String inputDir;
	private String outputQueue;

	public String getInputDir()
	{
		return inputDir;
	}

	public void setInputDir(String inputDir)
	{
		this.inputDir = inputDir;
	}

	public String getOutputQueue()
	{
		return outputQueue;
	}

	public void setOutputQueue(String outputQueue)
	{
		this.outputQueue = outputQueue;
	}

	/* (non-Javadoc)
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception
	{
		from("file:"+inputDir)
		.log("Receiving ****message: ${body}")
        .to("jms:"+outputQueue);

	}

}
