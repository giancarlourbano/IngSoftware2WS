/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.sw2;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Administrador
 */
@WebService(serviceName = "Articulo_Nuevo")
public class Articulo_Nuevo {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Articulo")
    public boolean Articulo(@WebParam(name = "nombre") String nombre, 
            @WebParam(name = "precio") float precio, 
            @WebParam(name = "descripcion") String descripcion, 
            @WebParam(name = "ruta") String ruta, 
            @WebParam(name = "usuario") String usuario) {
        //TODO write your implementation code here:
        boolean ok=false;
        String insert = "insert into Imagenes(nombre,descripcion,precio,imagen,usuario) values(?,?,?,?,?)";
        String select= "Select dni from usuario where usuario='"+usuario+"'";
        
         Connection cn=null;
        FileInputStream fis = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        int dni=0;
        try {

        File file = new File(ruta);
        fis = new FileInputStream(file);
        
        rs=ps.executeQuery(select);
        while(rs.next()){
            dni=rs.getInt(1);
        }
        String update="update articulos set imagen=load_file('D:\\"+ruta+"') where usuario="+dni;
        ps = cn.prepareStatement(update);
        //ps.setString(1, nombre);
        //ps.setString(2, descripcion);
        //ps.setFloat(3, precio);
        //ps.setBinaryStream(4,fis,(int)file.length());
        //ps.setInt(5, dni);
        ps.executeUpdate();
        cn.commit();
        return true;
        } catch (Exception ex) {
            System.out.println("Error de conexion: "+ex);
        }finally{
            try {
            ps.close();
            fis.close();
            cn.close();
            } catch (Exception ex) {
                System.out.println("Error 2: "+ex);
            }
        }        


       return ok;
    }
}
