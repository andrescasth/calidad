/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufidelitas.data;

import com.ufidelitas.modelo.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author deiv
 */
public class GestionProducto {

    ConexionBD conexion = new ConexionBD();
    ResultSet resultado = null;

    public void consultarProducto() {
        try {
            conexion.setConexion();
            conexion.setConsulta("SELECT PROD_ID, PROD_NOMBRE, PROD_DESCRIPCION, PROD_PRECIO, PROD_RESTAURANTE_ID FROM tab_producto");
            resultado = conexion.getResultado();

            while (resultado.next()) {
                int prodCodigo = resultado.getInt("PROD_CODIGO");
                String prodNombre = resultado.getString("PROD_NOMBRE");
                String prodDescripcion = resultado.getString("PROD_DESCRIPCION");
                double prodPrecio = resultado.getDouble("PROD_PRECIO");
                int prodCantidad = resultado.getInt("PROD_CANTIDAD");

                System.out.println("Código: " + prodCodigo + " - Nombre: " + prodNombre + " - Descripción: " + prodDescripcion
                        + " - Precio: " + prodPrecio + " - Cantidad: " + prodCantidad);
            }

            conexion.cerrarConexion();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public Producto consultarProductoPorCodigo(int codigo) {
        Producto menu = null;

        try {
            conexion.setConexion();
            conexion.setConsulta("SELECT PROD_ID, PROD_NOMBRE, PROD_DESCRIPCION, PROD_PRECIO, PROD_RESTAURANTE_ID FROM tab_producto"
                    + " WHERE PROD_ID = " + codigo);
            resultado = conexion.getResultado();

            while (resultado.next()) {
                int id = resultado.getInt("PROD_ID");
                int idRestaurante = resultado.getInt("PROD_RESTAURANTE_ID");
                String nombre = resultado.getString("PROD_NOMBRE");
                String descripcion = resultado.getString("PROD_DESCRIPCION");
                double precio = resultado.getDouble("PROD_PRECIO");

                menu = new Producto(id, idRestaurante, nombre, descripcion, precio);
            }

            conexion.cerrarConexion();
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return menu;
    }

    public void insertarProducto(int codigo, String nombre, String descripcion, double precio, int cantidad) {
        try {
            conexion.setConexion();
            conexion.setConsulta("INSERT INTO TAB_PRODUCTO(PROD_ID, PROD_NOMBRE, PROD_DESCRIPCION, PROD_PRECIO, PROD_CANTIDAD) values(?,?,?,?,?)");
            conexion.getConsulta().setInt(1, codigo);
            conexion.getConsulta().setString(2, nombre);
            conexion.getConsulta().setString(3, descripcion);
            conexion.getConsulta().setDouble(4, precio);
            conexion.getConsulta().setInt(5, cantidad);

            if (conexion.getConsulta().executeUpdate() > 0) {
                //Respuesta positiva
                System.out.println("Se insertó el producto!");
            } else {
                System.out.println("Error en la inserción!");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
