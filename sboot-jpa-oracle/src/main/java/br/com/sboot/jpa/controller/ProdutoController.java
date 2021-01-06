package br.com.sboot.jpa.controller;

import br.com.sboot.jpa.sevice.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {

	@Autowired
	public ProdutoService produtoService;
	
	@GetMapping("/v1/produtos")
	public ResponseEntity execute(){
		return ResponseEntity
				.ok()
				.body(produtoService.execute());
	}

}
