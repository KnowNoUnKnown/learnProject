package com.oracle.xing.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * https://github.com/oraclexing
 * <p>
 *
 * @author stardust
 * @version 1.0.0
 */
public class TopicConsumer {

    public static void main(String...args)throws Exception{
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection
                .createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test_topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(message ->{
            try {
                TextMessage textMessage = (TextMessage)message;
                System.out.println(textMessage.getText());
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
