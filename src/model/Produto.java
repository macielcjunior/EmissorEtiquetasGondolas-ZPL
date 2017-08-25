/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DecimalFormat;

/**
 *
 * @author Maciel C. Junior
 */
public class Produto {
 
   private String codigo;
   private String ean;
   private String descri;
   private double valor;

   
    DecimalFormat cd = new DecimalFormat("0,0");
    
    
   
   public Produto(){
   }

    public Produto(String codigo, String ean, String descri, double valor) {
        this.codigo = codigo;
        this.ean = ean;
        this.descri = descri;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

 
}