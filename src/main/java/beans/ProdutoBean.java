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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage("produto salvo"));
                produtos = produtoDAO.listar();
            } else {
                produtoDAO.atualizar(produto);
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage("produto atualizado"));
                produtos = produtoDAO.listar();
            }
            edicao = false;
        } catch (Exception e) {           
            FacesContext.getCurrentInstance().addMessage("", 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),e.toString()));
        }
    }

    public void excluir(Produto p) {
        try {
            produtoDAO.excluir(p);
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("produto excluido"));
            produtos = produtoDAO.listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),e.toString()));
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
