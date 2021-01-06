package br.com.springcloud.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class PedidoDTO implements Serializable{
	
	private static final long serialVersionUID = -2351954342527631282L;
	private Long id;
	private String nome;
	private String descricao;
	private int tamanho;
	private char ativo;
	
}
