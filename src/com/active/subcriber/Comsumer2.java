package com.active.subcriber;

import com.active.p2p.MyMessageListener;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Comsumer2 {
    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;//连接工厂
        Connection connection=null;//连接
        Session session=null;//会话
        Destination destination;//消息目的地
        MessageConsumer messageConsumer=null;//消费者
        try {
            connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
            connection=connectionFactory.createConnection();
            connection.start();//连接启动
            session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination=session.createTopic("短信发送T");//queue继承了Destination
            messageConsumer=session.createConsumer(destination);

            //MQ监听器
            messageConsumer.setMessageListener(new MyMessageListener2());

            session.commit();//关键步骤！要提交
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class MyMessageListener2 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage= (TextMessage) message;

            System.out.println("从MQ获取的消息:"+textMessage.getText());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}