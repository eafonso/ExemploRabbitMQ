package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmissorExchange {

  private final static String QUEUE_NAME = "minha-fila";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("3.227.180.238"); // Alterar
    factory.setUsername("admin"); // Alterar
    factory.setPassword("password"); // Alterar
    factory.setVirtualHost("/");    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    String messageImpar = "Olá Impar!";
                    //  (exchange, routingKey, props, message-body             ); 
    channel.basicPublish("Exchange 01", "", null,  messageImpar.getBytes("UTF-8"));
    System.out.println(" [x] Mensagem enviada: '" + messageImpar + "'");
    
    String messageParA = "Olá Par A!";
                    //  (exchange, routingKey, props, message-body             ); 
    channel.basicPublish("Exchange 02", "A", null,  messageParA.getBytes("UTF-8"));
    System.out.println(" [x] Mensagem enviada: '" + messageParA + "'");
    
    String messageParB = "Olá Par B!";
                    //  (exchange, routingKey, props, message-body             ); 
    channel.basicPublish("Exchange 02", "B", null,  messageParB.getBytes("UTF-8"));
    System.out.println(" [x] Mensagem enviada: '" + messageParB + "'");

    channel.close();
    connection.close();
  }
}