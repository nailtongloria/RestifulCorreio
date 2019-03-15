/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.Pessoa;

/**
 *
 * @author Administrador
 */
@ViewScoped
@ManagedBean
public class PessoaBeanDetalhes {

    private Pessoa pessoa;
    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = (HttpSession) request.getSession();
        pessoa = (Pessoa) session.getAttribute("pessoa");
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String chamaPaginaPesquisar() {
        return "pesquisa-pessoa";
    }

    public String chamaPaginaEditar() {
        return "editar-pessoa";
    }

   public String retornaDataNascimento() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(pessoa.getData().getTime());
    }

}
