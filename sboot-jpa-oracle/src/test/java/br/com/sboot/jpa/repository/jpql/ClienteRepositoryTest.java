package br.com.sboot.jpa.repository.jpql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Random;

import javax.persistence.Query;
import javax.persistence.RollbackException;
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

import br.com.sboot.jpa.entity.Cliente;
import br.com.sboot.jpa.entity.Conta;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteRepositoryTest {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	@Order(1)
	public void testeInsertCliente() {
		String nome = "Mefistófolis" + new Random().nextInt();
		
		Conta conta = entityManager.find(Conta.class, 1L);
		
		assertNotNull(conta);
		
		Cliente cliente = entityManager.persist(
				Cliente.builder()
				.nome(nome)
				.conta(conta)
				.build()
				);
		
		assertNotNull(cliente);
		
		entityManager.getEntityManager().getTransaction().commit();		
		
	}
	
	@Test
	@Order(2)
	public void testeInsertCliente_exception() {
		
		assertThrows(RollbackException.class, () -> {
			Conta conta = entityManager.find(Conta.class, 1L);	
			assertNotNull(conta);
			
			String nome = "Mefistófolis" + new Random().nextInt();
			
			entityManager.persist(
				Cliente.builder()
				.nome(nome)
				.conta(conta)
				.build());
			
			entityManager.persist(
					Cliente.builder()
					.nome(nome)
					.conta(conta)
					.build());
		
			entityManager.getEntityManager().getTransaction().commit();
				
		});
	}
	
	@Test
	@Order(3)
	public void testFindClienteQuery() {
		
		Query query = entityManager.getEntityManager()
				.createQuery("select c from Cliente c", Cliente.class);
		
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = query.getResultList();
		
		assert(clientes.size()) > 0;
		
	}
	
	@Test
	@Order(4)
	public void testeFindClienteTypedQuery() {
		
		TypedQuery<Cliente> query = entityManager.getEntityManager()
				.createQuery("select c from Cliente c", Cliente.class);
		
		List<Cliente> clientes = query.getResultList();
		
		assert(clientes.size()) > 0;
		
	}
	
	@Test
	@Order(5)
	public void testeFindClienteById() {
		
		Cliente cliente = Cliente.builder()
				.id(1L)
				.build();
		
		TypedQuery<Cliente> query = entityManager.getEntityManager()
				.createQuery("select c from Cliente c where c = :cliente", Cliente.class);
		
		query.setParameter("cliente", cliente);
		
		List<Cliente> clientes = query.getResultList();
		
		assert(clientes.size()) > 0;
		
	}
	
	@Test
	@Order(6)
	public void testeFindClienteByNome() {
		String nome = "Mefistófolis" + new Random().nextInt();
		
		Conta conta = entityManager.find(Conta.class, 1L);	
		assertNotNull(conta);
		
		entityManager.persist(
				Cliente.builder()
				.nome(nome)
				.conta(conta)
				.build());
		
		TypedQuery<Cliente> query = entityManager.getEntityManager()
				.createQuery("select c from Cliente c where c.nome = :nome", Cliente.class);
		
		query.setParameter("nome", nome);
		
		List<Cliente> clientes = query.getResultList();
		
		assert(clientes.size()) > 0;
		
	}

}
