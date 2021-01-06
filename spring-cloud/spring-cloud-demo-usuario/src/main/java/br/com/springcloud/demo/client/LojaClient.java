package br.com.springcloud.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

//basta passar o ID do service, já é integrado com Ribbon, com o eurekaserver.
@FeignClient("loja")
public interface LojaClient {
	
	@GetMapping("/todos")
	ResponseEntity<PedidoDTO> buscarTodos();

}
