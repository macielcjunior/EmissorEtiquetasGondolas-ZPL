/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

/**
 *
 * @author Maciel C. Junior
 */
public class ConsultaProdutoDAO {
    
    private Connection conn;

    // construtor
    public ConsultaProdutoDAO() throws Exception {
        try{
            this.conn = ConnectionFactory.getConnection();
        }catch(Exception exception){
            throw new Exception(exception.getMessage());
        }
    }
       
    //Consulta por Nivel de preço - Lojas Guaxupé
        public List ConsultaPLU(String plu) throws Exception{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = this.conn.prepareStatement("Select  git_cod_item||git_digito,git_descricao,git_codigo_ean13 from aa3citem\n" +
                                            "where git_cod_item ||git_digito =  ?");
            
            ps.setString(1, plu);
            
            rs = ps.executeQuery();
            
            
            
            List<Produto> lista = new ArrayList<Produto>();
            
            while(rs.next()){
                Produto prod = new Produto();
                prod.setCodigo(rs.getString(1));
                prod.setDescri(rs.getString(2));
                prod.setEan(rs.getString(3));
                lista.add(prod);
            }
            System.out.println(rs.getString(1));
            
            return lista;
        }catch(SQLException sqlException){
            throw new Exception("Erro ao consultar dados: " +
                    sqlException);
        }finally{
            ConnectionFactory.closeConnection(conn, ps, rs);
        }

    }
    
    
   public List EAN(String ean) throws Exception{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = this.conn.prepareStatement("Select aa3citem.git_cod_item||aa3citem.git_digito, aa3citem.git_descricao, "
                    + "aa3ccean.ean_cod_ean from aa3citem, aa3ccean "
                    + "where aa3ccean.ean_cod_pro_alt = aa3citem.git_cod_item ||aa3citem.git_digito "
                    + "and aa3ccean.ean_cod_ean = ?");
            
            ps.setString(1, ean);
            
            rs = ps.executeQuery();
            
            List<Produto> lista = new ArrayList<Produto>();
            
            while(rs.next()){
                Produto prod = new Produto();
                prod.setCodigo(rs.getString(1));
                prod.setDescri(rs.getString(2));
                prod.setEan(rs.getString(3));
                lista.add(prod);
            
//            System.out.println(rs.getDouble(1));
//            System.out.println(rs.getString(2));
//            System.out.println(rs.getString(3));

            }
            
            return lista;
        }catch(SQLException sqlException){
            throw new Exception("Erro ao consultar dados: " +
                    sqlException);
        }finally{
            ConnectionFactory.closeConnection(conn, ps, rs);
        }
        
   }
}


