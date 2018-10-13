/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.restmqtt;

import br.edu.ifpb.restmqtt.rest.ClientRest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
public class program {
    public static void main(String[] args) throws InterruptedException {
       
        
        new Thread(){
            @Override
            public void run(){
                while (true) {                    
                    System.err.println(ClientRest.ultimaTemp());
                    try { 
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
        
        while (true) {            
            ClientRest.ajusteTemp(50);
            System.out.println("Ajustado a maximo 50");
            Thread.sleep(5000); 
            ClientRest.ajusteTemp(20);
            System.out.println("Ajustado a maximo 20");
            Thread.sleep(5000);
            ClientRest.ajusteTemp(100);
            System.out.println("Ajustado a maximo 100");
            Thread.sleep(5000);
            ClientRest.desligaSensor();
            System.out.println("Desliga Sensor");
            Thread.sleep(5000);
            ClientRest.ligaSensor();
            System.out.println("liga Sensor");
            Thread.sleep(5000);
        }
    }
}
