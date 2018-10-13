/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.restmqtt.service;

import br.edu.ifpb.restmqtt.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author laerton
 */
@Stateless
public class ServiceMQTT {
    
    @Inject
    private Client client;
    
    public List<Integer> historicoTemperaturas(){
        return client.getSensor().getTemperaturas();
    }
    
    public Integer ultimaTemperatura(){
        return client.getSensor().getUltima();
    }
  
    
    public void ligaSensor(){
        client.ligar(true);
    }
    
    public void desligaSensor(){
        client.ligar(false);
    }
    
    public void ajustaTemp(int temp){
        client.ajustarTemperatura(temp);
    }
    
}
