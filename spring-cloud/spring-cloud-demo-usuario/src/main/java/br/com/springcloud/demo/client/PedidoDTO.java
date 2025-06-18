package br.com.springcloud.demo.client;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PedidoDTO implements Serializable{
	
	private static final long serialVersionUID = -2351954342527631280L;
	private List<Pedido> pedidos;
	
	@Builder
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Pedido{
		private Long id;
		private String nome;
		private String descricao;
		private int tamanho;
		private char ativo;
		
	}
	
}
