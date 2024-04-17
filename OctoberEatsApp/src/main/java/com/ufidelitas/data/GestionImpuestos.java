/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufidelitas.data;
import com.ufidelitas.modelo.Impuesto;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author isaac
 */
public class GestionImpuestos {

    ConexionBD conexion = new ConexionBD();
    ResultSet resultado = null;

    public Impuesto consultarImpuesto(int codigo) {
        Impuesto impuesto = null;

        try {
            conexion.setConexion();
            conexion.setConsulta("SELECT IMP_ID, IMP_NOMBRE, IMP_VALOR FROM tab_tipo_impuesto"
                    + " WHERE IMP_ID = " + codigo);
            resultado = conexion.getResultado();

            while (resultado.next()) {
                int id = resultado.getInt("IMP_ID");
                int valor = resultado.getInt("IMP_VALOR");
                String descripcion = resultado.getString("IMP_NOMBRE");

               impuesto = new Impuesto(id, descripcion, valor);
            }

            conexion.cerrarConexion();
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return impuesto;
    }
}
