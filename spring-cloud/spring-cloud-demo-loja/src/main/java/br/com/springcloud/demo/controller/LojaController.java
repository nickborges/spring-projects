package br.com.springcloud.demo.controller;

import br.com.springcloud.demo.domain.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.springcloud.demo.domain.PedidosDTO;
import br.com.springcloud.demo.service.LojaService;

@RestController
public class LojaController {

	@Autowired
	LojaService service;
	
	@GetMapping("/todos")
	public ResponseEntity<PedidosDTO> buscarTodos() {
		return ResponseEntity.ok(service.buscarTodos());
	}

	@PostMapping("/criar")
	public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoDTO pedido,
												  UriComponentsBuilder uri){

		PedidoDTO pedidosDTO = service.criarPedido(pedido);

		return ResponseEntity.created(
				uri.path("/crair/{id}")
						.buildAndExpand(1l).toUri()
		).body(pedidosDTO);
	}
}
