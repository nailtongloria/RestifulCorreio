/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilrestiful;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.bind.JAXBException;

/**
 *
 * @author nailton viana gloria
 */
public class Teste6 {
    public static void main(String[] args) {
      ServicoRestiful servico= new ServicoRestiful();
        System.out.println("cidades "+servico.listaCidades("AM"));
      
        
    }
    
}
