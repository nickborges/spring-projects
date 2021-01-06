package br.com.sboot.jpa.model;

import br.com.sboot.jpa.entity.ProdutoEntity;
import br.com.sboot.jpa.entity.TipoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public final class Produto {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private List<Tipo> tipos;

    public Produto(ProdutoEntity entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.tipos = tipos(entity);
    }

    @Builder
    @AllArgsConstructor
    public static final class Tipo{
        private Long id;
        private String nome;

        public Tipo(TipoEntity entity){
            this.id = entity.getId();
            this.nome = entity.getNome();
        }

    }

    public static final List<Produto.Tipo> tipos(ProdutoEntity entity){
        return entity.getTipos()
                .stream()
                .map(Produto.Tipo::new)
                .collect(Collectors.toList());
    }

    public static final List<Produto> produtos(List<ProdutoEntity> entity){
        return entity
                .stream()
                .map(Produto::new)
                .collect(Collectors.toList());
    }

}
