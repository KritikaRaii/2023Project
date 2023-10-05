/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kriti
 */
package my.contacteditor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class DBAccess {
    private static final String CONN_URL = "jdbc:mysql://computing.gfmat.org:3306";
    //private=only accesible among DBAccess class
    //final=constant
    private static final String DB_NAME = "2022_KRai_test";
    private static final String USER = "2022d_KRai";
    private static final String PASS = "";
    
    public static boolean sqlTestDBConnection(){
        boolean connection;
        try(Connection con = DriverManager.getConnection(CONN_URL + DB_NAME, USER, PASS)){
            System.out.println("CONNECTION MADE!");
            connection = true;
            con.close();
        }catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..."+ e.getMessage());
            connection = false;
        }
        return connection;
    }
    
    public static void sqlExecution (){
        try (Connection con = DriverManager.getConnection (CONN_URL + DB_NAME,USER,PASS)){
            System.out.println("CONNECTION MADE!");
            String sqlStatement = insertContacts("'Chris'","'071234'","'chris@gmail.com'");
            try (Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..."+ e.getMessage());
        }
    }
    
    public static ResultSet getdata() throws SQLException { //get data from DB
        String sql = "SELECT * FROM contacts";
        Connection con = DriverManager.getConnection(CONN_URL + DB_NAME, USER, PASS);
        
        Statement st = con.createStatement();
        ResultSet rs = con.executeQuery(sql);
        
        return rs;
    }
}
