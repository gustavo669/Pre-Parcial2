package umg.progra2;

import umg.progra2.Conexión.MySQLConnection;
import umg.progra2.Dao.DatosDAO;
import umg.progra2.Model.Datos;
import umg.progra2.Services.DatosService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

public class Ejercicio1Form extends JFrame {

    // Declaración de componentes
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblApellido;
    private JTextField txtApellido;
    private JLabel lblDepartamento;
    private JTextField txtDepartamento;
    private JLabel lblFechaNacimiento;
    private JTextField txtFechaNacimiento;
    private JButton btnCrear;
    private JButton btnLeer;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JLabel label;
    private JPanel Ejercicio1Form;

    public Ejercicio1Form() {

        // Configuración de la ventana
        setTitle("Formulario Ejercicio 1");
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLayout(new GridLayout(8, 4, 10, 10));
        add(label); // Añadir etiqueta de encabezado
        add(new JLabel(""));

        add(lblCodigo);
        add(txtCodigo);

        add(lblNombre);
        add(txtNombre);

        add(lblApellido);
        add(txtApellido);

        add(lblDepartamento);
        add(txtDepartamento);

        add(lblFechaNacimiento);
        add(txtFechaNacimiento);

        add(btnCrear);
        add(btnLeer);
        add(btnActualizar);
        add(btnEliminar);

        setVisible(true);
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String departamento = txtDepartamento.getText();
                String fechaNacimiento = txtFechaNacimiento.getText();


                if (nombre.isEmpty() || apellido.isEmpty() || departamento.isEmpty() || fechaNacimiento.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {

                    LocalDate fechaNac = LocalDate.parse(fechaNacimiento);

                    Datos datos = new Datos();
                    datos.setNombre(nombre);
                    datos.setApellido(apellido);
                    datos.setDepartamento(departamento);
                    datos.setFechaNacimiento(Date.valueOf(fechaNac));

                    DatosDAO datosDAO = new DatosDAO(MySQLConnection.getConnection());
                    datosDAO.agregarDatos(datos);


                    JOptionPane.showMessageDialog(null, "Datos agregados exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    txtNombre.setText("");
                    txtApellido.setText("");
                    txtDepartamento.setText("");
                    txtFechaNacimiento.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codigoTexto = txtCodigo.getText();


                if (codigoTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int codigo;
                try {
                    codigo = Integer.parseInt(codigoTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El código debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                DatosService datosService = new DatosService(MySQLConnection.getConnection());
                Datos datos = datosService.consultarDatosPorCodigo(codigo);

                if (datos == null) {

                    JOptionPane.showMessageDialog(null, "No se encontró ningún registro con el código: " + codigo, "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    txtNombre.setText(datos.getNombre());
                    txtApellido.setText(datos.getApellido());
                    txtDepartamento.setText(datos.getDepartamento());
                    txtFechaNacimiento.setText(datos.getFechaNacimiento().toString());
                }
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el código ingresado en el campo de texto
                String codigoTexto = txtCodigo.getText();  // Campo de texto para ingresar el código
                if (codigoTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int codigo;
                try {
                    codigo = Integer.parseInt(codigoTexto);  // Convertir el texto a número
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El código debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Obtener los valores de los demás campos de texto
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String departamento = txtDepartamento.getText();
                String fechaNacimiento = txtFechaNacimiento.getText(); // Debe estar en formato "YYYY-MM-DD"

                // Validar que los campos no estén vacíos
                if (nombre.isEmpty() || apellido.isEmpty() || departamento.isEmpty() || fechaNacimiento.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Llamar al servicio para actualizar los datos
                DatosService datosService = new DatosService(MySQLConnection.getConnection());
                String resultado = datosService.modificarDatos(codigo, nombre, apellido, departamento, fechaNacimiento);

                // Mostrar el resultado en un mensaje
                JOptionPane.showMessageDialog(null, resultado, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el código ingresado en el campo de texto
                String codigoTexto = txtCodigo.getText();  // Campo de texto para el código

                // Verificar que el campo no esté vacío
                if (codigoTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int codigo;
                try {
                    // Intentar convertir el texto a número entero
                    codigo = Integer.parseInt(codigoTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El código debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Confirmar la eliminación con el usuario
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar el registro con el código: " + codigo + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Llamar al servicio para eliminar los datos
                    DatosService datosService = new DatosService(MySQLConnection.getConnection());
                    String resultado = datosService.eliminarDatos(codigo);

                    // Mostrar el resultado en un mensaje
                    JOptionPane.showMessageDialog(null, resultado, "Resultado", JOptionPane.INFORMATION_MESSAGE);

                    // Limpiar los campos después de la eliminación
                    if (resultado.equals("Datos eliminados correctamente")) {
                        txtCodigo.setText("");
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtDepartamento.setText("");
                        txtFechaNacimiento.setText("");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        new Ejercicio1Form();
    }
}