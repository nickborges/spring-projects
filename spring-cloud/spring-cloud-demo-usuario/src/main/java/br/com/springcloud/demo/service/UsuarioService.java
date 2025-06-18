package br.com.springcloud.demo.service;

import br.com.springcloud.demo.entity.UsuarioEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import br.com.springcloud.demo.client.LojaClient;
import br.com.springcloud.demo.client.PedidoDTO;
import br.com.springcloud.demo.domain.UsuarioDTO;
import br.com.springcloud.demo.repository.UsuarioRepository;

import java.util.Arrays;

@Service
public class UsuarioService {

    @Autowired
    RestTemplate client;
    @Autowired
    DiscoveryClient eurekaDiscoveryClient;

    @Autowired
    LojaClient lojaClient;

    @Autowired
    UsuarioRepository usuarioRepository;

    @HystrixCommand(threadPoolKey = "buscarPorIdThreadPool")
    public UsuarioDTO.Usuario buscarPorId(Long id) {
        try {
            Thread.sleep(1000000L);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        System.out.println(">>> executando buscarPorId >>>");
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElse(new UsuarioEntity());
        return new UsuarioDTO.Usuario(usuarioEntity);
    }

    @HystrixCommand(fallbackMethod = "buscarTodosFallback", threadPoolKey = "buscarTodosThreadPool")
    public UsuarioDTO buscarTodos() {
        System.out.println(">>> executando buscarTodos >>>");
        pedidoRestTemplate();
        pedidoFeing();
        return UsuarioDTO.mapper(usuarioRepository.findAll());
    }

    @HystrixCommand(threadPoolKey = "criarThreadPool")
    public UsuarioDTO.Usuario criar(UsuarioDTO.Usuario usuario) {
        System.out.println(">>> executando criar >>>");
        UsuarioEntity usuarioEntity = usuarioRepository.save(new UsuarioEntity(usuario.getNome(), usuario.getIdade()));
        return UsuarioDTO.Usuario
                .builder()
                .id(usuarioEntity.getId())
                .idade(usuarioEntity.getIdade())
                .nome(usuarioEntity.getNome())
                .build();
    }

    //Client side load ballancer com Ribbon
    public void pedidoRestTemplate() {

        System.out.println(">>> RestTemplate >>>");
        ResponseEntity<PedidoDTO> exchange = client.exchange("http://loja/todos", HttpMethod.GET, null, PedidoDTO.class);
        System.out.println(exchange.getBody().getPedidos());

        //imprime todas as instâncias da API loja
        eurekaDiscoveryClient.getInstances("loja")
                .stream()
                .forEach(f -> {
                    System.out.println("Host: " + f.getHost() + " Porta: " + f.getPort());
                });
    }

    //Client side load ballancer com Feign
    public void pedidoFeing() {

        System.out.println(">>> FeignCLient >>>");
        ResponseEntity<PedidoDTO> pedidos = lojaClient.buscarTodos();
        System.out.println(pedidos.getBody().getPedidos());

    }

    public UsuarioDTO buscarTodosFallback() {

        return UsuarioDTO.builder()
                .usuarios(Arrays.asList(
                        UsuarioDTO.Usuario.builder()
                                .id(12L)
                                .nome("Teste")
                                .idade(26)
                                .build())
                ).build();
    }
}
