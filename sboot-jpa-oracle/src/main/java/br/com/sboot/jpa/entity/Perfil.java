package br.com.sboot.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Perfil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERF")
	@SequenceGenerator(name = "SEQ_PERF", allocationSize = 1, sequenceName = "SEQ_PERF")
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String tipo;
	
	@OneToOne(mappedBy = "perfil")
	private Aluno aluno;

}
