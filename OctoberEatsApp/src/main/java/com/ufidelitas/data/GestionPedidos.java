/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufidelitas.data;

import com.ufidelitas.modelo.Impuesto;
import com.ufidelitas.modelo.Producto;
import com.ufidelitas.modelo.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author isaac
 */
public class GestionPedidos {

    ConexionBD conexion = new ConexionBD();
    ResultSet resultado = null;

    public void insertarPedido(double total, List<Producto> carrito, Usuario usuario, int metodoPago, Impuesto impuesto) {
        try {
            conexion.setConexion();
            conexion.setConsulta(
                    "Insert into tab_pedidos(PED_USU_ID, PED_MET_PAGO_ID, DET_PED_TOTAL) values(?,?,?)");
            conexion.getConsulta().setInt(1, usuario.getId());
            conexion.getConsulta().setInt(2, metodoPago);
            conexion.getConsulta().setDouble(3, total);

            if (conexion.getConsulta().executeUpdate() > 0) {
                int idPedido = obtenerUltimoPedido();
                insertarDetalle(carrito, idPedido, impuesto);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido realizar el pedido", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void insertarDetalle(List<Producto> carrito, int idPedido, Impuesto impuesto) {
        try {

            for (Producto menu : carrito) {
                conexion.setConexion();
                conexion.setConsulta(
                        "Insert into tab_pedidos_detalle(DET_PED_ID, DET_PED_PROD_ID, DET_PED_PRECIO, DET_PED_CANTIDAD, DET_PED_SUBTOTAL, DET_PED_IMP_ID) values(?,?,?,?,?,?)");
                conexion.getConsulta().setInt(1, idPedido);
                conexion.getConsulta().setInt(2, menu.getId());
                conexion.getConsulta().setDouble(3, menu.getPrecio());
                conexion.getConsulta().setInt(4, menu.getCantidadAcomprar());
                 conexion.getConsulta().setDouble(5, menu.getSubTotal());
                conexion.getConsulta().setInt(6, impuesto.getId());

                if (conexion.getConsulta().executeUpdate() > 0) {
                    System.out.println("Se agrego el detalle");
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha podido realizar el pedido", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public int obtenerUltimoPedido() {
        int idPedido = 0;
        try {

            conexion.setConexion();
            conexion.setConsulta(
                    "SELECT PED_ID FROM tab_pedidos ORDER BY PED_ID DESC LIMIT 1;");

            resultado = conexion.getResultado();
            while (resultado.next()) {
                idPedido = resultado.getInt("PED_ID");
            }

            conexion.cerrarConexion();
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return idPedido;
    }
}
