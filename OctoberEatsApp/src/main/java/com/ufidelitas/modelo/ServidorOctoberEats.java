package com.ufidelitas.modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author isaac
 */
public class ServidorOctoberEats {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;
    
    private ServerSocket servidor;
    private Socket cliente;
    
    private DataOutputStream salida;
    private DataInputStream entrada;
    
    private String mensajeRecibido = "";
    
    public void iniciarServidor()
    {
        Scanner lectura = new Scanner(System.in);
        //Inicializamos el servidor y abrimos el puerto para la conexión de los clientes
        try
        {
            servidor = new ServerSocket(PUERTO);
            cliente = new Socket();
            
            System.out.println("Esperando conexión...");
            
            //Recibimos a los clientes
            
            cliente = servidor.accept();
            
            System.out.println("Se conectó un cliente...");
            
            //Atender al cliente: Preguntar ¿Qué necesita?
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
            
            //Preparamos la respuesta para el cliente y establecemos el espacio de comunicación
            String mensajeEnviado = "";
            
            while(!mensajeEnviado.equals("SALIR"))
            {
                //Me voy a comunicar con el cliente, siempre y cuando, no se escriba la palabra SALIR
                mensajeRecibido = entrada.readUTF();
                System.out.println("Mensaje del cliente: " + mensajeRecibido);
                System.out.println();
                if(mensajeRecibido.equals("Hola"))
                {
                    mensajeEnviado = "Hola";
                    salida.writeUTF(mensajeEnviado);
                }
                else
                {
                    System.out.println("Digite una respuesta para el cliente: ");
                    mensajeEnviado = lectura.nextLine();
                    salida.writeUTF(mensajeEnviado);
                }
            }
        }
        catch(IOException error)
        {
            JOptionPane.showMessageDialog(null, "Se presentó un error: " + error);
        }
    }
}