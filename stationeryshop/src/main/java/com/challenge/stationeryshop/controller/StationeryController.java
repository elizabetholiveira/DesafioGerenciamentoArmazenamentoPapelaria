package com.challenge.stationeryshop.controller;

import com.challenge.stationeryshop.dto.StationeryDTO;
import com.challenge.stationeryshop.repository.StationeryRepository;
import com.challenge.stationeryshop.service.StationeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stationeries")
public class StationeryController {

    @Autowired
    StationeryService stationeryService;

    @Autowired
    StationeryRepository stationeryRepository;

    //Requests

    //GET
    //Listar todos os itens
    @GetMapping
    public ResponseEntity<List<StationeryDTO>> listarTodos(){
        return ResponseEntity.ok(stationeryService.listarTodos());
    }

    //Mostrar um item por ID
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Optional<StationeryDTO>> mostrarItemPorID(@PathVariable Long id){
        return ResponseEntity.ok(stationeryService.mostrarItemPorID(id));
    }

    //Listar todos os itens contendo um nome
    @GetMapping(path = "/name/{nome}")
    public ResponseEntity<Optional<StationeryDTO>> mostrarItemPorNome(@PathVariable String nome){
        return ResponseEntity.ok(stationeryService.mostrarItemPorNome(nome));
    }

    //POST
    //Adicionar um novo item no armazenamento da papelaria
    @PostMapping
    public ResponseEntity<StationeryDTO> adicionar(@RequestBody StationeryDTO stationeryDTO){
        StationeryDTO novoItem = stationeryService.adicionar(stationeryDTO);
        return new ResponseEntity<>(novoItem, HttpStatus.CREATED);
    }

    //PUT
    //Atualizar um item
    @PutMapping(path = "/{id}")
    public ResponseEntity<StationeryDTO> atualizar(@PathVariable Long id, @RequestBody StationeryDTO stationeryDTO){
        StationeryDTO novoItem = stationeryService.atualizar(id, stationeryDTO);
        return ResponseEntity.ok(novoItem);
    }

    //DELETE
    //Excluir um item
    @DeleteMapping(path = "/{id}")
    public void excluir (@PathVariable Long id){
        stationeryService.excluir(id);
    }
}
