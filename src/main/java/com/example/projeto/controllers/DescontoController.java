package com.example.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.models.DescontoModel;
import com.example.projeto.service.DescontoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/descontos")
public class DescontoController {

    @Autowired
    private DescontoService service;


    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DescontoModel>> getAll() {	
        List<DescontoModel>	 lista = service.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

    @RequestMapping(method = RequestMethod.POST)
    public DescontoModel insert(@RequestBody DescontoModel desconto) {
        try{
            return service.save(desconto);
        }
        catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
        
    }
}