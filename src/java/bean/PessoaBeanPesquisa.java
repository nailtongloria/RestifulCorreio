/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ConsultaPessoa;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Pessoa;

/**
 *
 * @author Administrador
 */

@SessionScoped
@ManagedBean
public class PessoaBeanPesquisa {
    private Pessoa pessoa = new Pessoa();
    private Pessoa pessoaSelecionada = new Pessoa();
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    
    @EJB
    private ConsultaPessoa dao;
    
    @PostConstruct
    public void init(){
        listarPessoa();
        
    }
    
    public void listarPessoa(){
        pessoas=dao.listarPessoas();
    }
    public void pesquisarPessoa(){
        pessoas=dao.getPessoas(pessoa.getNome());
    }
        
   

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }
    
  
    
    public String chamaPaginaDetalhe(){
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) contexto.getExternalContext().getSession(false);
        session.setAttribute("pessoa", pessoaSelecionada);
        return "TelaDetalhePessoa";
    }
    
    public String chamaPaginaCadastrar(){
        return "adiciona-pessoa";
    }

    public ConsultaPessoa getDao() {
        return dao;
    }

    public void setDao(ConsultaPessoa dao) {
        this.dao = dao;
    }
    
}
