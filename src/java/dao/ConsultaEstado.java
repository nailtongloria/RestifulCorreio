/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Estado;

/**
 *
 * @author nailton viana gloria
 */
@Stateless

public class ConsultaEstado {

    @PersistenceContext(name = "ProjetoFinalDadPU")
    EntityManager em;

    public List<Estado> listar() {
        Query query = em.createQuery("select e  from Estado  e");
        List<Estado> estados = query.getResultList();
        return estados;
    }

    public Estado visualizar(long id) {
        return em.find(Estado.class, id);
    }

}
