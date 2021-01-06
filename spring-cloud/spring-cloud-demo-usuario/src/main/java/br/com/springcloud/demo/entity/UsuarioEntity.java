package br.com.springcloud.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "USUARIO")
@NoArgsConstructor
public class UsuarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private int idade;

	public UsuarioEntity(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}
}
