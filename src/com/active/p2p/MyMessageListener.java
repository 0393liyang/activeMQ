package com.active.p2p;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {

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
