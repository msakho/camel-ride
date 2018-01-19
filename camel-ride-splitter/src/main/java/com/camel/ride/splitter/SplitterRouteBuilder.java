/**
 * 
 */
package com.camel.ride.splitter;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author meissa
 * Cette Route est une simple implementation du pattern splitter. Il va scanner les fichiers du repertoire 
 * fourni en entrée et decouper (le split) le contenu des fichier en ligne pour ensuite les
 * envoyer dans une file jms.
 */
public class SplitterRouteBuilder extends RouteBuilder
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
		.split().tokenize("\n")
		.parallelProcessing()
		.to("log:com.camel.ride?showAll=true")
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

	public String getInputDir()
	{
		return inputDir;
	}

	public String getOutputQueue()
	{
		return outputQueue;
	}
	

}
