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
@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTA")
	@SequenceGenerator(sequenceName = "SEQ_CONTA", allocationSize = 1, name = "SEQ_CONTA")
	private Long id;
	
	@Column(nullable = false)
	private Integer agencia;
	
	@Column(unique = true, nullable = false)
	private Integer numero;

}
