package br.com.loja.dao;

import br.com.loja.model.Produto;

public class ProdutoDAO extends BaseDAO<Produto> {
    public ProdutoDAO() {
        super(Produto.class);
    }
}
