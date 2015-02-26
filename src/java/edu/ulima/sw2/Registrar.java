/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.sw2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Administrador
 */
@WebService(serviceName = "Registrar")
public class Registrar {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "registrar")
    public boolean registrar(@WebParam(name = "dni") Integer dni, @WebParam(name = "nombre") String nombre,
            @WebParam(name = "usuario") String usuario, @WebParam(name = "clave") String clave) {
        Connection cn=null;
        PreparedStatement ps=null;
        
        boolean ok=false;
        try {
            //TODO write your implementation code here:
            
            String url="Insert into usuarios (dni,nombre,usuario,clave) values(?,?,?,?)";
            Class.forName("com.mysql.jdbc.Driver");
            cn=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/sw2","root","root");
            ps=cn.prepareStatement(url);
            ps.setInt(1, dni);
            ps.setString(2, nombre);
            ps.setString(3, usuario);
            ps.setString(4, clave);
            
            ok=true;
            
            ps.executeUpdate();
            
            
        } catch (Exception e) {
            try {
                cn.close();
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Erro en la conexion"+ex);
            }
        }
        return ok;
    }
    
}
