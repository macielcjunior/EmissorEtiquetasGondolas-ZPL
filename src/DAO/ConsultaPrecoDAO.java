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
import model.Preco;

/**
 *
 * @author Maciel C. Junior
 */
public class ConsultaPrecoDAO {
    

private Connection conn;
    
    // construtor
    public ConsultaPrecoDAO() throws Exception {
        try{
            this.conn = ConnectionFactory.getConnection();
        }catch(Exception exception){
            throw new Exception(exception.getMessage());
        }
    }
    
    
            // método para consultar planocontas através do código
    public List PRECO(String data, String lj, String prod) throws Exception{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
//        System.out.println(data);
        //System.out.println(lj);
        //System.out.println(prod);
        
   
    try{
            ps = this.conn.prepareStatement("SELECT CH_PRI_LOJA,\n" +
"                     F_CALCSTPR(1, GIT_COD_ITEM, ?, TRUNC(CH_PRI_LOJA / 10)) AS PRECO_VIG, \n" +
"                     F_CALCSTPR(3, GIT_COD_ITEM, ?, TRUNC(CH_PRI_LOJA / 10)) AS PRECO_OFERTA \n" +
"                     FROM AA3CITEM, AA2CLINH, AA2CTIPO WHERE CH_PRI_LOJA = ? \n" +
"                     AND GIT_COD_ITEM||GIT_DIGITO = ? \n" +
"                     AND   CH_PRI_LINHA = GIT_LINHA\n" +
"                     AND   TIP_CODIGO   (+  ) = TRUNC(CH_PRI_LOJA / 10)\n" +
"                     AND   TIP_DIGITO   (+) = MOD(CH_PRI_LOJA , 10)\n" +
"                     ORDER BY CH_PRI_LOJA");
         
            
            ps.setString(1,data);
            ps.setString(2,data);
            ps.setString(3,lj);
            ps.setString(4,prod);
            
            
            rs = ps.executeQuery();
            
            List<Preco> lista = new ArrayList<Preco>();                                
            
            while(rs.next()){
                Preco preco = new Preco();
                preco.setCodlj(rs.getString(1));
                preco.setPreco(rs.getDouble(2));
                preco.setPoferta(rs.getDouble(3));
                // instancia um novo cliente e insere na lista
                lista.add(preco);           
                
               // System.out.println(rs.getString(1));
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