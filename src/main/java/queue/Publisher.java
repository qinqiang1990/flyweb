package queue;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {
	public static void main(String[] args) throws JMSException {
		Scanner sc = new Scanner(System.in);

		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://127.0.0.1:61616");

		// Create a Connection
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// Create a Session
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue("TEST.FOO");
		// Destination destination =
		// session.createTopic("TEST.FOO");
		// Create a MessageProducer from the Session to the Topic or Queue
		MessageProducer producer = session.createProducer(destination);
		// producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);

		String text = sc.nextLine();

		while (!text.equals("quit")) {
			// Create a messages
			TextMessage message = session.createTextMessage(text);
			// Tell the producer to send the message
			producer.send(message);

			text = sc.nextLine();

		}

		TextMessage message = session.createTextMessage(text);
		// Tell the producer to send the message
		producer.send(message);
		// Clean up
		session.close();
		connection.close();
	}
}
