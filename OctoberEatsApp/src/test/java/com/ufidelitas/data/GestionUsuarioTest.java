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
public class GestionUsuarioTest {
    
    public GestionUsuarioTest() {
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
    public void testInsertarUsuario() {
        System.out.println("insertarUsuario");
        int id = 1; // Podrías asignar un valor específico para el id si lo necesitas
        String nombre = "Andres Castillo";
        String direccion = "Dirección de prueba";
        String correo = "andrescasth@gmail.com";
        String contrasenia = "123";
        Usuario usuario = new Usuario(id, nombre, direccion, correo, contrasenia);
        GestionUsuario instance = new GestionUsuario();
        instance.insertarUsuario(usuario);
        
        
        
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Dirección: " + direccion);
        System.out.println("Correo: " + correo);
        System.out.println("Contraseña: " + contrasenia);
    }




    
}
