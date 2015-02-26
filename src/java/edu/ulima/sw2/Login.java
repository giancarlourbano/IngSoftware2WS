/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.sw2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Administrador
 */
@WebService(serviceName = "Login")
public class Login {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public boolean login(@WebParam(name = "usuario") String usuario, @WebParam(name = "clave") String clave) {
        //TODO write your implementation code here:
       Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String user="";
        String psw="";
        boolean ok=false;
        try {
            //TODO write your implementation code here:
            
            String url="Select usuario,clave from usuarios where usuario='"+usuario+"'";
            Class.forName("com.mysql.jdbc.Driver");
            cn=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/sw2","root","root");
            ps=cn.prepareStatement(url);
            rs=ps.executeQuery();
            while(rs.next()){
                user=rs.getString(1);
                psw=rs.getString(2);
                
                    ok=true;
            }
    
        } catch (Exception e) {
            try {
                cn.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error en la conexion"+ex);
            }
        }
        return ok;
    }
}
