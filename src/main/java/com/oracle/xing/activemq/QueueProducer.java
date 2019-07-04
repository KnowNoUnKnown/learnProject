package com.oracle.xing.activemq;

import com.oracle.xing.model.User;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.*;

/**
 * https://github.com/oraclexing
 * <p>
 *
 * @author stardust
 * @version 1.0.0
 */
public class QueueProducer {

    public static void main(String... args)throws Exception{
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection
                .createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test_queue");
        MessageProducer producer = session.createProducer(queue);
        TextMessage message = session.createTextMessage("为什么会需要消息队列(MQ)？");
        producer.send(message);
        ActiveMQObjectMessage objectMessage = new ActiveMQObjectMessage();
        objectMessage.setObject(new User("123","oracle",'a'));
        producer.send(objectMessage);
        producer.close();
        session.close();
        connection.close();

    }
}
