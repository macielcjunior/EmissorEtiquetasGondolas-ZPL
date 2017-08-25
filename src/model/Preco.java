/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Maciel C. Junior
 */
public class Preco {
    
    private String codlj;
    private Double preco;
    private Double poferta;
    
   public Preco(){
       
   }          

    public Preco(String codlj, Double preco, Double poferta) {
        this.codlj = codlj;
        this.preco = preco;
        this.poferta = poferta;
    }

    public String getCodlj() {
        return codlj;
    }

    public void setCodlj(String codlj) {
        this.codlj = codlj;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getPoferta() {
        return poferta;
    }

    public void setPoferta(Double poferta) {
        this.poferta = poferta;
    }
   
    
}
