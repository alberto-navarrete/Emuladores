package com.stefanini.emuladoresFG;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumerEmulador {

	private static String url = "tcp://localhost:61616";
	private static String queueName = "pruebas_queue";

	
    public static void main(String[] args) throws JMSException {
    	System.out.println("CONSUMER -- Inicia");
        // create connection factory
    	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", url);
        // create connection
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // create session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        System.out.println("CONSUMER -- Conectado");
        // create destination
        Destination destination = session.createQueue(queueName);
        // create consumer
        MessageConsumer consumer = session.createConsumer(destination);
        // receive message
        Message message = consumer.receive();
        // process message
        if (message instanceof TextMessage) {
            String text = ((TextMessage) message).getText();
            System.out.println("Received message: " + text);
        }
        // clean up resources
        session.close();
        connection.close();
        
        System.out.println("CONSUMER -- TERMINA");
    }
    
    
	public static void main2(String[] args) {
		try {
			System.out.println("Inicia");
			// create connection factory
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", url);
			// create connection
			Connection connection;

			connection = connectionFactory.createConnection();

			connection.start();
			// create session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// create destination
			Destination destination = session.createQueue(queueName);
			// create producer
			MessageProducer producer = session.createProducer(destination);
			// create message
			TextMessage message = session.createTextMessage("Hello, world!");
			// send message
			producer.send(message);
			// clean up resources
			session.close();
			connection.close();
			System.out.println("DONE!");

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}
}