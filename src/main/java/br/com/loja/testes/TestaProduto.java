package br.com.loja.testes;

import br.com.loja.dao.CategoriaDAO;
import br.com.loja.dao.ProdutoDAO;
import br.com.loja.model.Categoria;
import br.com.loja.model.Produto;

import java.math.BigDecimal;

public class TestaProduto {
    public static void main(String[] args) {

        // Cria uma categoria
        Categoria categoria = new Categoria("Celulares");

        // Cria um produto
        Produto celular = new Produto("IPhone X","Celular muito caro", new BigDecimal("12000"), categoria);

        // Istanciando os DAOs
        ProdutoDAO produtoDAO = new ProdutoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        // Inserido a categoria
        categoriaDAO.create(categoria);
        produtoDAO.create(celular);
    }
}
