package br.com.loja.models;

import br.com.loja.dao.CategoriaDAO;
import br.com.loja.dao.ProdutoDAO;
import br.com.loja.model.Categoria;
import br.com.loja.model.Produto;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void deveRetornarUmaListaVazia() throws Exception {
        produtoDAO.removeAll();
        assertEquals("Deve retornar uma lista vazia", 0, produtoDAO.findAll().size());
    }
}
