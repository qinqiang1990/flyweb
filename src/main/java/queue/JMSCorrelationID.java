package queue;

import javax.jms.Session;

public class JMSCorrelationID {
		//创建一个消费者，它只接受属于它自己的消息 
	  /* MessageConsumer consumer = session.createConsumer(queue, 
	           "receiver='" + name + "'"); 
	                   consumer.setMessageListener(new MessageListener(){ 
	                       public void onMessage(Message m) { 
	                           try { 
	                               MessageProducer producer = 
	           session.createProducer(queue); 
	                               System.out.println(name + " get:" + 
	           ((TextMessage)m).getText()); 
	                               //回复一个消息 
	                               Message replyMessage = 
	           session.createTextMessage("Reply from " + name); 
	                               //设置JMSCorrelationID为刚才收到的消息的ID 
	           replyMessage.setJMSCorrelationID(m.getJMSMessageID()); 
	                               producer.send(replyMessage); 
	                           } catch (JMSException e) { } 
	                       } 
	                   }); 
	                   
	                   
	                   
	                   MessageProducer producer = session.createProducer(queue); 
	                   producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT); 
	                   //创建一个消息，并设置一个属性receiver，为消费者的名字。 
	                   Message message = session.createTextMessage("Message from " 
	           + name); 
	                   message.setStringProperty("receiver", consumerName); 
	                   producer.send(message); 
	                   //等待回复的消息 
	                   MessageConsumer replyConsumer = 
	           session.createConsumer(queue, "JMSCorrelationID='" + 
	           message.getJMSMessageID() + "'"); 
	                   replyConsumer.setMessageListener(new MessageListener(){ 
	                       public void onMessage(Message m) { 
	                           try { 
	                               System.out.println(name + " get reply:" + 
	           ((TextMessage)m).getText()); 
	                           } catch (JMSException e) { } 
	                       } 
	                   }); */
}
