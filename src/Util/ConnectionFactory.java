/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Maciel
 */
public class ConnectionFactory {
    
    // declaração de variáveis de classe

    private static final String usuario = "userDB";
    private static final String senha = "PasswordDB";
    private static final String url = "jdbc:oracle:thin:@HostDB:HostString"; 

    // método para obter uma conexão com o BD
    public static Connection getConnection() throws Exception {
        try 
        {
            // carrega a definição da classe do Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // obtem a conexão
            return DriverManager.getConnection(url, usuario, senha);

        } 
        catch (Exception exception) {
            throw new Exception("Erro ao conectar com o BD: " + exception.getMessage());
        }
    }

    // método para fechar a conexão com 3 parâmetros
    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        close(conn, stmt, rs);
    }

    // método para fechar a conexão com 2 parâmetros
    public static void closeConnection(Connection conn, Statement stmt) throws Exception {
        close(conn, stmt, null);
    }

    // método para fechar a conexão com 1 parâmetro
    public static void closeConnection(Connection conn) throws Exception {
        close(conn, null, null);
    }

    // método privado para fechar a conexão
    private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        catch(Exception exception){ 
            throw new Exception("Erro no fechamento da conexão: " + exception.getMessage());
        }
    }
}
