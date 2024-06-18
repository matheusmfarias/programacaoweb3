package com.example.projeto.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.dtos.ContratoDTOResposta;
import com.example.projeto.models.ClienteModel;
import com.example.projeto.models.ContratoAluguelModel;
import com.example.projeto.models.ContratoModel;
import com.example.projeto.models.ContratoVendaModel;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.UserModel;
import com.example.projeto.service.ContratoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/contratos")
public class ContratoController {

	@Autowired
	private ContratoService service;

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

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody ContratoDTOResposta dto) {
		ContratoModel model = convertToModel(dto);
		service.insert(model);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody ContratoDTOResposta dto) {
		if (dto.getTipo() == null) {
			throw new IllegalArgumentException("O tipo do contrato não pode ser nulo.");
		}
		if (dto.getClienteId() == null) {
			throw new IllegalArgumentException("O id do cliente não pode ser nulo.");
		}
		if (dto.getUsuarioId() == null) {
			throw new IllegalArgumentException("O id do usuário não pode ser nulo.");
		}
		if (dto.getImovelId() == null) {
			throw new IllegalArgumentException("O id do imóvel não pode ser nulo.");
		}

		ContratoModel model = convertToModel(dto);
		model.setId(id);
		service.update(model);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	private ContratoModel convertToModel(ContratoDTOResposta dto) {
		ClienteModel cliente = service.findClienteById(dto.getClienteId());
		UserModel usuario = service.findUserById(dto.getUsuarioId());
		ImovelModel imovel = service.findImovelById(dto.getImovelId());

		if (dto.getTipo() == 1) {
			ContratoAluguelModel aluguelModel = new ContratoAluguelModel();
			aluguelModel.setValor(dto.getValor());
			aluguelModel.setClienteModel(cliente);
			aluguelModel.setUserModel(usuario);
			aluguelModel.setImovelModel(imovel);
			aluguelModel.setDataInicio(dto.getDataInicio());
			aluguelModel.setDataFim(dto.getDataFim());
			aluguelModel.setIndiceReajuste(dto.getIndiceReajuste());
			return aluguelModel;
		} else if (dto.getTipo() == 2) {
			ContratoVendaModel vendaModel = new ContratoVendaModel();
			vendaModel.setValor(dto.getValor());
			vendaModel.setClienteModel(cliente);
			vendaModel.setUserModel(usuario);
			vendaModel.setImovelModel(imovel);
			vendaModel.setDataAssinatura(dto.getDataAssinatura());
			return vendaModel;
		}

		throw new IllegalArgumentException("Tipo de contrato inválido: " + dto.getTipo());
	}
}
