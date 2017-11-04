/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author deividnn
 */
import entidades.Produto;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import util.hibernate.HibernateSF;
import util.hibernate.Transactional;

public class ProdutoDAO implements Serializable {

    @Inject
    private HibernateSF sessionFactory;

    @Transactional
    public void salvar(Produto produto) throws ErroException {
        try {
            sessionFactory.getCurrentSession().save(produto);
        } catch (Exception e) {
            throw new ErroException("erro ao salvar.");
        }
    }

    @Transactional
    public void atualizar(Produto produto) throws ErroException {
        try {
        sessionFactory.getCurrentSession().update(produto);
        } catch (Exception e) {
            throw new ErroException("erro ao atualizar.");
        }
    }

    @Transactional
    public void excluir(Produto produto) throws ErroException {
        try{
        sessionFactory.getCurrentSession().delete(produto);
        } catch (Exception e) {
            throw new ErroException("erro ao excluir.");
        }
    }

    public Produto pegar(int id) {
        Produto produto = sessionFactory.getCurrentSession().get(Produto.class, id);
        return produto;
    }

    @SuppressWarnings("unchecked")
    public List<Produto> listar() {
        List<Produto> produtos = sessionFactory.getCurrentSession().
                createQuery("select vo from Produto vo order by id desc").list();
        return produtos;
    }

}
