# Curso de persistência com jpa - introdução ao Hibernate



### Repositório do curso Persistência com JPA - Introdução ao Hibernate da Alura.



### Notas importantes:

**JDBC** é a especificação que dita as regras para implementação de comunicação com bancos de dados. Exemplo: quando você baixa um driver **jdbc** para **postgres**, você está baixando uma implementação jdbc específica para o **postgres**, e assim por diante.



- **Hibernate** surgiu como uma alternativa ao **JDBC** e **EJB2 **e foi criado em **2001** por **Gavin King**
- **JPA** é uma especificação para padronização de **ORM** e foi lançada em **2006**
- Além da especificação JPA, você utiliza uma implementação como **Hibernate**, **EclipseLink**, **OpenJPA** que, implementam a especificação.

### Passos importantes

No arquivo **pom.xml** insira as seguintes dependências:

```xml
<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-entitymanager</artifactId>
			<version>5.4.27.Final</version>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<version>1.4.200</version>
		</dependency>
```

Note que utilizaremos o H2 como banco de dados. Caso deseje utilizar qualquer outro banco de dados, basta pesquisar no repositório do **maven** as dependências correspondentes.

### Criando o arquivo persistence.xml

Dentro da pasta src/main/resources/META-INF, crie um arquivo chamado persistence.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2"
    xmlns="http://xmlns.jcp.org/xml/ns/persistence"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">

    <persistence-unit name="loja-pu" transaction-type="RESOURCE_LOCAL">
        <properties>
            <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:h2:mem:loja"/>
            <property name="javax.persistence.jdbc.user" value="sa"/>
            <property name="javax.persistence.jdbc.password" value=""/>

            <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
        </properties>
    </persistence-unit>
</persistence>
```



### Criando a primeira entidade

Dentro de **src/main/br/com/loja/models** crie um arquivo chamado Produto.java com o seguinte conteúdo:

```java
package br.com.loja.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    public Produto(){

    }

    public Produto(long id, String nome, String descricao, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}

```

Criada a classe que representa sua entidade no banco de dados, adicione a mesma no arquivo persistence.xml

```xml
<class>br.com.loja.models.Produto</class>
```

Cria uma classe de teste para inserir um produto

```java
package br.com.loja.testes;

import br.com.loja.models.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class TestaProduto {
    public static void main(String[] args) {
        // Cria um produto
        Produto celular = new Produto("IPhone X","Celular muito caro", new BigDecimal("12000"));

        // Cria a entidade de persistencia
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja-pu");
        EntityManager em = emf.createEntityManager();

        // Persiste o objeto
        em.persist(celular);
    }
}

```

Aluno: **@iratuan**

