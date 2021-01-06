package br.com.sboot.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedEntityGraphs({
		@NamedEntityGraph(name = "produtoComTipo",
				attributeNodes = { @NamedAttributeNode("tipos") }
				)
})
public class ProdutoGraph {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROG")
	@SequenceGenerator(name = "SEQ_PROG", allocationSize = 1, sequenceName = "SEQ_PROG")
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
