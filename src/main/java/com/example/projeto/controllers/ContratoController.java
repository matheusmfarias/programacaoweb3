package com.example.projeto.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.dtos.ContratoDTOResposta;
import com.example.projeto.models.ContratoModel;
import com.example.projeto.service.ContratoService;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/contratos")
public class ContratoController {

	@Autowired
	private ContratoService service;

	// @RequestMapping(method = RequestMethod.GET)
	// public ResponseEntity<List<ContratoModel>> getAll() {
	// 	List<ContratoModel> list = service.getAll();
	// 	return ResponseEntity.status(HttpStatus.OK).body(list);
	// }

	@GetMapping
    public ResponseEntity<List<ContratoDTOResposta>> getAll() {
        List<ContratoModel> contratos = service.getAll();
        List<ContratoDTOResposta> listaRetorno = new ArrayList<>();

        for (ContratoModel contrato : contratos) {
           ContratoDTOResposta dtoResposta = new ContratoDTOResposta(contrato);
		   listaRetorno.add(dtoResposta);
        }

        return new ResponseEntity<>(listaRetorno, HttpStatus.OK);
    }

	// @RequestMapping(method = RequestMethod.POST)
	// public ResponseEntity<Void> insert(@RequestBody ContratoDTO model) {	
	// 	ContratoModel x = model.transformaParaObjeto();
	// 	//service.insert(model);
	// 	return new ResponseEntity(model, HttpStatus.CREATED);
	// }

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ContratoModel model) {	
		service.insert(model);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

}
