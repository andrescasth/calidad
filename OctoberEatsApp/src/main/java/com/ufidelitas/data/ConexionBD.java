/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufidelitas.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author deiv
 */
public class ConexionBD {
    Connection conexion = null;
    PreparedStatement consulta = null;
    ResultSet resultado = null;
    String url = "jdbc:mysql://localhost:3306/octobereats";
    String username = "root";
    String password = "Rojo2001.";
    
    public void setConexion()
    {
        try
        {
            this.conexion = DriverManager.getConnection(url, username, password);
        }
        catch(SQLException error)
        {
            error.printStackTrace();
        }
    }
    
    public void setConsulta(String consulta)
    {
        try
        {
            this.consulta = conexion.prepareStatement(consulta);
        }
        catch(SQLException error)
        {
            error.printStackTrace();
        }
    }
    
    public ResultSet getResultado()
    {
        try
        {
            return consulta.executeQuery();
        }
        catch(SQLException error)
        {
            error.printStackTrace();
            return null;
        }
    }
    
    public PreparedStatement getConsulta()
    {
        return consulta;
    }
    
    public void cerrarConexion()
    {
        //Cerramos y limpiamos los resultados
        if(resultado != null)
        {
            try
            {
                resultado.close();
            }
            catch(SQLException error)
            {
                error.printStackTrace();
            }
        }
        //Cerramos y limpiamos las consultas
        if(consulta != null)
        {
            try
            {
                consulta.close();
            }
            catch(SQLException error)
            {
                error.printStackTrace();
            }
        }
        //Cerramos y limpiamos la conexión
        if(conexion != null)
        {
            try
            {
                conexion.close();
            }
            catch(SQLException error)
            {
                error.printStackTrace();
            }
        }
    }
}
