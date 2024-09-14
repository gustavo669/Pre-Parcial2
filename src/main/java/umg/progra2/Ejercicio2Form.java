package umg.progra2;

import umg.progra2.Conexión.MySQLConnection;
import umg.progra2.Model.Usuario;
import umg.progra2.Services.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Ejercicio2Form extends JFrame {
    private JButton btnCrear, btnLeer, btnActualizar, btnEliminar;
    private UsuarioService usuarioService;
    private JLabel label;
    private JTextField txtIdUsuario;
    private JTextField txtCarne;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtSeccion;
    private JTextField txtTelegramId;
    private JTextField txtActivo;
    private JLabel lblActivo;
    private JLabel lblTelegramId;
    private JLabel lblSeccion;
    private JLabel lblCorreo;
    private JLabel lblNombre;
    private JLabel lblCarne;
    private JLabel lblIdUsuario;

    public Ejercicio2Form() {

        Connection connection = MySQLConnection.getConnection();
        usuarioService = new UsuarioService(connection);

        setTitle("Formulario Ejercicio 2");
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLayout(new GridLayout(10, 4, 10, 10));

        add(label);
        add(new JLabel(""));

        add(lblIdUsuario);
        add(txtIdUsuario);

        add(lblCarne);
        add(txtCarne);

        add(lblNombre);
        add(txtNombre);

        add(lblCorreo);
        add(txtCorreo);

        add(lblSeccion);
        add(txtSeccion);

        add(lblTelegramId);
        add(txtTelegramId);

        add(lblActivo);
        add(txtActivo);

        add(btnCrear);
        add(btnLeer);
        add(btnActualizar);
        add(btnEliminar);

        // Agregar ActionListeners
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String carne = txtCarne.getText();
                String nombre = txtNombre.getText();
                String correo = txtCorreo.getText();
                String seccion = txtSeccion.getText();
                long telegramId = Long.parseLong(txtTelegramId.getText());
                String activo = txtActivo.getText();

                String resultado = usuarioService.crearUsuario(carne, nombre, correo, seccion, telegramId, activo);
                JOptionPane.showMessageDialog(null, resultado);
            }
        });

        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idUsuarioTexto = txtIdUsuario.getText();
                if (idUsuarioTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID del usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idUsuario;
                try {
                    idUsuario = Integer.parseInt(idUsuarioTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Usuario usuario = usuarioService.leerUsuario(idUsuario);
                if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "No se encontró el usuario con el ID: " + idUsuario, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    txtCarne.setText(usuario.getCarne());
                    txtNombre.setText(usuario.getNombre());
                    txtCorreo.setText(usuario.getCorreo());
                    txtSeccion.setText(usuario.getSeccion());
                    txtTelegramId.setText(String.valueOf(usuario.getTelegramId()));
                    txtActivo.setText(usuario.getActivo());
                }
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idUsuarioTexto = txtIdUsuario.getText();
                if (idUsuarioTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID del usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idUsuario;
                try {
                    idUsuario = Integer.parseInt(idUsuarioTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String carne = txtCarne.getText();
                String nombre = txtNombre.getText();
                String correo = txtCorreo.getText();
                String seccion = txtSeccion.getText();
                long telegramId = Long.parseLong(txtTelegramId.getText());
                String activo = txtActivo.getText();

                String resultado = usuarioService.actualizarUsuario(idUsuario, carne, nombre, correo, seccion, telegramId, activo);
                JOptionPane.showMessageDialog(null, resultado);
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idUsuarioTexto = txtIdUsuario.getText();
                if (idUsuarioTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID del usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idUsuario;
                try {
                    idUsuario = Integer.parseInt(idUsuarioTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar el usuario con el ID: " + idUsuario + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    String resultado = usuarioService.eliminarUsuario(idUsuario);
                    JOptionPane.showMessageDialog(null, resultado);

                    // Limpiar campos después de la eliminación
                    if (resultado.equals("Usuario eliminado correctamente")) {
                        txtIdUsuario.setText("");
                        txtCarne.setText("");
                        txtNombre.setText("");
                        txtCorreo.setText("");
                        txtSeccion.setText("");
                        txtTelegramId.setText("");
                        txtActivo.setText("");
                    }
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Ejercicio2Form();
    }
}