package com.challenge.stationeryshop.controller;

import com.challenge.stationeryshop.dto.StationeryDTO;
import com.challenge.stationeryshop.repository.StationeryRepository;
import com.challenge.stationeryshop.service.StationeryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stationeries")
@Tag(name = "Armazenamento da papelaria")
public class StationeryController {

    @Autowired
    StationeryService stationeryService;

    @Autowired
    StationeryRepository stationeryRepository;

    //Requests

    //GET
    //Listar todos os itens
    @GetMapping
    @Operation(summary = "Lista todos os itens", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso"))
    public ResponseEntity<List<StationeryDTO>> listarTodos(){
        return ResponseEntity.ok(stationeryService.listarTodos());
    }

    //Mostrar um item por ID
    @GetMapping(path = "/id/{id}")
    @Operation(summary = "Mostra um item pelo ID", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Item encontrado"))
    public ResponseEntity<Optional<StationeryDTO>> mostrarItemPorID(@PathVariable Long id){
        return ResponseEntity.ok(stationeryService.mostrarItemPorID(id));
    }

    //Listar todos os itens contendo um nome
    @GetMapping(path = "/name/{nome}")
    @Operation(summary = "Lista todos os itens que contém um nome", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso"))
    public ResponseEntity<List<StationeryDTO>> mostrarItemPorNome(@PathVariable String nome){
        return ResponseEntity.ok(stationeryService.mostrarItemPorNome(nome));
    }

    //POST
    //Adicionar um novo item no armazenamento da papelaria
    @PostMapping
    @Operation(summary = "Adiciona um item no armazenamento", method = "POST")
    @ApiResponses(value = @ApiResponse(responseCode = "201", description = "Criação de item feita com sucesso"))
    public ResponseEntity<StationeryDTO> adicionar(@Valid @RequestBody StationeryDTO stationeryDTO){
        StationeryDTO novoItem = stationeryService.adicionar(stationeryDTO);
        return new ResponseEntity<>(novoItem, HttpStatus.CREATED);
    }

    //PUT
    //Atualizar um item
    @PutMapping(path = "/{id}")
    @Operation(summary = "Altera os dados de um item", method = "PUT")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Atualização feita com sucesso"))
    public ResponseEntity<StationeryDTO> atualizar(@PathVariable Long id, @Valid @RequestBody StationeryDTO stationeryDTO){
        StationeryDTO novoItem = stationeryService.atualizar(id, stationeryDTO);
        return ResponseEntity.ok(novoItem);
    }

    //DELETE
    //Excluir um item
    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Deleta um item pelo ID", method = "DELETE")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Exclusão feita com sucesso"))
    public void excluir (@PathVariable Long id){
        stationeryService.excluir(id);
    }
}
