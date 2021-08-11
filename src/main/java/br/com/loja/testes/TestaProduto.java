package br.com.loja.testes;

import br.com.loja.dao.CategoriaDAO;
import br.com.loja.dao.ProdutoDAO;
import br.com.loja.model.Categoria;
import br.com.loja.model.Produto;

import java.math.BigDecimal;
import java.util.List;

public class TestaProduto {
    public static void main(String[] args) throws Exception {

        // Cria uma categoria
        Categoria categoria = new Categoria("Celulares");

        // Cria um produto
        Produto celular1 = new Produto("IPhone X1","Celular muito caro", new BigDecimal("12000"), categoria);
        Produto celular2 = new Produto("IPhone X2","Celular muito caro", new BigDecimal("12000"), categoria);
        Produto celular3 = new Produto("IPhone X3","Celular muito caro", new BigDecimal("12000"), categoria);

        // Istanciando os DAOs
        ProdutoDAO produtoDAO = new ProdutoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        // Inserido a categoria
        categoriaDAO.create(categoria);
        produtoDAO.create(celular1);
        produtoDAO.create(celular2);
        produtoDAO.create(celular3);

        System.out.println("Quantidade de produtos: " + produtoDAO.findAll().size());
        for (Produto p: produtoDAO.findAll()) {
            System.out.println("Produto " + "ID: "+ p.getId() + " - " + p.getNome());
        }

        System.out.println("Exibindo o produto #1: " + produtoDAO.getOne(1L).getNome());

        produtoDAO.create(celular1);
        Produto produtoRetornado = produtoDAO.getOne(1L);
        produtoRetornado.setNome("Produto atualizado");
        Produto p = produtoDAO.update(produtoRetornado);

        System.out.println("Atualizando um produto: " + p.getNome());

        List<Produto> produtosBuscados = produtoDAO.searchByName("IPhone");
        System.out.println("Pesquisando produtos por nome e retornando " + produtosBuscados.size() + " resultados");

        try {
            produtoDAO.removeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Quantidade de produtos: " + produtoDAO.findAll().size());
    }

}
