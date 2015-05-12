package topicpersistentselector;

import java.util.Timer;
import java.util.TimerTask;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
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
public class TopicSenderSelector {
	static Session session;
	static MessageProducer producer;
	private static int identify = 0;

	public static void main(String[] args) {
		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory;
		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;
		// Session： 一个发送或接收消息的线程
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
			// 创建一个生产者，然后发送多个消息。

			producer = session.createProducer(topic);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);

			TopicSenderSelector send = new TopicSenderSelector();
			Timer timer = new Timer();
			SenderTimerTask timerTask = send.new SenderTimerTask();
			timer.schedule(timerTask, 0, 5000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 定时任务调度
	 * 
	 * @author Administrator
	 * 
	 */
	class SenderTimerTask extends TimerTask {
		@Override
		public void run() {
			TextMessage message;
			try {
				message = session.createTextMessage("ActiveMq Topic发送的消息"
						+ identify);
				message.setStringProperty("receiver", identify%3+1+"");
				 
				// 发送消息到目的地方
				System.out.println("发送消息：" + "ActiveMq Topic发送的消息" + identify+",receiver="+identify%3+1);
				producer.send(message);
				identify++;
			} catch (JMSException e) {
				e.printStackTrace();
			}

		}
	}
}