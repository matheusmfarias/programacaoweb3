package com.example.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.dtos.OfertaDTOResposta;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.OfertaModel;
import com.example.projeto.models.UserModel;
import com.example.projeto.service.ImovelService;
import com.example.projeto.service.OfertaService;
import com.example.projeto.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/ofertas")
public class OfertaController {

	@Autowired
	private OfertaService ofertaService;

	@Autowired
	private ImovelService imovelService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<OfertaModel>> getAll() {
		List<OfertaModel> list = ofertaService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	// @RequestMapping(method = RequestMethod.POST)
	// public ResponseEntity<Void> insert(@RequestBody OfertaDTO dto) {
	// 	OfertaModel Oferta = service.transformaParaObjeto(dto);
	// 	service.insert(Oferta);
	// 	return new ResponseEntity(Oferta, HttpStatus.CREATED);
	// }

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<OfertaDTOResposta> insert(@RequestBody OfertaModel model, @RequestParam Integer imovelId, @RequestParam Integer usuarioId) {
		
		ImovelModel imovel = imovelService.find(imovelId);

		model.setImovelModel(imovel);

		UserModel userModel = userService.find(usuarioId);

		model.setUserModel(userModel);

		ofertaService.insert(model);

		OfertaDTOResposta dto = new OfertaDTOResposta(model);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}


}
