/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Cidade;
import modelo.Logradouro;
import modelo.Pessoa;

/**
 *
 * @author nailton viana gloria
 */
@Stateless

public class ConsultaPessoa {

    @PersistenceContext(name = "ProjetoFinalDadPU")
    private EntityManager em;

    public void inserir( Logradouro l, Pessoa p) {

        //em.persist(c);
        em.persist(l);
        em.persist(p);

    }

     public void Alterar(Cidade c,Logradouro l,Pessoa p){
       // Cliente cliente = em.find(Cliente.class,c.getId());
        Cidade cidade =em.find(Cidade.class,c.getId());
        em.merge(cidade);
        Logradouro logradouro = em.find(Logradouro.class,l.getId());
        // Endereco endereco = em.find(Endereco.class, e.getId());
         em.merge(logradouro);
        Pessoa pessoa = em.find(Pessoa.class, p.getId());
       //  Peca peca= em.find(Peca.class,p.getId());
         em.merge(pessoa);
    }
    
      

    public List<Pessoa> listarPessoas() {
        Query query = em.createQuery("select p from  Pessoa  p");
        List<Pessoa> pessoas = query.getResultList();
        return pessoas;

    }

    public List<Pessoa> getPessoas(String pessoa) {
        String jpql = "select p from Pessoa  p  WHERE p.nome  like :buscaPessoa";
        Query query = em.createQuery(jpql);
        query.setParameter("buscaPessoa", "%" + pessoa + "%");
        return query.getResultList();

    }

    public void excluir(Pessoa pessoa) {
        Pessoa p = em.find(Pessoa.class, pessoa.getId());
        em.remove(p);
        Logradouro l = em.find(Logradouro.class, pessoa.getLogradouro().getId());
        em.remove(l);
        Cidade c = em.find(Cidade.class, pessoa.getCidade().getId());
        em.remove(c);

    }

    public Pessoa visualizar(long id) {
        return em.find(Pessoa.class, id);
    }

}
