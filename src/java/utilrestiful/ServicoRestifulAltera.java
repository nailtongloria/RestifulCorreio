/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilrestiful;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import modelo.Cidade;
import modelo.Logradouro;
import modelo.Pessoa;
import modelorestiful.Cep;

/**
 *
 * @author nailton viana gloria
 */
public class ServicoRestifulAltera {
    
    Pessoa pessoa = new Pessoa();

    public Pessoa encontraCep(String cep) {
        
        try {
            
            String browser = "https://viacep.com.br/ws/" + cep + "/xml/";
            URL url = new URL(browser);
            HttpURLConnection con;
            try {
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Accept", "application/xml");
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                JAXBContext jaxbContext;
                try {
                    jaxbContext = JAXBContext.newInstance(Cep.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    Cep cep1 = (Cep) jaxbUnmarshaller.unmarshal(br);
                    Cidade c = new Cidade();
                    c.setNome(cep1.getLocalidade());
                    c.setIbge(cep1.getIbge());
                    Logradouro l = new Logradouro();
                    l.setCep(cep1.getCep());
                    l.setBairro(cep1.getBairro());
                    l.setLogradouro(cep1.getLogradouro());
                    l.setUf(cep1.getUf());
                    l.setCidade(c);
                    pessoa.setLogradouro(l);
                    
                } catch (JAXBException ex) {
                    Logger.getLogger(ServicoRestiful.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ServicoRestiful.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServicoRestiful.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pessoa;
        
    }
    
}
