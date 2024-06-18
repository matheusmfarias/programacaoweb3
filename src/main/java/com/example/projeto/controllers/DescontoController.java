package com.example.projeto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.dtos.DescontoDTOResposta;
import com.example.projeto.models.DescontoModel;
import com.example.projeto.models.OfertaModel;
import com.example.projeto.service.DescontoService;
import com.example.projeto.service.OfertaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/descontos")
public class DescontoController {

    @Autowired
    private DescontoService descontoService;

    @Autowired
    private OfertaService ofertaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DescontoDTOResposta>> getAll() {
        try {
            List<DescontoModel> list = descontoService.getAll();
            List<DescontoDTOResposta> dtoList = list.stream().map(DescontoDTOResposta::new).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(dtoList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DescontoDTOResposta> insert(@RequestBody DescontoModel model,
            @RequestParam Integer ofertaId) {
        try {
            OfertaModel oferta = ofertaService.find(ofertaId);
            if (oferta == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            model.setOfertaModel(oferta);

            descontoService.insert(model);

            DescontoDTOResposta dto = new DescontoDTOResposta(model);

            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            descontoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescontoDTOResposta> update(@PathVariable Integer id, @RequestBody DescontoModel model,
            @RequestParam Integer ofertaId) {
        try {
            DescontoModel existingModel = descontoService.find(id);
            if (existingModel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            OfertaModel oferta = ofertaService.find(ofertaId);
            if (oferta == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            model.setOfertaModel(oferta);
            model.setId(id);

            DescontoModel updatedModel = descontoService.insert(model);
            DescontoDTOResposta resposta = new DescontoDTOResposta(updatedModel);

            return ResponseEntity.status(HttpStatus.OK).body(resposta);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}