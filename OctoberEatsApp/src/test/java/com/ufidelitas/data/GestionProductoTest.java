/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ufidelitas.data;

import com.ufidelitas.modelo.Producto;
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
public class GestionProductoTest {
    
    public GestionProductoTest() {
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

    
    /**
     * Test of insertarProducto method, of class GestionProducto.
     */
     @Test
    public void testInsertarProducto() {
        System.out.println("insertarProducto");
        int codigo = 1;
        String nombre = "Producto de prueba";
        String descripcion = "Descripción del producto de prueba";
        double precio = 25.99;
        int cantidad = 10;
        
        GestionProducto instance = new GestionProducto();
        instance.insertarProducto(codigo, nombre, descripcion, precio, cantidad);
        
        System.out.println("Agregado con exito");
        System.out.println("Producto insertado:");
        System.out.println("Código: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Precio: " + precio);
        System.out.println("Cantidad: " + cantidad);
    }
    
}
