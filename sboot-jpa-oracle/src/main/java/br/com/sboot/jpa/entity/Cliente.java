package br.com.sboot.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLI")
	@SequenceGenerator(name = "SEQ_CLI", allocationSize = 1, sequenceName = "SEQ_CLI")
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String nome;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Conta conta;

}
