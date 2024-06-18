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
    public ResponseEntity<List<OfertaDTOResposta>> getAll() {
        try {
            List<OfertaModel> list = ofertaService.getAll();
            List<OfertaDTOResposta> dtoList = list.stream()
                    .map(OfertaDTOResposta::new)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(dtoList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OfertaDTOResposta> insert(@RequestBody OfertaModel model, @RequestParam Integer imovelId,
            @RequestParam Integer usuarioId) {
        try {
            ImovelModel imovel = imovelService.find(imovelId);
            if (imovel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            model.setImovelModel(imovel);

            UserModel userModel = userService.find(usuarioId);
            if (userModel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            model.setUserModel(userModel);

            ofertaService.insert(model);

            OfertaDTOResposta dto = new OfertaDTOResposta(model);

            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            ofertaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfertaDTOResposta> update(@PathVariable Integer id, @RequestBody OfertaModel model,
            @RequestParam Integer imovelId, @RequestParam Integer usuarioId) {
        try {
            OfertaModel existingModel = ofertaService.find(id);
            if (existingModel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            ImovelModel imovel = imovelService.find(imovelId);
            if (imovel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            model.setImovelModel(imovel);

            UserModel userModel = userService.find(usuarioId);
            if (userModel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            model.setUserModel(userModel);
            model.setId(id);

            OfertaModel updatedModel = ofertaService.update(model);
            OfertaDTOResposta dto = new OfertaDTOResposta(updatedModel);

            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
