package br.com.sboot.jpa.repository.jpql;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Random;

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

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoriaRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Test
	@Order(1)
	public void testeInsertCategoria() {
		Categoria categoria = entityManager.persist(
				Categoria.builder()
				.nome("Viagem" + new Random().nextInt())
				.build());
		
		assertNotNull(categoria);
		
		Categoria categoria2 = entityManager.persist(
				Categoria.builder()
				.nome("Depósito" + new Random().nextInt())
				.build());
		
		assertNotNull(categoria2);
		
		entityManager.getEntityManager().getTransaction().commit();
				
	}

}
