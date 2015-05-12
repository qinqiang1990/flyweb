package queue;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerListener {

	protected static String text = "";

	public static void main(String[] args) throws JMSException {

		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://127.0.0.1:61616");

		// Create a Connection
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// connection.setExceptionListener(this);

		// Create a Session
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue("TEST.FOO");
		// Destination destination =session.createTopic("TEST.FOO");

		// Create a MessageConsumer from the Session to the Topic or Queue
		MessageConsumer consumer = session.createConsumer(destination);

		consumer.setMessageListener(new MessageListener() {

			public void onMessage(Message message) {
				// TODO Auto-generated method stub
				if (message instanceof TextMessage) {//
					TextMessage textMessage = (TextMessage) message;
					try {
						text = textMessage.getText();
						System.out.print(text);
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("Received: " + message);
				}
			}
		});
		consumer.close();//
		session.close();//
		connection.close();//

	}
}
