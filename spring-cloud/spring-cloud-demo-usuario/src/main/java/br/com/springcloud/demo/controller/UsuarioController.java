package br.com.springcloud.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.springcloud.demo.domain.UsuarioDTO;
import br.com.springcloud.demo.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService service;
	
	@GetMapping("/todos")
	public ResponseEntity<UsuarioDTO> buscarTodos(){
		return ResponseEntity.ok(service.buscarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO.Usuario> buscarTodos(@PathVariable Long id){
		return ResponseEntity.ok(service.buscarPorId(id));
	}

	@PostMapping("/criar")
	public ResponseEntity<UsuarioDTO.Usuario> criar(@RequestBody UsuarioDTO.Usuario usuario){
		return ResponseEntity.ok(service.criar(usuario));
	}


}
