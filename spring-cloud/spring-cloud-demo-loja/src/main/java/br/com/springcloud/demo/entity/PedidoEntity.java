package br.com.springcloud.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "PEDIDO")
@NoArgsConstructor
public class PedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private int tamanho;
	
	private char ativo;

	public PedidoEntity(String nome, String descricao, int tamanho, char ativo) {
		this.nome = nome;
		this.descricao = descricao;
		this.tamanho = tamanho;
		this.ativo = ativo;
	}
}
