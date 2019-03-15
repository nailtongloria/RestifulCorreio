/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ConsultaEstado;
import dao.ConsultaPessoa;
import dao.ConsultaSexo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.Cidade;
import modelo.Estado;
import modelo.Logradouro;
import modelo.Municipio;

import modelo.Pessoa;
import modelo.Sexo;
import utilrestiful.ServicoRestiful;
import utilrestiful.ServicoRestifulAltera;

/**
 *
 * @author nailton viana gloria
 */
@ManagedBean(name = "pessoaBean")
@SessionScoped
public class PessoaBean implements Serializable {

    private Date data = new Date();
    private Logradouro l = new Logradouro();
    private Estado estado = new Estado();
    private Pessoa pessoaSelecionada = new Pessoa();

    @EJB
    private ConsultaEstado estadoDao;
    @EJB
    private ConsultaSexo sexoDao;
    @EJB
    private ConsultaPessoa pessoaDao;
    private List<Estado> estados = new ArrayList<Estado>();
    private Pessoa pessoa = new Pessoa();
    private Cidade cidade = new Cidade();

    private Municipio muncipio = new Municipio();
    private List<Municipio> municipios = new ArrayList<Municipio>();
    private List<Cidade> cidades = new ArrayList<Cidade>();
    private Sexo sexo = new Sexo();
    private List<Sexo> sexos = new ArrayList<Sexo>();
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    @PostConstruct
    public void init() {

        this.sexos = sexoDao.listar();
        listarPessoas();

        //chamaPaginaDetalhe();
    }

    public void procurarCidade(String nome) {
        for (Municipio m : municipios) {
            if (m.getNome().equalsIgnoreCase(nome)) {
                muncipio = m;
            }
        }
    }

    public void procurarEstado(String uf) {
        for (Estado e : estados) {
            if (e.getSigla().equalsIgnoreCase(uf)) {
                estado = e;
            }
        }
    }

    public void consultaCep() {
        ServicoRestiful servico = new ServicoRestiful();
        //System.out.println("ceps uf  " +l.getCep()+" "+ l.getUf()+" "+l.getCidade());
        // listarCidades(l.getUf());
        l = servico.encontraCep(l.getCep());
        System.out.println("ceps uf  " + l.getCep() + " " + l.getUf() + " " + l.getCidade());
        listarEstado();
        listarCidades(l.getUf());
        procurarCidade(l.getCidade().getNome());
        // System.out.println(l.get);
        procurarEstado(l.getUf());

    }

    public void listarEstado() {
        this.estados = estadoDao.listar();
    }

    public void listarCidades(String uf) {
        ServicoRestiful servico = new ServicoRestiful();
        municipios = servico.listaCidades(uf);

    }

    public void listarPessoas() {
        this.pessoas = pessoaDao.listarPessoas();

    }

    public void pesquisarPessoas() {
        this.pessoas = pessoaDao.getPessoas(pessoa.getNome());
    }

    public String salvar() {

        cidade.setEstado(estado);
        cidade.setNome(muncipio.getNome());
        cidade.setIbge(muncipio.getCodigoIbge9());
        cidade.setEstado(estado);
        l.setCidade(cidade);
        l.setEstado(estado);
        pessoa.setCidade(cidade);
        pessoa.setData(retornaData());
        pessoa.setIdade(String.valueOf(retornaIdade()));
        pessoa.setSexo(sexo);
        pessoa.setLogradouro(l);

        pessoaDao.inserir(l, pessoa);

        cidade = new Cidade();
        l = new Logradouro();
        pessoa = new Pessoa();
        return "CadastroEfetuadoSucesso? faces-redirect=true";

    }

    public Municipio consultaCidade(long id) {
        Municipio municipio = null;
        for (Municipio m : municipios) {
            if (m.getId() == id) {
                municipio = m;
            }
        }
        return municipio;
    }

    public String excluir(Pessoa pessoa) {

        System.out.println("excluida  " + pessoa);

        pessoaDao.excluir(pessoa);

        return "TelaPesquisaPessoa";

    }

    public String alterar(Pessoa p) {

        pessoaDao.Alterar(cidade, l, pessoa);
        this.cidade = new Cidade();
        this.l = new Logradouro();
        this.pessoa = new Pessoa();
        return "AlteracaoEfetuadaSucesso";
    }

    public String chamaPaginaDetalhe() {

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("pessoa", this.pessoaSelecionada);
        return "TelaDetalhePessoa";
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

  

    public PessoaBean() {

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

    public ConsultaEstado getEstadoDao() {
        return estadoDao;
    }

    public void setEstadoDao(ConsultaEstado estadoDao) {
        this.estadoDao = estadoDao;
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

    public ConsultaSexo getSexoDao() {
        return sexoDao;
    }

    public void setSexoDao(ConsultaSexo sexoDao) {
        this.sexoDao = sexoDao;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ConsultaPessoa getPessoaDao() {
        return pessoaDao;
    }

    public void setPessoaDao(ConsultaPessoa pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Municipio getMuncipio() {
        return muncipio;
    }

    public void setMuncipio(Municipio muncipio) {
        this.muncipio = muncipio;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

}
