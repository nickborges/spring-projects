package br.com.sboot.jpa.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testeGetProdutos() throws Exception {
		var mvc = mockMvc.perform(get("/v1/produtos"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();

		var result = mvc.getResponse().getContentAsString();

		Assertions.assertEquals(result.isBlank(), false);
	}
	
}
