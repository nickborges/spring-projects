package br.com.springcloud.demo.domain;

import java.util.List;

import br.com.springcloud.demo.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
public class UsuarioDTO {

	private List<Usuario> usuarios;
	
	@Builder
	@Getter
	@AllArgsConstructor
	public static class Usuario{
		private Long id;
		private String nome;
		private int idade;
		
		public Usuario(UsuarioEntity entity) {
			this.id = entity.getId();
			this.nome = entity.getNome();
			this.idade = entity.getIdade();
		}
	}
	
	public static UsuarioDTO mapper(List<UsuarioEntity> entidades){
		return UsuarioDTO.builder()
				.usuarios(entidades.stream().map(UsuarioDTO.Usuario::new).collect(Collectors.toList()))
				.build();
	}
	
}
