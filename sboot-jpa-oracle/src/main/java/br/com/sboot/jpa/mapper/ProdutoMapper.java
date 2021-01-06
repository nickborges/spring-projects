package br.com.sboot.jpa.mapper;

import br.com.sboot.jpa.entity.ProdutoEntity;
import br.com.sboot.jpa.model.Produto;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProdutoMapper {

    public List<Produto> mapToProduto(List<ProdutoEntity> entity){
        return Produto.produtos(entity);
    }

}
