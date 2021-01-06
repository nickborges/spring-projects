package br.com.sboot.jpa.repository.jpql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Random;
import javax.persistence.PersistenceException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import br.com.sboot.jpa.entity.Aluno;
import br.com.sboot.jpa.entity.Perfil;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RelacionamentoBidirecionalRepositoryTest {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	@Order(1)
	public void testeRelacionamentoBidirecional_exception() {
		
		Perfil perfil = Perfil.builder()
				.tipo("Estudante")
				.build();
		
		Aluno aluno = Aluno.builder()
				.nome("Mitador")
				.build();

		perfil.setAluno(aluno);
		aluno.setPerfil(perfil);
		
		entityManager.persist(perfil);
		entityManager.persist(aluno);
		
		var result = assertThrows(PersistenceException.class, () -> {
			entityManager.getEntityManager().getTransaction().commit();
		});
		
		assert(result.getCause().getMessage())
			.contains("org.hibernate.exception.ConstraintViolationException: could not execute statement");

	}
	
	@Test
	@Order(2)
	public void testeRelacionamentoBidirecional_success() {
		
		Perfil perfil = Perfil.builder()
				.tipo("Estudante" + new Random().nextInt())
				.build();
		
		Aluno aluno = Aluno.builder()
				.nome("Mitador" + new Random().nextInt())
				.build();

		perfil.setAluno(aluno);
		aluno.setPerfil(perfil);
		
		entityManager.persist(perfil);
		entityManager.persist(aluno);
		
		assertNotNull(perfil);
		
		assertNotNull(aluno);
		
		entityManager.getEntityManager().getTransaction().commit();

	}

}
