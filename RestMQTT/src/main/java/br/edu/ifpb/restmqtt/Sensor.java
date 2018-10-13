package br.edu.ifpb.restmqtt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.LinkedList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author laerton
 */
public class Sensor implements MqttCallback{
 
 private String nome;
 private String topic;
 private List<Integer> temperaturas = new LinkedList<>();
 private Integer ultima =0;

    public Sensor(String nome, String topic) {
        this.nome = nome;
        this.topic = topic;
    }

    public Sensor() {
    }

    public Integer getUltima() {
        return ultima;
    }

    public void setUltima(Integer ultima) {
        this.ultima = ultima;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

     
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Integer> getTemperaturas() {
        return temperaturas;
    }

    public void setTemperaturas(List<Integer> temperaturas) {
        this.temperaturas = temperaturas;
    }
   
    public void addTemp (Integer temp){
        this.temperaturas.add(temp);
    }
     @Override
     public void connectionLost(Throwable thrwbl) {
            System.out.println("ex = " + thrwbl);
     }

     @Override
     public void messageArrived(String topic, MqttMessage mm) throws Exception {
            byte[] bytes = mm.getPayload();
            setUltima(Integer.parseInt((new String(bytes))));
            addTemp(getUltima());
     }

     @Override
     public void deliveryComplete(IMqttDeliveryToken imdt) {
            //TODO
            System.out.println("deliveryComplete");
     }
   
    
    
 
}
