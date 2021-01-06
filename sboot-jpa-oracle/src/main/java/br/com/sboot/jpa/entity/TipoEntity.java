package br.com.sboot.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TIPO")
public class TipoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIP")
	@SequenceGenerator(name = "SEQ_TIP", allocationSize = 1, sequenceName = "SEQ_TIP")
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String nome;

}
