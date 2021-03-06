package topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

/**
 * 与Queue不同的是，Topic实现的是发布/订阅模型，在下面的例子中， 启动2个消费者共同监听一个Topic，然后循环给这个Topic中发送多个消息。
 *
 */
public class TopicReceiverTest {
	public static void main(String[] args) {
		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory;
		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;
		// Session： 一个发送或接收消息的线程
		Session session;
		connectionFactory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");
		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
			session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
			Topic topic = new ActiveMQTopic("FirstTopic");
			MessageConsumer comsumer = session.createConsumer(topic);
			comsumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					TextMessage textMsg = (TextMessage) message;
					try {
						System.out.println("消费者1 接收：" + textMsg.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}

			});

			MessageConsumer comsumer2 = session.createConsumer(topic);
			comsumer2.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					TextMessage textMsg = (TextMessage) message;
					try {
						System.out.println("消费者2 接收：" + textMsg.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}