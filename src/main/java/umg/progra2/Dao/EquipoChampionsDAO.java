package umg.progra2.Dao;
import umg.progra2.Model.EquipoChampions;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoChampionsDAO {
    private Connection connection;

    public EquipoChampionsDAO(Connection connection) {
        this.connection = connection;
    }

    // Crear un nuevo equipo
    public boolean crearEquipo(EquipoChampions equipo) throws SQLException {
        String query = "INSERT INTO equipos_champions (nombre, pais, ciudad, estadio, fundacion, entrenador, web_oficial, facebook, twitter, instagram, patrocinador_principal) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getPais());
            ps.setString(3, equipo.getCiudad());
            ps.setString(4, equipo.getEstadio());
            ps.setInt(5, equipo.getFundacion());
            ps.setString(6, equipo.getEntrenador());
            ps.setString(7, equipo.getWebOficial());
            ps.setString(8, equipo.getFacebook());
            ps.setString(9, equipo.getTwitter());
            ps.setString(10, equipo.getInstagram());
            ps.setString(11, equipo.getPatrocinadorPrincipal());

            return ps.executeUpdate() > 0;
        }
    }

    // Leer equipo por ID
    public EquipoChampions leerEquipo(int idEquipo) throws SQLException {
        String query = "SELECT * FROM equipos_champions WHERE id_equipo = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idEquipo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new EquipoChampions(
                        rs.getInt("id_equipo"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("ciudad"),
                        rs.getString("estadio"),
                        rs.getInt("fundacion"),
                        rs.getString("entrenador"),
                        rs.getString("web_oficial"),
                        rs.getString("facebook"),
                        rs.getString("twitter"),
                        rs.getString("instagram"),
                        rs.getString("patrocinador_principal")
                );
            } else {
                return null;
            }
        }
    }

    // Actualizar un equipo
    public boolean actualizarEquipo(EquipoChampions equipo) throws SQLException {
        String query = "UPDATE equipos_champions SET nombre = ?, pais = ?, ciudad = ?, estadio = ?, fundacion = ?, entrenador = ?, web_oficial = ?, facebook = ?, twitter = ?, instagram = ?, patrocinador_principal = ? " +
                "WHERE id_equipo = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getPais());
            ps.setString(3, equipo.getCiudad());
            ps.setString(4, equipo.getEstadio());
            ps.setInt(5, equipo.getFundacion());
            ps.setString(6, equipo.getEntrenador());
            ps.setString(7, equipo.getWebOficial());
            ps.setString(8, equipo.getFacebook());
            ps.setString(9, equipo.getTwitter());
            ps.setString(10, equipo.getInstagram());
            ps.setString(11, equipo.getPatrocinadorPrincipal());
            ps.setInt(12, equipo.getIdEquipo());

            return ps.executeUpdate() > 0;
        }
    }

    // Eliminar un equipo
    public boolean eliminarEquipo(int idEquipo) throws SQLException {
        String query = "DELETE FROM equipos_champions WHERE id_equipo = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idEquipo);
            return ps.executeUpdate() > 0;
        }
    }

    // Obtener todos los equipos
    public List<EquipoChampions> obtenerEquipos() throws SQLException {
        List<EquipoChampions> equipos = new ArrayList<>();
        String query = "SELECT * FROM equipos_champions";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                equipos.add(new EquipoChampions(
                        rs.getInt("id_equipo"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("ciudad"),
                        rs.getString("estadio"),
                        rs.getInt("fundacion"),
                        rs.getString("entrenador"),
                        rs.getString("web_oficial"),
                        rs.getString("facebook"),
                        rs.getString("twitter"),
                        rs.getString("instagram"),
                        rs.getString("patrocinador_principal")
                ));
            }
        }
        return equipos;
    }
}