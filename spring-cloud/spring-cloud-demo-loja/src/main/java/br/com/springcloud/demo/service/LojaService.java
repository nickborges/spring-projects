package br.com.springcloud.demo.service;

import br.com.springcloud.demo.domain.PedidoDTO;
import br.com.springcloud.demo.entity.PedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.springcloud.demo.domain.PedidosDTO;
import br.com.springcloud.demo.repository.PedidoRepositoy;

@Service
public class LojaService {

	@Autowired
	PedidoRepositoy pedidoRepository;

	public PedidosDTO buscarTodos(){
		return PedidosDTO.mapper(pedidoRepository.findAll());
	}

	public PedidoDTO criarPedido(PedidoDTO pedido) {
		pedidoRepository.save(
				new PedidoEntity(
						pedido.getNome(),
						pedido.getDescricao(),
						pedido.getTamanho(),
						pedido.getAtivo()
				)
		);
		return pedido;
	}
}
