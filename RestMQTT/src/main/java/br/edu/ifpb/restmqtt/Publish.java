package br.edu.ifpb.restmqtt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author laerton
 */
public class Publish {
 
    public static void main(String[] args) throws InterruptedException {
        SensorArCondicionado();
    }

    private static void SensorArCondicionado() throws InterruptedException {
        String topic = "sensor/temperatura/";
        String content = "54";
        int qos = 2;
        String broker = "ws://iot.eclipse.org:80/ws";
        String clientId = "laerton";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
 
            while (true) {                
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Conectando ao broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Conectado");
            System.out.println("Publicando a mensagem: " + content);

                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(qos);
                sampleClient.publish(topic, message);
                System.out.println("Mensagem publicada");
                sampleClient.disconnect();
                System.out.println("Disconectado");
                //System.exit(0);
                Thread.sleep(2000);
            }
            /*for (int i = 0; i < 10; i++) {
                MqttMessage message = new MqttMessage(String.valueOf(i * 5).getBytes());
                message.setQos(qos);
                sampleClient.publish(topic, message);
            }*/
            
            
 
        } catch (MqttException me) {
            System.out.println("motivo " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("causa " + me.getCause());
            System.out.println("ex " + me);
            me.printStackTrace();
        }
    }
}
