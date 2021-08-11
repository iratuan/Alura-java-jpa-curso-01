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
        Produto celular1 = new Produto("IPhone X","Celular muito caro", new BigDecimal("12000"), categoria);
        Produto celular2 = new Produto("IPhone X","Celular muito caro", new BigDecimal("12000"), categoria);
        Produto celular3 = new Produto("IPhone X","Celular muito caro", new BigDecimal("12000"), categoria);

        // Istanciando os DAOs
        ProdutoDAO produtoDAO = new ProdutoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        // Inserido a categoria
        categoriaDAO.create(categoria);
        produtoDAO.create(celular1);
        produtoDAO.create(celular2);
        produtoDAO.create(celular3);

        System.out.println("Quantidade de produtos: " + produtoDAO.findAll().size());

        try {
            produtoDAO.removeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Quantidade de produtos: " + produtoDAO.findAll().size());
    }

}
