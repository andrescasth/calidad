/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufidelitas.data;

import com.ufidelitas.modelo.Restaurante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isaac
 */
public class GestionRestaurantes {

    ConexionBD conexion = new ConexionBD();
    ResultSet resultado = null;

    public List<Restaurante> colsultarRestaurantes() {
        List<Restaurante> restaurantes = new ArrayList<>();
        try {

            conexion.setConexion();
            conexion.setConsulta(
                    "SELECT RES_ID, RES_NOMBRE, RES_DIRECCION, RES_CAT_ID FROM tab_restaurante");

            resultado = conexion.getResultado();
            while (resultado.next()) {
                int id = resultado.getInt("RES_ID");
                String nombre = resultado.getString("RES_NOMBRE");
                String ubicacion = resultado.getString("RES_DIRECCION");
                int categoria = resultado.getInt("RES_CAT_ID");

                restaurantes.add(new Restaurante(id, nombre, ubicacion, categoria));
            }

            conexion.cerrarConexion();
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return restaurantes;
    }
}
