### 1. Subindo container Docker do Oracle
````
docker pull epiclabs/docker-oracle-xe-11g				
docker run -dit --restart unless-stopped -p 49161:1521 -e ORACLE_ALLOW_REMOTE=true epiclabs/docker-oracle-xe-11g -v dbvolume:/u01/app/oracle/oradata oracle/database:11.2.0.2-xe				
"hostname: localhost
port: 49161
sid: xe
username: system
password: oracle"				
````

### 2. configurando dependência do Oracle
````
baixar o jar ojdbc8.jar e depois rodar o comando abaixo:

mvn install:install-file -Dfile=/Users/nickkrasborges/ambiente/ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar
````

#### 2.1. Gerar senha master criptografada
````
mvn -emp <colocar aqui senah da máquina>
````

#### 2.2. criar/configurar security-setting.xml
````
<?xml version="1.0" encoding="UTF-8"?>

  <settingsSecurity> 
	  <master>{incluir senha master criptografada no passo 1.1}</master> 
  </settingsSecurity> 
````
#### 2.3. gerar senha da Oracle criptograda
````
mvn -ep <colocar aqui senha do usuário da Oracle>
````

#### 2.4. configurar setting.xml
````
<servers>
	<server>
	  <id>maven.oracle.com </id>
	  <username>nickkborges@hotmail.com</username>
	  <password>{incluir senha oracle criptografada no passo 1.3}</password>
	<configuration>
	  <basicAuthScope>
	    <host>ANY </host>
	    <port>ANY </port>
	    <realm>OAM 11g </realm>
	  </basicAuthScope>
	  <httpConfiguration>
	    <all>
	    <params>
	      <property>
	        <name>http.protocol.allow-circular-redirects</name>
	        <value>%b,true </value>
	      </property>
	    </params>
	    </all>
	  </httpConfiguration>
	</configuration>
	</server>
</servers>
````

#### 2.5. Configurar o pom
````
<dependency>
	<groupId>com.oracle.jdbc</groupId>
	<artifactId>ojdbc8</artifactId>
	<version>12.2.0.1</version>
</dependency>


<repositories>
	<repository>
		<id>maven.oracle.com</id>
		<name>oracle-maven-repo</name>
		<url>https://maven.oracle.com</url>
		<layout>default</layout>
		<releases>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</releases>
	</repository>
</repositories>
<pluginRepositories>
	<pluginRepository>
		<id>maven.oracle.com</id>
		<name>oracle-maven-repo</name>
		<url>https://maven.oracle.com</url>
		<layout>default</layout>
		<releases>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</releases>
	</pluginRepository>
</pluginRepositories>

````

### 3. Padrões do projeto

#### 3.1 Tipos de Estados de uma Entity

 * **Transient:** quando a transação ainda não foi iniciada, a entida(objeto) ainda não conhece a tabela do banco.
 * **Managed:** quando a transação foi iniciada, a entidade já conhece a tabela e a transação ainda não foi fechada.
 * **Dispatched:** quando a transação foi finalizada, a entida(objeto) já não conhece mais a tabela do banco.
 * **Removed:** quando o objeto é removido da tabela.


#### 3.2 Tipos de Relacionamentos

 * **@OneToOne:** Relacionamente um para um, aqui deve-se utilizar a anotação @JoinColumn(unique = true), por padrão o jpa não cria restrição de valor para não inserir registros repetidos.
 * **@OneToMany:** Ralacionamento um para muitos
 * **@ManyToOne:** Ralacionamento muitos para um, aqui é criado uma tabela de relacionamento entre as duas tabelas.
 * **@ManyToMany:** Relacionamento muitos para muitos, aqui é criado uma tabela de relacionamento entre as duas tabelas.
 * **@JoinColumn(unique = true):** jpa informa para o banco para criar uma contraint para não inserir registros duplicados na tabela filha.
 * **Observações:** 
    * Qualquer relacionamento para-muitos (*ToMany) é LAZY por padrão.
    * Qualquer relacionamento para-um (*ToOne) é EAGER por padrão.
    * Qualquer relacionamento muitos-para (ManyTo*) (cria uma terceira tabela de relacionamento para ligar as outras duas tabelas, através das suas respectivas chaves primarias).
    * Para alterar o nome da terceira tabela criada com os joins, basta usar a anotação **@JoinTable** 
	
   
 #### 3.3 Relacionamento Bidirecional
 * Quando dois objetos são mapeados com @OneToOne, exemplo:
````
	@Entity
	public class Aluno {
	
	    @OneToOne
	    private Perfil perfil; 
	}
	
	@Entity
	public class Perfil {
	
	    @OneToOne(mappedBy="perfil") //Esse atributo causa a criação da chave estrangeira(PERFIL_ID) na tabela Aluno. 
	    private Aluno aluno;
	} 
````

#### 3.4 Tipos de Busca
 * **find()** e o **getReference()** da interface EntityManager permitem que recuperemos uma instância pela id. Após isso eles a retornam como Managed. A diferença entre find() e getReference() é que o getReference() é Lazy.
 * **JPQL:**
    * **...ToMany:** por padrão é **lazy** e trás apenas a referência da lista, "select p from Produto p join p.categorias c where c.id = :pCategoriId"
    * **fetch:** trás a lista em si, "select p from Produto p join fetch p.categorias c where c.id = :pCategoriId"

#### 3.5 Criteria
 * **CriteriaBuilder:** Responsável por criar a instância do Criteria e também é onde é setado a condições da clausula Where da query (equal, like, between e etc...).
 * **CriteriaQuery<Produto>:** Responsável por criar a query do tipoEntity produtoEntity, contém as clausulas (select, from e where).
 * **Root<Produto>:** Responsável por identificar os elementos do objeto Produto, como (atributos e joins).
 * **EntityGraphs:** Uma alternativa às queries planejadas é utilizar este recurso. Podemos dizer à JPA quais relacionamentos queremos trazer nas "queries".
    * o *javax.persistence.loadgraph* que trata os atributos configurados no grafo como Eager e os demais seguem seu FetchType padrão. A outra opção seria usar *javax.persistence.fetchgraph* que trata os atributos não especificados como lazy.
    ````
        @NamedEntityGraphs({
          @NamedEntityGraph(name = "produtoComCategoria", 
                            attributeNodes = { 
                                  @NamedAttributeNode("categorias") 
                            }) 
        })
        @Entity
        public class Produto {
            ...
    ````
    ````
        public List<Produto> getProdutos() {
                return em.createQuery("select distinct p from Produto p", Produto.class)
                        .setHint("javax.persistence.loadgraph", em.getEntityGraph("produtoComCategoria"))
                        .getResultList();
        } 
    ````
 * **@DynamicUpdate:** permite que na query estejam apenas os campos que foram alterados. Exemplo:
    ````
    update Produto set nome=? where id=? 
    ````

#### 4. Pool de conexão
 * Para gerenciar a escalabilidade da aplicação, é utilizada uma técninca de criar uma conexão por aplicação e também criar demais conexões para os determinados acessos(para cada chamada) conecido como pool conection. Assim temos um pool de conexões abertas esperando as requisições.
    * Uma aplicação com apenas uma conexão para ser compartilhada entre todas as requisições não é uma boa solução, pois uma requisição sempre deve esperar a outra acabar de usá-la para prosseguir com o seu processamento, o que prejudica a escalabilidade da aplicação.
 * O DataSource padrão (DriverManagerDataSource) abre uma conexão a cada chamada. Mais detalhes em http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/jdbc/datasource/DriverManagerDataSource.html
 * C3PO: implementação padrão do hibernate para pool connection.
    ````
       @Configuration
       public class ConfigPoolConnectionC3P0 {
            ...
    ````

#### 5. Lock Optimistic e Pessimistic
 * **Pessimistic: ** seria impedir que aconteça o conflito e pedir para o banco de dados travar o registro que está sendo atualizado. Porém, em como consequência um grande gargalo de escalabilidade, já que no momento em que travamos um registro os demais ficarão esperando a liberação do mesmo para realizar a edição.
	````
	entityManager.getTransaction().begin();
	
	Produto produtoEntity = entityManager.find(Produto.class, 1);
	entityManager.lock(produtoDoEM1,LockModeType.PESSIMISTIC_WRITE);
	
	produtoEntity.setNome("xyz");
	````
 * **Optimistic: ** Neste caso devemos permitir que os conflitos ocorram, nunca travar o registro, tratar e lançar a exception.
 	````
 	@Entity
	public class Produto {
	    // ...
	
	    @Version //lock Optimistic, a cada update gera uma versão no registro do banco. A primeira requisição vai atualizar, a última será impedida de atualizar.
	    private Integer versao;
	
	    // ...
	}
 	````

#### 6. Cache
 * **cache de primeiro nível: ** impede de carregar a entidade duas vezes do banco mas é feito por EntityManager, se houver mais de um EntityManager não será usado o cache, ou seja se houver uma requisição feita por cada um dos EntityManager na mesma tabela, não será utilizado o cache. Exemplo:
 	````
 	//exemplo cache
 	EntityManager em = emf.createEntityManager();
    
    Produto produto1 = em.find(Produto.class, 1); //busca da base
    Produto produto2 = em.find(Produto.class, 1); //busca do cache
 	````
 	````
 	//exemplo ignorando cache
 	EntityManager em = emf.createEntityManager();
    EntityManager em2 = emf.createEntityManager();

    Produto produto1 = em.find(Produto.class, 1); //busca da base
    Produto produto2 = em2.find(Produto.class, 1); //busca da base também, pois é outro EntityManager
 	````
 	
  * **cache de segundo nível: ** o cache é compartilhado por todos os EntityManager's.
  	* **Observação:** 
  	* **READ_ONLY** que abre mão dos locks e sincronizações por não permitir alterações no estado do objeto. 
  	* **READ_WRITE** usado para alterações de estado onde ocorram simultaneamente, mais custosa pois utiliza vários lock's e garante que a última versão da entidade no banco seja a mesma que está no cache.
  	* quando houver relacionamento, deve-se utilizar **@Cache** nas entitidades relacionandas também, pois o cache armazena apenas os ID's na lista de registros ao invés da lista de objetos e continuará realizando a consulta em banco.
  * **Exemplo:**  
  	````
  	public class JpaConfigurator {
	    ...
	    @Bean
	    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) {
	
	        ...
	        props.setProperty("hibernate.cache.use_second_level_cache", "true"); //habilita o cache
	        props.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory"); //provider
	        props.setProperty("hibernate.cache.use_query_cache", "true"); //habilita o cache para as querys de consulta
	        ...
	    }
	}
	
	@Entity
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public class Produto {
		...
		@ManyToMany
		@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
		private List<Categoria> categorias = new ArrayList<>();
		...

	}
	
	@Entity
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public class Categoria {
		...
	}
	
	
	public List<Produto> getProdutos(String nome, Integer categoriaId, Integer lojaId) {
	    ...
	
	    TypedQuery<Produto> typedQuery = em.createQuery(query.where(conjuncao));
	    
	    //EhCache por default não armazena as consultas em cache, devemos informar na consulta.
	    typedQuery.setHint("org.hibernate.cacheable", "true");
	
	    return typedQuery.getResultList();
	}

  	````
  
 #### 7. Hibernate Statistics
  * Exemplo, conferir a existência de uma eventual connection leak devido a uma conexão que ficou aberta, ou, um número muito grande miss do cache que pode significar que devemos reconfigurar a estratégia de cache.
  * Habilitando o statistics:
  	````
  	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) {
	    ...    
	    props.setProperty("hibernate.generate_statistics", "true");
	    ...
	}
	
	@Bean
	public Statistics statistics(EntityManagerFactory emf) { 
	    return emf.unwrap(SessionFactory.class).getStatistics();
	}
  	````
  
  