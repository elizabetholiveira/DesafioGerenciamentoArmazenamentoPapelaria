package com.challenge.stationeryshop;

import com.challenge.stationeryshop.controller.StationeryController;
import com.challenge.stationeryshop.dto.StationeryDTO;
import com.challenge.stationeryshop.model.StationeryModel;
import com.challenge.stationeryshop.repository.StationeryRepository;
import com.challenge.stationeryshop.service.StationeryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.convert.ThreeTenBackPortConverters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StationeryController.class)
class StationeryshopApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private StationeryService stationeryService;

	@MockBean
	private StationeryRepository stationeryRepository;

	//Testar se um novo item está sendo salvo no armazenamento corretamente
	//Tudo certo
	@Test
	public void testAdicionarCerto() throws Exception{
		String name = "SuperSoft Faber-Castell";
		String description = "50 EcoLápis de Cor SuperSoft";
		BigDecimal price = new BigDecimal(100.00);
		Long quantity = 5L;

		StationeryDTO teste = new StationeryDTO(name, description, price, quantity);
		when(stationeryService.adicionar(teste))
				.thenReturn(new StationeryDTO(name, description, price, quantity));
		mockMvc.perform(MockMvcRequestBuilders.post("/api/stationeries")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(teste)))
				.andExpect(status().isCreated());
	}

	//Sem descrição(permitido)
	@Test
	public void testAdicionarSemDescricao() throws Exception{
		String name = "SuperSoft Faber-Castell";
		String description = null;
		BigDecimal price = new BigDecimal(100.00);
		Long quantity = 5L;

		StationeryDTO teste = new StationeryDTO(name, description, price, quantity);
		when(stationeryService.adicionar(teste))
				.thenReturn(new StationeryDTO(name, description, price, quantity));
		mockMvc.perform(MockMvcRequestBuilders.post("/api/stationeries")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(teste)))
				.andExpect(status().isCreated());
	}

	//Sem nome(não permitido)
	//ToDo Está permitindo criação. Conferir que mensagem que sai no POSTMAN
	@Test
	public void testAdicionarSemNome() throws Exception{
		String name = null;
		String description = "50 EcoLápis de Cor SuperSoft";
		BigDecimal price = new BigDecimal(100.00);
		Long quantity = 5L;

		StationeryDTO teste = new StationeryDTO(name, description, price, quantity);
		when(stationeryService.adicionar(teste))
				.thenReturn(new StationeryDTO(name, description, price, quantity));
		mockMvc.perform(MockMvcRequestBuilders.post("/api/stationeries")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(teste)))
				.andExpect(status().isBadRequest());
	}

	//Sem preço(não permitido)
	//ToDo mesma coisa do anterior.
	@Test
	public void testAdicionarSemPreco() throws Exception{
		String name = "SuperSoft Faber-Castell";
		String description = "50 EcoLápis de Cor SuperSoft";
		BigDecimal price = null;
		Long quantity = 5L;

		StationeryDTO teste = new StationeryDTO(name, description, price, quantity);
		when(stationeryService.adicionar(teste))
				.thenReturn(new StationeryDTO(name, description, price, quantity));
		mockMvc.perform(MockMvcRequestBuilders.post("/api/stationeries")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(teste)))
				.andExpect(status().isBadRequest());
	}

	//Preço incorreto(não permitido)
	//ToDo mesma coisa.
	@Test
	public void testAdicionarPrecoIncorreto() throws Exception{
		String name = "SuperSoft Faber-Castell";
		String description = "50 EcoLápis de Cor SuperSoft";
		BigDecimal price = new BigDecimal(100.002);
		Long quantity = 5L;

		StationeryDTO teste = new StationeryDTO(name, description, price, quantity);
		when(stationeryService.adicionar(teste))
				.thenReturn(new StationeryDTO(name, description, price, quantity));
		mockMvc.perform(MockMvcRequestBuilders.post("/api/stationeries")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(teste)))
				.andExpect(status().isBadRequest());
	}

	//Testar se um item está sendo atualizado corretamente
	@Test
	public void testAtualizar() throws Exception{
		String name = "SuperSoft Faber-Castell";
		String description = "50 EcoLápis de Cor SuperSoft";
		BigDecimal price = new BigDecimal(100.00);
		Long quantity = 5L;

		StationeryDTO teste = new StationeryDTO(name, description, price, quantity);
		when(stationeryService.atualizar(1L, teste))
				.thenReturn(new StationeryDTO(name, description, price, quantity));
		mockMvc.perform(MockMvcRequestBuilders.put("/api/stationeries/{id}", 1)
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(teste)))
				.andExpect(status().isOk());
	}

	//Testar se todos os itens estão sendo listados corretamente
	@Test
	public void testListarTodos() throws Exception{
		mockMvc.perform(get("/api/stationeries"))
				.andExpect(status().isOk());
	}

	//Testar se mostrar um item por ID está funcionando corretamente
	//Id existe
	@Test
	public void testMostrarItemPorIdExistente() throws Exception{
		Long id = 1L;
		mockMvc.perform(get("/api/stationeries/id/{id}", id))
				.andExpect(status().isOk());
	}

	//Id não existe
	//ToDo não estou conseguindo fazer com que ele não reconheça o id
	@Test
	public void testMostrarItemPorIdInexistente() throws Exception{
		Long id = 2L;
		mockMvc.perform(get("/api/stationeries/id/{id}", id))
				.andExpect(status().isNotFound());
	}

	//Testar se mostrar um item por nome está funcionando corretamente
	//Nome existe
	//ToDo mesma coisa do mostrar um item pelo ID, tanto Existente quanto Inexistente dá na mesma
	@Test
	public void testMostrarItemPorNome() throws Exception{
		String name = "Lapis";
		mockMvc.perform(get("/api/stationeries/name/{nome}", name))
				.andExpect(status().isOk());
	}

	//Testar se excluir um item está funcionando
	@Test
	public void testExcluir() throws Exception{
		Long id = 1L;
		mockMvc.perform(delete("/api/stationeries/{is}", id))
				.andExpect(status().isOk());
	}

}
