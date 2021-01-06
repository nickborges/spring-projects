package br.com.springcloud.demo.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import br.com.springcloud.demo.entity.PedidoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class PedidosDTO implements Serializable{
	
	private static final long serialVersionUID = -2351954342527631280L;
	private List<Pedido> pedidos;
	
	@Builder
	@Getter
	@AllArgsConstructor
	public static class Pedido{
		private Long id;
		private String nome;
		private String descricao;
		private int tamanho;
		private char ativo;
		
		public Pedido(PedidoEntity entity){
			this.id = entity.getId();
			this.nome = entity.getNome();
			this.descricao = entity.getDescricao();
			this.tamanho = entity.getTamanho();
			this.ativo = entity.getAtivo();
		}
		
	}
	
	public static PedidosDTO mapper(List<PedidoEntity> entiy){
		return PedidosDTO.builder()
				.pedidos(entiy.stream().map(PedidosDTO.Pedido::new).collect(Collectors.toList()))
				.build();
	}
	
}
