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

/**
 *
 * @author nailton viana gloria
 */
@Stateless

public class ConsultaCidade   {
    
    @PersistenceContext(name="ProjetoFinalDadPU")
    EntityManager em;
    
    public List<Cidade> listar() {
            Query query = em.createQuery("select c  from Cidade  c");
        List<Cidade> cidades = query.getResultList();
         return cidades;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
        public Cidade visualizar(long id)    {
        return em.find(Cidade.class, id);
    }

}
