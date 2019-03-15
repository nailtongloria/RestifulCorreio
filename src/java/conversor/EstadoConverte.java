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
import modelo.Estado;

/**
 *
 * @author nailton viana gloria
 */
@Named("estadoConverter")
@SessionScoped
public class EstadoConverte implements Converter {

    @Inject
    EntityManager em;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Long id = Long.valueOf(value);
        Estado estado = em.find(Estado.class, id);
        System.out.println("estado: " + estado.toString());
        return estado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Estado estado = (Estado) value;
        return String.valueOf(estado.getId());

    }

}
