/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ConsultaEstado;
import dao.ConsultaPessoa;
import dao.ConsultaSexo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.Cidade;
import modelo.Estado;
import modelo.Logradouro;
import modelo.Pessoa;
import modelo.Sexo;
import modelorestiful.Cep;
import utilrestiful.ServicoRestiful;
import utilrestiful.ServicoRestifulAltera;

/**
 *
 * @author nailton viana gloria
 */
@ManagedBean(name = "pessoaBeanAlterar")
@SessionScoped
public class PessoaBeanAlterar {

    private Date data = new Date();
    private Logradouro l = new Logradouro();
    private Estado estado = new Estado();
    private Pessoa pessoaSelecionada = new Pessoa();
    private String cep;

    @EJB
    private ConsultaEstado estadoDao;
    @EJB
    private ConsultaSexo sexoDao;
    @EJB
    private ConsultaPessoa pessoaDao;
    private List<Estado> estados = new ArrayList<Estado>();
    private Pessoa pessoa = new Pessoa();
    private Cidade cidade = new Cidade();
    private Sexo sexo = new Sexo();
    private List<Sexo> sexos = new ArrayList<Sexo>();
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();
   // private Cep cep1 = new Cep();

    public PessoaBeanAlterar() {
    }

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = (HttpSession) request.getSession();
        pessoa = (Pessoa) session.getAttribute("pessoa");
        cep = pessoa.getLogradouro().getCep();
        l = pessoa.getLogradouro();
        estado = pessoa.getLogradouro().getCidade().getEstado();
        cidade = pessoa.getLogradouro().getCidade();
        data = pessoa.getData().getTime();
        sexo = pessoa.getSexo();
        l.setLogradouro(pessoa.getLogradouro().getLogradouro());
        l.setBairro(pessoa.getLogradouro().getBairro());
        l.setCep(pessoa.getLogradouro().getCep());
        l.getCidade().setIbge(pessoa.getLogradouro().getCidade().getIbge());
        l.getCidade().setNome(pessoa.getLogradouro().getCidade().getNome());
        /*cep1.setLogradouro(pessoa.getLogradouro().getLogradouro());
         cep1.setBairro(pessoa.getLogradouro().getBairro());
         cep1.setCep(pessoa.getLogradouro().getCep());
         cep1.setIbge(pessoa.getLogradouro().getCidade().getIbge());
         cep1.setLocalidade(pessoa.getLogradouro().getCidade().getNome());*/

    }

    public String chamaPaginaEditar() {

        return "TelaAlteraPessoa2";
    }

    public void consultaCep() {
        ServicoRestiful servico = new ServicoRestiful();
        System.out.println("ceps " + cep);
        l = servico.encontraCep(cep);
    }

    public String alterarPessoa() {
        pessoaDao.Alterar(pessoa.getCidade(), pessoa.getLogradouro(), pessoa);
        this.cidade = new Cidade();
        this.l = new Logradouro();
        this.pessoa = new Pessoa();
        return "alteracaoEfetuadaSucesso";
    }

    public Calendar retornaData() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar;
    }

    public int retornaIdade() {
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        int anoNascimento = pessoa.getData().get(Calendar.YEAR);
        int idade = anoAtual - anoNascimento;
        return idade;
    }

    public String retornaDataNascimento() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(pessoa.getData().getTime());
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Logradouro getL() {
        return l;
    }

    public void setL(Logradouro l) {
        this.l = l;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }

    public ConsultaEstado getEstadoDao() {
        return estadoDao;
    }

    public void setEstadoDao(ConsultaEstado estadoDao) {
        this.estadoDao = estadoDao;
    }

    public ConsultaSexo getSexoDao() {
        return sexoDao;
    }

    public void setSexoDao(ConsultaSexo sexoDao) {
        this.sexoDao = sexoDao;
    }

    public ConsultaPessoa getPessoaDao() {
        return pessoaDao;
    }

    public void setPessoaDao(ConsultaPessoa pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public List<Sexo> getSexos() {
        return sexos;
    }

    public void setSexos(List<Sexo> sexos) {
        this.sexos = sexos;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
