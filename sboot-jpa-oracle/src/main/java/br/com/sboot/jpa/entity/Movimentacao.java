package br.com.sboot.jpa.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOV")
	@SequenceGenerator(sequenceName = "SEQ_MOV", allocationSize = 1, name = "SEQ_MOV")
	private Long id;
	
	@Column(name = "VALOR", nullable = false, scale = 2)
	private BigDecimal valor;
	
	@Column(name = "TIPO", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	
	@Column(name = "DATA", nullable = false)
	private LocalDateTime data;
	
	@Column(name = "DESCRICAO", nullable = false)
	private String descircao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Conta conta;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Categoria> categorias;
	
}
