package umg.progra2.Dao;

import umg.progra2.Model.Datos;
import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatosDAO {

    private Connection connection;

    public DatosDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para crear un nuevo registro
    public void agregarDatos(Datos datos) {
        String sql = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, datos.getNombre());
            statement.setString(2, datos.getApellido());
            statement.setString(3, datos.getDepartamento());
            statement.setDate(4, datos.getFechaNacimiento()); // Convertir LocalDate a Date
            statement.executeUpdate();
            System.out.println("Datos agregados correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para modificar un registro existente
    public void modificarDatos(Datos datos) {
        String sql = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, datos.getNombre());
            statement.setString(2, datos.getApellido());
            statement.setString(3, datos.getDepartamento());
            statement.setDate(4, datos.getFechaNacimiento());
            statement.setInt(5, datos.getCodigo());
            statement.executeUpdate();
            System.out.println("Datos modificados correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un registro por su código
    public void eliminarDatos(int codigo) {
        String sql = "DELETE FROM tb_datos WHERE codigo = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigo);
            statement.executeUpdate();
            System.out.println("Datos eliminados correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar un registro por su código
    public Datos consultarDatosPorCodigo(int codigo) {
        String sql = "SELECT * FROM tb_datos WHERE codigo = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Datos datos = new Datos();
                datos.setCodigo(resultSet.getInt("codigo"));
                datos.setNombre(resultSet.getString("nombre"));
                datos.setApellido(resultSet.getString("apellido"));
                datos.setDepartamento(resultSet.getString("departamento"));
                datos.setFechaNacimiento(Date.valueOf(resultSet.getDate("fecha_nacimiento").toLocalDate())); // Convertir Date a LocalDate
                return datos;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para consultar todos los registros
    public List<Datos> consultarTodosLosDatos() {
        String sql = "SELECT * FROM tb_datos";
        List<Datos> listaDatos = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Datos datos = new Datos();
                datos.setCodigo(resultSet.getInt("codigo"));
                datos.setNombre(resultSet.getString("nombre"));
                datos.setApellido(resultSet.getString("apellido"));
                datos.setDepartamento(resultSet.getString("departamento"));
                datos.setFechaNacimiento(Date.valueOf(resultSet.getDate("fecha_nacimiento").toLocalDate()));
                listaDatos.add(datos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDatos;
    }
}