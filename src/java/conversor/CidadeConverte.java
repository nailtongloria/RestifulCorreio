/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import bean.PessoaBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import modelo.Cidade;

/**
 *
 * @author Administrador
 */
@Named("cidadeConverte")
@SessionScoped
public class CidadeConverte implements Converter {

    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         PessoaBean bean = (PessoaBean) context.getApplication().evaluateExpressionGet(context, "#{pessoaBean}", PessoaBean.class);

        if (value == null || value.isEmpty()) {
            return null;
        }
        return bean.consultaCidade(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Cidade cidade = (Cidade) value;
        if (cidade == null || cidade.getId() == 0) {
            return null;
        }
        return String.valueOf(cidade.getId());
    }

    

}
