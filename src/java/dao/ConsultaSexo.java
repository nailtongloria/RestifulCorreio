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
import modelo.Sexo;

/**
 *
 * @author nailton viana gloria
 */
@Stateless
public class ConsultaSexo {

    @PersistenceContext(name = "ProjetoFinalDadPU")
    EntityManager em;

    public List<Sexo> listar() {
        Query query = em.createQuery("select s  from Sexo  s");
        List<Sexo> sexos = query.getResultList();
        return sexos;
    }

    public Sexo visualizar(long id) {
        return em.find(Sexo.class, id);
    }

}
