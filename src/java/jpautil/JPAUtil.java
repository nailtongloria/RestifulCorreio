/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpautil;



import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author nailton viana gloria
 */
public class JPAUtil {
    @PersistenceUnit
    private EntityManagerFactory fabrica;
    
    @Produces
    @RequestScoped
    public EntityManager getEntityManager(){
      return fabrica.createEntityManager();
    }
    
    public void close(@Disposes EntityManager em){
       em.close();
    }
    
    
}
