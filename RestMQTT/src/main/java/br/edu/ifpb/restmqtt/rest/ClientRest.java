/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.restmqtt.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
public class ClientRest {
private static final String urlSetting = "http://localhost:8080/RestMQTT/api/sensores/";
    
    /***
     * Exibi o histórico das temperaturas registradas no sistema
     * @return 
     */
    public static List<Integer> historicoTemps(){
        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + "historicoTemp/");
            Gson gson = new Gson();
            java.lang.reflect.Type usuariosListType = new TypeToken<ArrayList<Integer>>(){}.getType(); 
            List<Integer> lista =  gson.fromJson(responseBuffer,usuariosListType);
            return lista;
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new LinkedList<>();
    }
    
    public static void ligaSensor(){
        try {
            getMetodo(urlSetting + "ligaSensor/");
        } catch (IOException ex) {
            Logger.getLogger(ClientRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void desligaSensor(){
        try {
            getMetodo(urlSetting + "desligaSensor/");
        } catch (IOException ex) {
            Logger.getLogger(ClientRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ajusteTemp(int temp){
    try {
        BufferedReader responseBuffer = getMetodo(urlSetting + "ajusteTemp/" + temp);
    } catch (IOException ex) {
        Logger.getLogger(ClientRest.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    /***
     * Exibe a ultima temperatura do sensor
     * @return 
     */
    public static Integer ultimaTemp()
    {
        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + "ultimaTemp/");
            Gson gson = new Gson();
            java.lang.reflect.Type usuariosListType = new TypeToken<Integer>(){}.getType(); 
            Integer temp =  gson.fromJson(responseBuffer,usuariosListType);
            return temp;
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
     private static BufferedReader getMetodo(String urlcomple) throws MalformedURLException, IOException{
            URL url = new URL(urlcomple);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() !=200){
                throw new RuntimeException("HTTP GET erro code: "+ connection.getResponseCode());
            }
            return new BufferedReader(new InputStreamReader((connection.getInputStream())));
            
    }
}
