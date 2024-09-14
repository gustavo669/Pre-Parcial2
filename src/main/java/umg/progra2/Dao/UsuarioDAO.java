package umg.progra2.Dao;

import umg.progra2.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un nuevo usuario
    public void crearUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO tb_usuarios (carne, nombre, correo, seccion, telegramid, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, usuario.getCarne());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getSeccion());
            ps.setLong(5, usuario.getTelegramId());
            ps.setString(6, usuario.getActivo());
            ps.executeUpdate();
        }
    }

    // Método para leer un usuario por ID
    public Usuario leerUsuario(int idUsuario) throws SQLException {
        String query = "SELECT * FROM tb_usuarios WHERE idusuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("idusuario"),
                            rs.getString("carne"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("seccion"),
                            rs.getLong("telegramid"),
                            rs.getString("activo")
                    );
                }
            }
        }
        return null;
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE tb_usuarios SET carne = ?, nombre = ?, correo = ?, seccion = ?, telegramid = ?, activo = ? WHERE idusuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, usuario.getCarne());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getSeccion());
            ps.setLong(5, usuario.getTelegramId());
            ps.setString(6, usuario.getActivo());
            ps.setInt(7, usuario.getIdUsuario());
            ps.executeUpdate();
        }
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int idUsuario) throws SQLException {
        String query = "DELETE FROM tb_usuarios WHERE idusuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
        }
    }

    // Método para listar todos los usuarios
    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String query = "SELECT * FROM tb_usuarios";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("idusuario"),
                        rs.getString("carne"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("seccion"),
                        rs.getLong("telegramid"),
                        rs.getString("activo")
                );
                listaUsuarios.add(usuario);
            }
        }
        return listaUsuarios;
    }
}
