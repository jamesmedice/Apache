package com.gft.dataservice.web.controllers;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gft.dataservice.entities.Client;
import com.gft.dataservice.repositories.ClientRepository;

@RestController
@RequestMapping("/data")
public class ClientResource {

    @Autowired
    private ClientRepository clientRepository;
    
    @RequestMapping(value = "/clients/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    
    @RequestMapping(value = "/clients/save/", method = RequestMethod.POST)
    @ResponseBody
    public Client save(@RequestBody Client client) {
    	clientRepository.save(client);
    	return client;
    }

}
