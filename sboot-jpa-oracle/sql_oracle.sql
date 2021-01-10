--
select movimentacao_categorias.*
      ,cliente.nome
      ,conta.agencia
      ,conta.numero
      ,movimentacao.descricao
      ,movimentacao.tipo
      ,to_char(movimentacao.data, 'dd/MM/yyyy HH24:mm:ss') as data
      ,movimentacao.valor
      ,categoria.nome as categoria
    from movimentacao
    inner join conta
        on conta.id = movimentacao.conta_id
    inner join cliente
        on cliente.conta_id = conta.id
    inner join movimentacao_categorias
        on movimentacao.id = movimentacao_categorias.movimentacao_id
    inner join categoria
        on categoria.id = movimentacao_categorias.categorias_id
;
--
select conta.* 
    from conta;
--
select cliente.* 
    from cliente;
--
select categoria.* 
    from categoria;
--
select movimentacao.* 
    from movimentacao;
--
select movimentacao_categorias.* 
    from movimentacao_categorias;
--
select aluno.*, perfil.*
    from aluno
    inner join perfil
    on perfil.id = aluno.perfil_id;
--
select produto.* 
    from produto;
--
select tipo.*
    from tipo;
