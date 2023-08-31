package com.challenge.stationeryshop.service;

import com.challenge.stationeryshop.dto.StationeryDTO;
import com.challenge.stationeryshop.model.StationeryModel;
import com.challenge.stationeryshop.repository.StationeryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StationeryService {

    @Autowired
    StationeryRepository stationeryRepository;

    //methods

    //Adicionar um novo item no armazenamento da papelaria
    public StationeryDTO adicionar(StationeryDTO stationeryDTO){
        StationeryModel stationeryModel = new StationeryModel();
        BeanUtils.copyProperties(stationeryDTO, stationeryModel);
        stationeryRepository.save(stationeryModel);
        return stationeryDTO;
    }

    //Atualizar um item
    public StationeryDTO atualizar(Long id, StationeryDTO stationeryDTO){
        Optional<StationeryModel> stationeryOptional = stationeryRepository.findById(id);
        if (!stationeryOptional.isPresent()){
            throw new RuntimeException("ID " + id + " não encontrado");
        }
        StationeryModel encontrado = stationeryOptional.get();
        if(stationeryDTO.getName() != null){
            encontrado.setName(stationeryDTO.getName());
        }
        if(stationeryDTO.getDescription() != null){
            encontrado.setDescription(stationeryDTO.getDescription());
        }
        if(stationeryDTO.getPrice() != null){
            encontrado.setPrice(stationeryDTO.getPrice());
        }
        if(stationeryDTO.getQuantity() != null){
            encontrado.setQuantity(stationeryDTO.getQuantity());
        }
        stationeryRepository.save(encontrado);
        StationeryDTO newStationeryDTO = new StationeryDTO();
        BeanUtils.copyProperties(encontrado, newStationeryDTO);
        return newStationeryDTO;
    }

    //Listar todos os itens
    public List<StationeryDTO> listarTodos(){
        List<StationeryModel> stationeryModels = stationeryRepository.findAll();
        List<StationeryDTO> stationeryDTOS = new ArrayList<>();
        for (StationeryModel stationeryModel : stationeryModels){
            StationeryDTO stationeryDTO = new StationeryDTO();
            BeanUtils.copyProperties(stationeryModel, stationeryDTO);
            stationeryDTOS.add(stationeryDTO);
        }
        return stationeryDTOS;
    }

    //Mostrar um item por ID
    public Optional<StationeryDTO> mostrarItemPorID(Long id){
        Optional<StationeryModel> stationeryModel = stationeryRepository.findById(id);

        if (!stationeryModel.isPresent()){
            throw new RuntimeException("ID " + id + " não encontrado");
        }
        StationeryDTO stationeryDTO = new StationeryDTO();
        StationeryModel encontrado = stationeryModel.get();
        BeanUtils.copyProperties(encontrado, stationeryDTO);
        return Optional.of(stationeryDTO);
    }

    //Listar todos os itens contendo um nome
    public Optional<StationeryDTO> mostrarItemPorNome(String nome){
        Optional<StationeryModel> stationeryModel = stationeryRepository.findByNome(nome);

        if (!stationeryModel.isPresent()){
            throw new RuntimeException("Nada contendo " + nome + " foi encontrado");
        }
        StationeryDTO stationeryDTO = new StationeryDTO();
        StationeryModel encontrado = stationeryModel.get();
        BeanUtils.copyProperties(encontrado, stationeryDTO);
        return Optional.of(stationeryDTO);
    }

    //Excluir um item
    public void excluir(Long id){
        Optional<StationeryModel> stationeryModel = stationeryRepository.findById(id);
        if(!stationeryModel.isPresent()){
            throw new RuntimeException("ID " + id + " não encontrado");
        }
        stationeryRepository.deleteById(id);
    }

}