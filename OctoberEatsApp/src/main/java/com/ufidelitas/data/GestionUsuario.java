package com.ufidelitas.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufidelitas.modelo.Usuario;
import javax.swing.JOptionPane;

public class GestionUsuario {

    ConexionBD conexion = new ConexionBD();
    ResultSet resultado = null;

    public List<Usuario> consultarUsuarioPorCorreo(String correo) {
        List<Usuario> usuarios = new ArrayList<>();
        try {

            conexion.setConexion();
            conexion.setConsulta(
                    "SELECT USU_ID, USU_NOMBRE_USUARIO, USU_DIRECCION, USU_CORREO, USU_CONTRASENIA "
                    + "FROM tab_usuario "
                    + "WHERE USU_CORREO = '" + correo + "'");
            resultado = conexion.getResultado();
            while (resultado.next()) {
                int id = resultado.getInt("USU_ID");
                String nombre = resultado.getString("USU_NOMBRE_USUARIO");
                String direccion = resultado.getString("USU_DIRECCION");
                String correoUsuario = resultado.getString("USU_CORREO");
                String contrasenia = resultado.getString("USU_CONTRASENIA");

                usuarios.add(new Usuario(id, nombre, direccion, correoUsuario, contrasenia));

            }

            conexion.cerrarConexion();
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return usuarios;
    }

   public Usuario validarUsuario(String correo, String contrasenia) {
        Usuario usuario = null;
        try {

            conexion.setConexion();
            conexion.setConsulta(
                    "SELECT USU_ID, USU_NOMBRE_USUARIO, USU_DIRECCION, USU_CORREO, USU_CONTRASENIA "
                    + "FROM tab_usuario "
                    + "WHERE USU_CORREO = '" + correo + "' AND USU_CONTRASENIA = '" + contrasenia + "'");

            resultado = conexion.getResultado();
            while (resultado.next()) {
                int id = resultado.getInt("USU_ID");
                String nombre = resultado.getString("USU_NOMBRE_USUARIO");
                String direccion = resultado.getString("USU_DIRECCION");
                String correoUsuario = resultado.getString("USU_CORREO");
                String contraseniaUsuario = resultado.getString("USU_CONTRASENIA");

                usuario = new Usuario(id, nombre, direccion, correoUsuario, contraseniaUsuario);
            }

            conexion.cerrarConexion();
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return usuario;
    }

    public void insertarUsuario(Usuario usuario) {
        try {
            conexion.setConexion();
            conexion.setConsulta(
                    "Insert into tab_usuario(USU_NOMBRE_USUARIO, USU_DIRECCION, USU_CORREO, USU_CONTRASENIA) values(?,?,?,?)");
            conexion.getConsulta().setString(1, usuario.getNombre());
            conexion.getConsulta().setString(2, usuario.getDireccion());
            conexion.getConsulta().setString(3, usuario.getCorreo());
            conexion.getConsulta().setString(4, usuario.getContrasenia());

            if (conexion.getConsulta().executeUpdate() > 0) {
                // Respuesta positiva
                JOptionPane.showMessageDialog(null, "Se ha registrado en el sistema con éxito!", "Felicidades!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void eliminarUsuario(int id) {
        try {
            conexion.setConexion();
            conexion.setConsulta("Delete from TAB_USUARIO where USU_ID = " + id);
            if (conexion.getConsulta().executeUpdate() > 0) {
                // Se eliminó la información
                System.out.println("Se eliminó el usuario!");
            } else {
                System.out.println("Error en la ejecución del borrado!");
            }

            conexion.cerrarConexion();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void modificarUsuario(String parametro, String dato, int id) {
        try {
            conexion.setConexion();
            // System.out.println("Update Empleado set " + parametro + " = '" + dato + "'
            // where idEmpleado = " + id);
            conexion.setConsulta("Update TAB_USUARIO set " + parametro + " = '" + dato + "' where USU_ID = " + id);
            if (conexion.getConsulta().executeUpdate() > 0) {
                System.out.println("Usuario actualizado!");
            } else {
                System.out.println("Error en la actualización!");
            }
            conexion.cerrarConexion();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void modificarCorreo(String nuevoCorreo, int idUsuario) {
        modificarUsuario("USU_CORREO", nuevoCorreo, idUsuario);
    }

    public void modificarContrasenia(String nuevaContrasenia, int idUsuario) {
        modificarUsuario("USU_CONTRASENIA", nuevaContrasenia, idUsuario);
    }

    public boolean registrarUsuario(Usuario usuario) {
        List<Usuario> usuariosPorCorreo = consultarUsuarioPorCorreo(usuario.getCorreo());
        if (usuariosPorCorreo.isEmpty()) {
            insertarUsuario(usuario);
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe un usuario con este correo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
       public void consultarUsuarioPorCorreoAsync(String correo, Callback<List<Usuario>> callback) {
        new Thread(() -> {
            List<Usuario> usuarios = consultarUsuarioPorCorreo(correo);
            callback.onResult(usuarios);
        }).start();
    }

    public void validarUsuarioAsync(String correo, String contrasenia, Callback<Usuario> callback) {
        new Thread(() -> {
            Usuario usuario = validarUsuario(correo, contrasenia);
            callback.onResult(usuario);
        }).start();
    }
    
     public interface Callback<T> {
        void onResult(T result);
    }
}
