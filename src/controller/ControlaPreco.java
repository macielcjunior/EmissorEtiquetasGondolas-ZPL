/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ConsultaPrecoDAO;
import java.util.List;
import model.Preco;

/**
 *
 * @author Maciel C. Junior
 */
public class ControlaPreco {
 
    
    private ConsultaPrecoDAO consultaPrecoDAO;
    
        // método para pesquisar usuario pelo codigo
    public List pesquisar(String data, String lj, String prod) throws Exception{
        
        consultaPrecoDAO = new ConsultaPrecoDAO();
        
        List<Preco> lista = consultaPrecoDAO.PRECO(data, lj,prod);
        
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
}
