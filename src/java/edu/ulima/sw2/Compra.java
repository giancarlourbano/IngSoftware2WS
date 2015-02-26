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
@WebService(serviceName = "Compra")
public class Compra {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "compra")
    public boolean compra(@WebParam(name = "dni") Integer dni, @WebParam(name = "credito") Integer credito) {
        //TODO write your implementation code here:
        boolean ok=false;
        
        Connection cn=null;
        PreparedStatement ps=null;
        
        try {
            //TODO write your implementation code here:
            String select="Select creditos from usuarios where dni="+dni;
            ResultSet rs=null;
            int cred=0;
            String url="Update usuarios SET creditos=? where dni=?";
            Class.forName("com.mysql.jdbc.Driver");
            cn=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/sw2","root","root");
            
            ps=cn.prepareStatement(select);
            rs=ps.executeQuery();
            while(rs.next()){
                cred=rs.getInt(1);
            }
            
            
            
            ps=cn.prepareStatement(url);
            ps.setInt(1, credito+cred);
            ps.setInt(2, dni);
            
            ps.executeUpdate();
            
            ok=true;
            
            
        } catch (Exception e) {
            try {
                cn.close();
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Error en la conexion"+ex);
            }
        }
        
        
        
        return ok;
    }
}
