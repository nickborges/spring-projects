package br.com.sboot.jpa.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRODUTO")
public class ProdutoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRO")
	@SequenceGenerator(name = "SEQ_PRO", allocationSize = 1, sequenceName = "SEQ_PRO")
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false, scale = 2)
	private BigDecimal valor;
	
	@ManyToMany
	private List<TipoEntity> tipos;

}
