/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import bean.PessoaBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import modelo.Municipio;

/**
 *
 * @author Administrador
 */
@ViewScoped
@ManagedBean
public class MunicipioConverte implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        PessoaBean bean = (PessoaBean) context.getApplication().evaluateExpressionGet(context, "#{pessoaBean}", PessoaBean.class);

        if (value == null || value.isEmpty()) {
            return null;
        }
        return bean.consultaCidade(Long.parseLong(value));
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        Municipio municipio = (Municipio) value;
        if (municipio == null || municipio.getId() == 0) {
            return null;
        }
        return String.valueOf(municipio.getId());
    }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

}
