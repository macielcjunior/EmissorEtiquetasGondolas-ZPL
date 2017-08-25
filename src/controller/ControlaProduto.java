/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ConsultaProdutoDAO;
import java.util.List;
import model.Produto;

/**
 *
 * @author Maciel C. Junior
 */
public class ControlaProduto {
    
    private ConsultaProdutoDAO consultaprodutoDAO;
    
        // método para pesquisar produto pelo codigo
    public List PLU(String plu) throws Exception{
        
        consultaprodutoDAO = new ConsultaProdutoDAO();
        
        List<Produto> lista = consultaprodutoDAO.ConsultaPLU(plu);
        
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }

        public List consultaEAN(String ean) throws Exception{
        
        consultaprodutoDAO = new ConsultaProdutoDAO();
        
        List<Produto> lista = consultaprodutoDAO.EAN(ean);
        
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
     }
}
