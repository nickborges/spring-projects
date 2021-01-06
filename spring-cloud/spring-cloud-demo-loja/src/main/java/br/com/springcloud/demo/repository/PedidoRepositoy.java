package br.com.springcloud.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.springcloud.demo.entity.PedidoEntity;

@Repository
public interface PedidoRepositoy extends JpaRepository<PedidoEntity, Long>{
	
}
