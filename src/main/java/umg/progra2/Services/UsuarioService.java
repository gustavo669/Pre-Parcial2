package umg.progra2.Services;

import umg.progra2.Dao.UsuarioDAO;
import umg.progra2.Model.Usuario;

import java.sql.Connection;
import java.util.List;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public UsuarioService(Connection connection) {
        this.usuarioDAO = new UsuarioDAO(connection);
    }

    public String crearUsuario(String carne, String nombre, String correo, String seccion, long telegramId, String activo) {
        try {
            Usuario usuario = new Usuario(0, carne, nombre, correo, seccion, telegramId, activo);
            usuarioDAO.crearUsuario(usuario);
            return "Usuario creado correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al crear el usuario: " + e.getMessage();
        }
    }

    public Usuario leerUsuario(int idUsuario) {
        try {
            return usuarioDAO.leerUsuario(idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String actualizarUsuario(int idUsuario, String carne, String nombre, String correo, String seccion, long telegramId, String activo) {
        try {
            Usuario usuario = new Usuario(idUsuario, carne, nombre, correo, seccion, telegramId, activo);
            usuarioDAO.actualizarUsuario(usuario);
            return "Usuario actualizado correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al actualizar el usuario: " + e.getMessage();
        }
    }

    public String eliminarUsuario(int idUsuario) {
        try {
            usuarioDAO.eliminarUsuario(idUsuario);
            return "Usuario eliminado correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al eliminar el usuario: " + e.getMessage();
        }
    }

    public List<Usuario> listarUsuarios() {
        try {
            return usuarioDAO.listarUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
