package br.com.loja.models;

import br.com.loja.dao.CategoriaDAO;
import br.com.loja.dao.ProdutoDAO;
import br.com.loja.model.Categoria;
import br.com.loja.model.Produto;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProdutoTest {

    private Produto produto;
    private Categoria categoria;
    private ProdutoDAO produtoDAO;
    private CategoriaDAO categoriaDAO;

    @Before
    public void init() {
        produtoDAO = new ProdutoDAO();
        categoriaDAO = new CategoriaDAO();
        // Cria uma categoria
        categoria = new Categoria("Celulares");
        // Cria um produto
        produto = new Produto("IPhone X", "Celular muito caro", new BigDecimal("12000"), categoria);
    }

    @Test
    public void deveInserirUmProdutoComCategoria() throws Exception {
        produtoDAO.removeAll();
        categoriaDAO.create(categoria);
        produtoDAO.create(produto);
        assertTrue("A lista de produtos, pós insert, deve ser maior que zero: ", produtoDAO.findAll().size() > 0);
    }

    @Test
    public void deveRetornarUmProduto(){
        categoriaDAO.create(categoria);
        produtoDAO.create(produto);
        assertTrue("Deve resgatar o produto com id #1: " , produtoDAO.getOne(1L).getId() == 1);
    }

    @Test
    public void deveRetornarUmaListaVazia() throws Exception {
        produtoDAO.removeAll();
        assertEquals("Deve retornar uma lista vazia", 0, produtoDAO.findAll().size());
    }

    @Test
    public void deveAtualizarUmProduto() throws Exception {
        categoriaDAO.create(categoria);
        Produto p = produtoDAO.create(produto);
        p.setNome("Produto atualizado");
        p = produtoDAO.update(p);
        assertNotNull("Deve retornar um produto atualizado", p);
        assertTrue("Deve retornar um produto com título atualizado","Produto atualizado".equals(p.getNome()));
    }

    @Test
    public void devePesquisarUmProdutoPorNome(){
        categoriaDAO.create(categoria);
        produtoDAO.create(produto);
        assertTrue("Deve retornar, pelo menos, um produto com o título informado: ", produtoDAO.searchByName(produto.getNome()).size() > 0);
    }
}
