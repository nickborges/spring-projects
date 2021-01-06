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

import br.com.sboot.jpa.entity.Conta;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContaRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Test
	@Order(1)
	public void testePersisteConta_success() {

		Conta conta = 
				entityManager.persistAndFlush(
						Conta.builder()
						.agencia(2038)
						.numero(new Random().nextInt())
						.build());
	
		assertNotNull(conta);
		
		entityManager.getEntityManager().getTransaction().commit();

	}
	
	@Test
	@Order(2)
	public void testeFindConta_success() {

		Conta conta = 
				entityManager.persistAndFlush(
						Conta.builder()
						.agencia(2038)
						.numero(new Random().nextInt())
						.build());
	
		Conta findConta = entityManager.find(Conta.class, conta.getId());
		
		assertNotNull(findConta);
		
		entityManager.getEntityManager().getTransaction().commit();

	}

}
