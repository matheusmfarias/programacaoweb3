package com.example.projeto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.projeto.dtos.ImovelDTO;
import com.example.projeto.dtos.ImovelDTOResposta;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.service.ImovelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/imoveis")
public class ImovelController {

	@Autowired
	private ImovelService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ImovelDTOResposta>> getAll() {
		List<ImovelModel> list = service.getAll();

		List<ImovelDTOResposta> listaDtos = list.stream().map(imovel -> new ImovelDTOResposta(imovel))
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(listaDtos);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ImovelDTOResposta> insert(
			@RequestParam("descricao") String descricao,
			@RequestParam("quartos") Integer quartos,
			@RequestParam("vagas") Integer vagas,
			@RequestParam("usuario_id") Integer usuarioId,
			@RequestParam("imagem") MultipartFile imagem) {

		ImovelDTO imovelDTO = new ImovelDTO();
		imovelDTO.setDescricao(descricao);
		imovelDTO.setQuartos(quartos);
		imovelDTO.setVagas(vagas);
		imovelDTO.setUsuario_id(usuarioId);

		ImovelModel imovel = service.transformaParaObjeto(imovelDTO);

		String urlImagem = service.uploadImagem(imagem);

		imovel.setImagem(urlImagem);

		service.insert(imovel);

		return new ResponseEntity(ImovelDTOResposta.transformaEmDTO(imovel), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ImovelDTOResposta> atualizar(
			@RequestParam("id") Integer id,
			@RequestParam("descricao") String descricao,
			@RequestParam("quartos") Integer quartos,
			@RequestParam("vagas") Integer vagas,
			@RequestParam("usuario_id") Integer usuarioId,
			@RequestParam("imagem") MultipartFile imagem) {

		ImovelDTO imovelDTO = new ImovelDTO();
		imovelDTO.setId(id);
		imovelDTO.setDescricao(descricao);
		imovelDTO.setQuartos(quartos);
		imovelDTO.setVagas(vagas);
		imovelDTO.setUsuario_id(usuarioId);

		ImovelModel imovel = service.transformaParaObjeto(imovelDTO);

		String urlImagem = service.uploadImagem(imagem);

		imovel.setImagem(urlImagem);

		service.insert(imovel);

		return new ResponseEntity(ImovelDTOResposta.transformaEmDTO(imovel), HttpStatus.CREATED);
	}

	@GetMapping("/descontos")
	public ResponseEntity<List<ImovelDTOResposta>> getAllDesconto() {
		List<ImovelModel> list = service.getAllDesconto();

		List<ImovelDTOResposta> listaDtos = list.stream().map(imovel -> new ImovelDTOResposta(imovel))
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(listaDtos);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
