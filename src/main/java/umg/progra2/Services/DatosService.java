package umg.progra2.Services;

import umg.progra2.Dao.DatosDAO;
import umg.progra2.Model.Datos;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class DatosService {
    private DatosDAO datosDAO;

    public DatosService(Connection connection) {
        this.datosDAO = new DatosDAO(connection);  // Iniciar con una conexión a la base de datos
    }

    // Método para agregar datos
    public String agregarDatos(String nombre, String apellido, String departamento, String fechaNacimiento) {
        // Validaciones básicas
        if (nombre == null || nombre.isEmpty()) {
            return "El nombre no puede estar vacío";
        }

        if (apellido == null || apellido.isEmpty()) {
            return "El apellido no puede estar vacío";
        }

        if (departamento == null || departamento.isEmpty()) {
            return "El departamento no puede estar vacío";
        }

        LocalDate fechaNac;
        try {
            fechaNac = LocalDate.parse(fechaNacimiento);
        } catch (Exception e) {
            return "El formato de la fecha es inválido. Debe ser YYYY-MM-DD";
        }

        // Crear un objeto Datos y agregar a la base de datos
        Datos datos = new Datos();
        datos.setNombre(nombre);
        datos.setApellido(apellido);
        datos.setDepartamento(departamento);
        datos.setFechaNacimiento(Date.valueOf(fechaNac));

        try {
            datosDAO.agregarDatos(datos);
            return "Datos agregados correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al agregar los datos: " + e.getMessage();
        }
    }

    // Método para modificar datos
    public String modificarDatos(int codigo, String nombre, String apellido, String departamento, String fechaNacimiento) {
        // Validaciones básicas
        if (codigo <= 0) {
            return "El código debe ser mayor que cero";
        }

        if (nombre == null || nombre.isEmpty()) {
            return "El nombre no puede estar vacío";
        }

        if (apellido == null || apellido.isEmpty()) {
            return "El apellido no puede estar vacío";
        }

        if (departamento == null || departamento.isEmpty()) {
            return "El departamento no puede estar vacío";
        }

        LocalDate fechaNac;
        try {
            fechaNac = LocalDate.parse(fechaNacimiento);
        } catch (Exception e) {
            return "El formato de la fecha es inválido. Debe ser YYYY-MM-DD";
        }

        // Crear un objeto Datos y modificar en la base de datos
        Datos datos = new Datos();
        datos.setCodigo(codigo);
        datos.setNombre(nombre);
        datos.setApellido(apellido);
        datos.setDepartamento(departamento);
        datos.setFechaNacimiento(Date.valueOf(fechaNac));

        try {
            datosDAO.modificarDatos(datos);
            return "Datos modificados correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al modificar los datos: " + e.getMessage();
        }
    }

    // Método para eliminar datos
    public String eliminarDatos(int codigo) {
        if (codigo <= 0) {
            return "El código debe ser mayor que cero";
        }

        try {
            datosDAO.eliminarDatos(codigo);
            return "Datos eliminados correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al eliminar los datos: " + e.getMessage();
        }
    }

    // Método para consultar un registro por su código
    public Datos consultarDatosPorCodigo(int codigo) {
        if (codigo <= 0) {
            return null;
        }

        return datosDAO.consultarDatosPorCodigo(codigo);
    }

    // Método para obtener todos los registros
    public List<Datos> consultarTodosLosDatos() {
        return datosDAO.consultarTodosLosDatos();
    }
}