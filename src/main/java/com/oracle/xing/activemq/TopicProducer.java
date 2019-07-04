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
public class TopicProducer {

    public static void main(String...args)throws Exception{
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection
                .createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test_topic");
        MessageProducer producer = session.createProducer(topic);
        TextMessage message = session.createTextMessage("为什么会需要消息队列(MQ)？-->topic");
        producer.send(message);
        producer.close();
        session.close();
        connection.close();
    }
}
