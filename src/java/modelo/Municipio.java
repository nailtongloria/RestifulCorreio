/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@XmlRootElement(name="municipio")
@XmlAccessorType (XmlAccessType.FIELD)
public class Municipio {

    private static final long serialVersionUID = 1l;
    private long id;
    private String nome;
    private String uf;
    private String codigoIbge9;

    public Municipio() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCodigoIbge9() {
        return codigoIbge9;
    }

    public void setCodigoIbge9(String codigoIbge9) {
        this.codigoIbge9 = codigoIbge9;
    }

    @Override
    public String toString() {
        return id + " " + nome + " " + uf + " " + codigoIbge9;
    }

}
