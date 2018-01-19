/**
 * 
 */
package com.camel.ride.simple.route;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;



/**
 * @author meissa
 * Cette classe crée un contexte d'execution camel, définie un composant jms en memoire et ajoute la route 
 * <code>JavaRouteBuilder</code>et le composant jms dans le contexte camel qui et démarré pour une durée de 5 seconde.
 * 
 * La route va envoyer des messages dans la file "orders" qui sera lue dans cette classe pour afficher les messages
 * dans la sortie standard de la console.
 *
 */
public class JavaRouter
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
CamelContext context = new DefaultCamelContext();
		
		//définition d composant jms avec ActiveMq factory
		//ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
		//context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"vm://localhost");
		context.addComponent("jms",
				JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		
		
		context.addRoutes(new JavaRouteBuilder());
		context.start();
		Thread.sleep(5000);
		
		//Verrification de l'écriture des messages jms.
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination queue = session.createQueue("orders");
		MessageConsumer consumer = session.createConsumer(queue); 
		Message message = consumer.receive(10);
		while (message != null) {
			if (message instanceof BytesMessage) {
				byte[] bytes = new byte[20];
				((BytesMessage) message).readBytes(bytes);
				System.out.println("**********Got message: "
						+ new String(bytes));
			}
			message = consumer.receive(10);
		}
		consumer.close();
		session.close();
		connection.close();
		
		context.stop();
		
		

	}

}
