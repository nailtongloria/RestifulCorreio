/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import modelo.Sexo;

/**
 *
 * @author nailton viana gloria
 */

@Named("sexoConverter")
@SessionScoped
public class SexoConverte implements Converter  {

     @Inject
    EntityManager em;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          Long id = Long.valueOf(value);
         Sexo sexo = em.find(Sexo.class, id);
        System.out.println("sexo: " + sexo.toString());
        return  sexo;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         Sexo sexo = (Sexo) value;
        return String.valueOf(sexo.getId());
    }
    
}
