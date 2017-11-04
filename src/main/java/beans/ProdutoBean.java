/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ProdutoDAO;
import entidades.Produto;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import util.jsf.FacesUtil;

/**
 *
 * @author deividnn
 */
@Named
@ViewScoped
public class ProdutoBean implements Serializable {

    @Inject
    ProdutoDAO produtoDAO;

    private Produto produto;
    private List<Produto> produtos;
    private boolean edicao;

    @PostConstruct
    public void init() {
        edicao = false;
        produtos = produtoDAO.listar();
    }

    public void salvar() {
        try {
            produto.setDatahora(Calendar.getInstance().getTime());
            produto.setDescricao(produto.getDescricao().toUpperCase());
            if (produto.getId() == null) {
                produtoDAO.salvar(produto);
                FacesUtil.mensagem("produto salvo");
                produtos = produtoDAO.listar();
            } else {
                produtoDAO.atualizar(produto);
                FacesUtil.mensagemAviso("produto atualizado");
                produtos = produtoDAO.listar();
            }
            edicao = false;
        } catch (Exception e) {
            FacesUtil.mensagemErro(e.toString());
        }
    }

    public void excluir(Produto p) {
        try {
            produtoDAO.excluir(p);
            FacesUtil.mensagemAviso("produto excluido");
            produtos = produtoDAO.listar();
        } catch (Exception e) {
            FacesUtil.mensagemErro(e.toString());
        }
    }

    public void editar() {
        produto = new Produto();
        edicao = true;
    }

    public void editar(Produto p) {
        produto = p;
        edicao = true;
    }

    public void cancelar() {
        edicao = false;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public boolean isEdicao() {
        return edicao;
    }

    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

}
