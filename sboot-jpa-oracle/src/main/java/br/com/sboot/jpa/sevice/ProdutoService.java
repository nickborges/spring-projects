package br.com.sboot.jpa.sevice;

import br.com.sboot.jpa.mapper.ProdutoMapper;
import br.com.sboot.jpa.model.Produto;
import br.com.sboot.jpa.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoMapper produtoMapper;

    public ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> execute() {
        return produtoMapper.mapToProduto(
                produtoRepository.findAll()
        );

    }
}
