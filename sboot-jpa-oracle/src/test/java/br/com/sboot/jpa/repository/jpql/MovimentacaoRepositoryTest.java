package br.com.sboot.jpa.repository.jpql;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.sboot.jpa.entity.Categoria;
import br.com.sboot.jpa.entity.Conta;
import br.com.sboot.jpa.entity.Movimentacao;
import br.com.sboot.jpa.entity.TipoMovimentacao;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovimentacaoRepositoryTest {
	
	@Autowired
	TestEntityManager entityManager;

	@Test
	@Order(1)
	public void testeInsertMovimentacao() {
		Categoria categora = entityManager.find(Categoria.class, 1L);
		
		assertNotNull(categora);
		
		List<Categoria> categorias = new ArrayList<>();
		categorias.add(categora);
		
		Conta conta = entityManager.find(Conta.class, 1L);
		
		assertNotNull(conta);
		
		Movimentacao movimentacao1 = entityManager.persist(
				Movimentacao.builder()
				.conta(conta)
				.tipo(TipoMovimentacao.ENTRADA)
				.descircao("Depósito em dinheiro")
				.data(LocalDateTime.now())
				.valor(new BigDecimal(300.90).setScale(2, RoundingMode.HALF_EVEN))
				.categorias(categorias)
				.build()
				);
		
		assertNotNull(movimentacao1);
		
		Movimentacao movimentacao2 = entityManager.persist(
				Movimentacao.builder()
				.conta(conta)
				.tipo(TipoMovimentacao.ENTRADA)
				.descircao("Depósito em dinheiro")
				.data(LocalDateTime.now())
				.valor(new BigDecimal(300.90).setScale(2, RoundingMode.HALF_EVEN))
				.categorias(categorias)
				.build());
		
		assertNotNull(movimentacao2);
		
		
		entityManager.getEntityManager().getTransaction().commit();
		
	}
	
	@Test
	@Order(2)
	public void testeInsertMovimentacaoCategoria() {
		
		Categoria categora = entityManager.find(Categoria.class, 2L);
		
		assertNotNull(categora);
		
		List<Categoria> categorias = new ArrayList<>();
		categorias.add(categora);
		
		Conta conta = entityManager.find(Conta.class, 2L);
		
		Movimentacao mov1 = entityManager.persist(
				Movimentacao.builder()
				.conta(conta)
				.tipo(TipoMovimentacao.SAIDA)
				.descircao("Viagem de ida à SP")
				.data(LocalDateTime.now())
				.valor(new BigDecimal(300).setScale(2, RoundingMode.HALF_EVEN))
				.categorias(categorias)
				.build());
		
		assertNotNull(mov1);
		
		Movimentacao mov2 = entityManager.persist(
				Movimentacao.builder()
				.conta(conta)
				.tipo(TipoMovimentacao.SAIDA)
				.descircao("Viagem de volta para Poa")
				.data(LocalDateTime.now())
				.valor(new BigDecimal(350).setScale(2, RoundingMode.HALF_EVEN))
				.categorias(categorias)
				.build());
		
		assertNotNull(mov2);
		
		entityManager.getEntityManager().getTransaction().commit();
		
		
	}
	
	@Test
	@Order(3)
	public void testeFindMovimentacaoCategoria() {
		
		TypedQuery<Movimentacao> query = entityManager.getEntityManager()
				.createQuery("select m from Movimentacao m join m.categorias c where c.nome like :pCtegoriaNome", Movimentacao.class)
				.setParameter("pCtegoriaNome", "Depósito");
		
		List<Movimentacao> movimentacoes = query.getResultList();
		
		assert(movimentacoes.size()) > 0;
		
		assert(movimentacoes.get(0).getCategorias().size()) > 0;
		
		String nome = movimentacoes.get(0).getCategorias().get(0).getNome();
		assert(nome).contains("Depósito");
		
	}
	
}
