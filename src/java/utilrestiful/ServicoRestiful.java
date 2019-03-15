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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import javax.xml.bind.Unmarshaller;
import modelo.Cidade;

import modelo.ListaMunicipio;

import modelo.Logradouro;
import modelo.Municipio;

import modelorestiful.Cep;

/**
 *
 * @author nailton viana gloria
 */
public class ServicoRestiful {

    public Logradouro encontraCep(String cep) {

        Logradouro logradouro = new Logradouro();
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

                    Cidade c = new Cidade();
                    Cep cep1 = (Cep) jaxbUnmarshaller.unmarshal(br);
                    c.setNome(cep1.getLocalidade());
                    c.setIbge(cep1.getIbge());
                    logradouro.setCidade(c);
                    logradouro.setBairro(cep1.getBairro());
                    logradouro.setLogradouro(cep1.getLogradouro());
                    logradouro.setCep(cep1.getCep());
                    logradouro.setUf(cep1.getUf());
                } catch (JAXBException ex) {
                    Logger.getLogger(ServicoRestiful.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (IOException ex) {
                Logger.getLogger(ServicoRestiful.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(ServicoRestiful.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logradouro;

    }
    
    public List<Municipio>listaCidades(String uf){
        ListaMunicipio municipios = null;
        try {
            
            URL url = new URL("http://dadosabertos.almg.gov.br/ws/brasil/localidades/ufs/"+uf+"/municipios");
            HttpURLConnection connection =(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/xml");
            JAXBContext contexto = JAXBContext.newInstance(ListaMunicipio.class);
            Unmarshaller geradorObj=contexto.createUnmarshaller();
            System.out.println("fazendo unmarshal");
            municipios=(ListaMunicipio)geradorObj.unmarshal(connection.getInputStream());
            connection.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServicoRestiful.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServicoRestiful.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(ServicoRestiful.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return municipios.getMunicipios();
    }

}
