package com.active.subcriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producter1 {
    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;//连接工厂
        Connection connection=null;//连接
        Session session=null;//会话
        Destination destination;//消息目的地
        MessageProducer messageProducer=null;//生产者
        try {
            connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
            connection=connectionFactory.createConnection();
            connection.start();//连接启动
            session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination=session.createTopic("短信发送T");
            messageProducer=session.createProducer(destination);
            for (int i=0;i<10;i++){
                TextMessage textMessage=session.createTextMessage("wangyinghh"+i);
                messageProducer.send(destination,textMessage);//消息以及属于哪个队列

            }
            session.commit();//关键步骤！要提交
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                messageProducer.close();
                session.close();
                connection.close();
            }catch (Exception e){

            }


        }
    }
}
