/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.api;

import br.edu.ifpb.restmqtt.service.ServiceMQTT;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author laerton
 */
@Stateless
@Path("sensores")
public class ResourceMQTT {
    
    @Inject
    private ServiceMQTT service;
    
    @GET
    @Path("historicoTemp")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response historicoTemp(){
        List<Integer> temps = service.historicoTemperaturas();
        GenericEntity<List<Integer>> entity = new GenericEntity<List<Integer>>(temps) {
        };
        return Response.ok() 
                .entity(entity)
                .build();
    }
    
    @GET
    @Path("ligaSensor")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response ligaSensor(){
        service.ligaSensor();
        return Response.ok() 
                .build();
    }
    
    @GET
    @Path("desligaSensor")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response desligaSensor(){
        service.desligaSensor();
        return Response.ok() 
                .build();
    }
    
    @GET
    @Path("ultimaTemp")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response ulimaTemp(){
        Integer temp = service.ultimaTemperatura();
        GenericEntity<Integer> entity = new GenericEntity<Integer>(temp) {
        };
        return Response.ok() 
                .entity(entity)
                .build();
    }
    
    @GET
    @Path("ajusteTemp/{temp}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response ajusteTemp(@PathParam("temp") int temp){
        service.ajustaTemp(temp);
        return Response.ok() 
                .build();
    }
    
}
