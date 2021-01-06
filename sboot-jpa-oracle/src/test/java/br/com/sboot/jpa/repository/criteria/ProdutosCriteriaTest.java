package br.com.sboot.jpa.repository.criteria;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import br.com.sboot.jpa.entity.ProdutoEntity;
import br.com.sboot.jpa.entity.ProdutoGraph;
import br.com.sboot.jpa.entity.TipoEntity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdutosCriteriaTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Test
	@Order(1)
	public void testeInserProduto() {
		
		TipoEntity tipoEntity = entityManager.persist(TipoEntity.builder()
				.nome("Celular " + new Random().nextInt())
				.build());
		
		assertNotNull(tipoEntity);
		
		List<TipoEntity> tipoEntities = new ArrayList<>();
		tipoEntities.add(tipoEntity);
		
		ProdutoEntity produtoEntity = entityManager.persist(ProdutoEntity.builder()
				.nome("Iphone " + new Random().nextInt())
				.descricao("n/a")
				.tipos(tipoEntities)
				.valor(new BigDecimal(999.99))
				.build());
		
		assertNotNull(produtoEntity);
		
		entityManager.getEntityManager().getTransaction().commit();
		
		
	}
	
	@Test
	@Order(2)
	public void testeGetProdutos() {
		
		CriteriaBuilder builder = entityManager.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ProdutoEntity> query = builder.createQuery(ProdutoEntity.class);
		Root<ProdutoEntity> produto = query.from(ProdutoEntity.class);
		
		query.select(produto);
		query.where(builder.like(produto.get("nome"), "%Iphone%"),
				builder.equal(produto.join("tipos").get("id"), 1L));
		
		List<ProdutoEntity> result = entityManager.getEntityManager().createQuery(query).getResultList();
		
		assert(result.size()) > 0;
		
		assert(result.get(0).getTipos().get(0).getNome()).contains("Celular");
		
	}

	@Test
	@Order(3)
	public void testeEntityGraphs() {

		TipoEntity tipoEntity = entityManager.persist(TipoEntity.builder()
				.nome("Notebook " + new Random().nextInt())
				.build());

		assertNotNull(tipoEntity);

		List<TipoEntity> tipoEntities = new ArrayList<>();
		tipoEntities.add(tipoEntity);

		ProdutoGraph produto = entityManager.persist(ProdutoGraph.builder()
				.nome("Macbook pro " + new Random().nextInt())
				.descricao("n/a")
				.tipos(tipoEntities)
				.valor(new BigDecimal(2999.99))
				.build());

		assertNotNull(produto);

		List<ProdutoGraph> produtoGraphs =
				entityManager.getEntityManager()
						.createQuery("select p from ProdutoGraph p", ProdutoGraph.class)
						.setHint("javax.persistence.loadgraph",
								entityManager.getEntityManager().getEntityGraph("produtoComTipo"))
						.getResultList();

		assertNotNull(produtoGraphs);

		entityManager.getEntityManager().getTransaction().commit();


	}
}
