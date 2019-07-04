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
public class QueueConsumer {

    public static void main(String...args)throws Exception{
        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        connectionFactory.setTrustAllPackages(Boolean.TRUE);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection
                .createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test_queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(message -> {
            try {
                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println(textMessage.getText());
                }
                if(message instanceof ObjectMessage){
                    ActiveMQObjectMessage objectMessage = (ActiveMQObjectMessage)message;
                    User user = (User) objectMessage.getObject();
                    System.out.println(user.toString());
                }
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