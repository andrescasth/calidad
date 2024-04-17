/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ufidelitas.data;

import com.ufidelitas.modelo.Usuario;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author andre
 */
public class GestionUsuarioTestEliminar {
    
    public GestionUsuarioTestEliminar() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    
  

   @Test
    public void testEliminarUsuario() {
        System.out.println("eliminarUsuario");
        int id = 2; // Id del usuario a eliminar
        GestionUsuario instance = new GestionUsuario();
        instance.eliminarUsuario(id);
        System.out.println("Eliminado con exito");
        // Aquí no necesitas llamar a fail(), ya que el test terminará correctamente si no hay excepciones durante la ejecución.
    }

    
}
