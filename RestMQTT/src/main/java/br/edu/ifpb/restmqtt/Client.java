package br.edu.ifpb.restmqtt;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

/**
 *
 * @author laerton
 */
@Singleton
public class Client {

    private MqttClient client;
    private MqttConnectOptions connOpts;
    private Sensor sensor;
    
    public Client() {
        String topic = "sensor1/temperatura/#";
        String broker = "ws://iot.eclipse.org:80/ws";
        String clientId = "laerton";
        sensor = new Sensor("Sensor 1", "sensor1/temperatura/#");
        String tmpDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
        int qos = 2;        
        
        try {
            client = new MqttClient(broker, clientId, dataStore);
            connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Conectando ao broker: " + broker);
            client.setCallback(sensor);
            client.connect(connOpts);
            client.subscribe(topic, qos);
            System.out.println("Conectado");
        } catch (MqttException me) {
            me.printStackTrace();
        }
    }
    
    public void ligar(boolean ligar){
      MqttMessage mensage = new MqttMessage(((ligar)?"0":"1").getBytes());
      mensage.setQos(2);
      
        try {
            client.publish("atuador1/temperatura", mensage);
        } catch (MqttException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ajustarTemperatura(int temp){
      MqttMessage mensage = new MqttMessage((String.valueOf(temp)).getBytes());
      mensage.setQos(2);
      
        try {
            client.publish("ajuste/temperatura", mensage);
        } catch (MqttException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*static class ClienteCall implements MqttCallback {

        @Override
        public void connectionLost(Throwable thrwbl) {
            System.out.println("ex = " + thrwbl);
        }

        @Override
        public void messageArrived(String topic, MqttMessage mm) throws Exception {
            byte[] bytes = mm.getPayload();
            System.out.println("topic: "+topic);
            System.out.println("array transmitido: "+Arrays.toString(bytes));
            System.out.println("valor: "+new String(bytes));
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken imdt) {
            //TODO
            System.out.println("deliveryComplete");
        }
    }*/

    public  Sensor getSensor() {
        return sensor;
    }
    
}
