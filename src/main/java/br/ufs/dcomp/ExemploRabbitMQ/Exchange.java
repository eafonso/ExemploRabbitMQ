package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Exchange {

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("3.227.180.238"); // Alterar
    factory.setUsername("admin"); // Alterar
    factory.setPassword("password"); // Alterar
    factory.setVirtualHost("/");    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    channel.exchangeDeclare("Exchange 01","fanout");
    channel.exchangeDeclare("Exchange 02","direct");
    Boolean A = true;
    for(int i = 1; i <= 10; i++){
      if(i%2 != 0) channel.queueBind("Fila " + (i > 9 ? i : "0"+i), "Exchange 01", "");
      else{
        channel.queueBind("Fila " + (i > 9 ? i : "0"+i), "Exchange 02", (A ? "A" : "B"));
        A = !A;
      }
    }


    channel.close();
    connection.close();
  }
}