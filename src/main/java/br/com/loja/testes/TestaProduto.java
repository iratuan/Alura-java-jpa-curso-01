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
        em.getTransaction().begin();
        em.persist(celular);
        em.getTransaction().commit();
        em.close();
    }
}
