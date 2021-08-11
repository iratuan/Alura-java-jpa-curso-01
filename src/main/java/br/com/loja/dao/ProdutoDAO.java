package br.com.loja.dao;

import br.com.loja.model.Produto;

import javax.persistence.Query;
import java.util.List;

public class ProdutoDAO extends BaseDAO<Produto> {
    public ProdutoDAO() {
        super(Produto.class);
    }

    public List<Produto> searchByName(String name) {
        final Query query = getEntityManager().createQuery("Select p from Produto p where p.nome LIKE CONCAT('%',:name,'%')");
        query.setParameter("name", name);
        return query.getResultList();
    }
}
