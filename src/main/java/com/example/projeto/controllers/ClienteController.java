package com.example.projeto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.dtos.ClienteRespostaDTO;
import com.example.projeto.models.ClienteModel;
import com.example.projeto.service.ClienteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteRespostaDTO>> getAll() {
		List<ClienteModel> list = service.getAll();
		
		List<ClienteRespostaDTO> dtoList = list.stream()
			.map(cliente -> new ClienteRespostaDTO(cliente))
			.collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ClienteModel model) {		
		service.insert(model);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ClienteModel model) {		
		service.insert(model);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


}
