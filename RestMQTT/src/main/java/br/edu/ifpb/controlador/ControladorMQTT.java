/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.controlador;


import br.edu.ifpb.restmqtt.rest.ClientRest;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author laerton
 */
@Named
@SessionScoped
public class ControladorMQTT implements Serializable {
    
    private int ultimaTemp ;
    private int temMax ;
    

    public int getUltimaTemp() {
        return ultimaTemp;
    }

    public void setUltimaTemp(int ultimaTemp) {
        this.ultimaTemp = ultimaTemp;
    }

    public int getTemMax() {
        return temMax;
    }

    public void setTemMax(int temMax) {
        this.temMax = temMax;
    }

    
    public List<Integer> lerHistorico(){
        return ClientRest.historicoTemps();
    }
    
    public String ultimaTemp (){
        ultimaTemp= ClientRest.ultimaTemp();
        return null;
    }
    
    public String ligar(){
        ClientRest.ligaSensor();
        return null;
    }
    
    public String desligar(){
        ClientRest.desligaSensor();
        return null;
    }
    
    public void ajustarTemp(){
        ClientRest.ajusteTemp(temMax);
    }
    
    
}
