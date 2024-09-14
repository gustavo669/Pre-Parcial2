package umg.progra2;


import umg.progra2.Conexión.MySQLConnection;
import umg.progra2.Dao.EquipoChampionsDAO;
import umg.progra2.Model.EquipoChampions;
import umg.progra2.Services.EquipoChampionsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Ejercicio3Form extends JFrame {

    private EquipoChampionsService equipoService;
    private JButton btnCrear;
    private JButton btnEliminar;
    private JButton btnLeer;
    private JButton btnActualizar;
    private JTextField txtIdEquipo;
    private JTextField txtNombre;
    private JTextField txtPais;
    private JTextField txtCiudad;
    private JTextField txtEstadio;
    private JTextField txtFundacion;
    private JTextField txtEntrenador;
    private JTextField txtWebOficial;
    private JTextField txtFacebook;
    private JTextField txtInstagram;
    private JTextField txtTwitter;
    private JTextField txtPatrocinador;
    private JLabel lblIdEquipo;
    private JLabel lblNombre;
    private JLabel lblPais;
    private JLabel lblCiudad;
    private JLabel lblEstadio;
    private JLabel lblFundacion;
    private JLabel lblEntrenador;
    private JLabel lblWebOficial;
    private JLabel lblFacebook;
    private JLabel lblTwitter;
    private JLabel lblInstagram;
    private JLabel lblPatrocinador;

    public Ejercicio3Form() {
        Connection connection = MySQLConnection.getConnection();
        equipoService = new EquipoChampionsService(new EquipoChampionsDAO(connection));

        setTitle("Formulario Ejercicio 3");
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(14, 2));

        add(lblIdEquipo);
        add(txtIdEquipo);
        add(lblNombre);
        add(txtNombre);
        add(lblPais);
        add(txtPais);
        add(lblCiudad);
        add(txtCiudad);
        add(lblEstadio);
        add(txtEstadio);
        add(lblFundacion);
        add(txtFundacion);
        add(lblEntrenador);
        add(txtEntrenador);
        add(lblWebOficial);
        add(txtWebOficial);
        add(lblFacebook);
        add(txtFacebook);
        add(lblTwitter);
        add(txtTwitter);
        add(lblInstagram);
        add(txtInstagram);
        add(lblPatrocinador);
        add(txtPatrocinador);

        add(btnCrear);
        add(btnLeer);
        add(btnActualizar);
        add(btnEliminar);

        // Acción para crear un equipo
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EquipoChampions equipo = new EquipoChampions(
                            0,
                            txtNombre.getText(),
                            txtPais.getText(),
                            txtCiudad.getText(),
                            txtEstadio.getText(),
                            Integer.parseInt(txtFundacion.getText()),
                            txtEntrenador.getText(),
                            txtWebOficial.getText(),
                            txtFacebook.getText(),
                            txtTwitter.getText(),
                            txtInstagram.getText(),
                            txtPatrocinador.getText()
                    );
                    boolean resultado = equipoService.crearEquipo(equipo);
                    JOptionPane.showMessageDialog(null, resultado ? "Equipo creado correctamente." : "Error al crear el equipo.");
                } catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // Acción para leer un equipo
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idEquipoTexto = txtIdEquipo.getText();
                if (idEquipoTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID del equipo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idEquipo;
                try {
                    idEquipo = Integer.parseInt(idEquipoTexto);
                    EquipoChampions equipo = equipoService.leerEquipo(idEquipo);
                    if (equipo != null) {
                        txtNombre.setText(equipo.getNombre());
                        txtPais.setText(equipo.getPais());
                        txtCiudad.setText(equipo.getCiudad());
                        txtEstadio.setText(equipo.getEstadio());
                        txtFundacion.setText(String.valueOf(equipo.getFundacion()));
                        txtEntrenador.setText(equipo.getEntrenador());
                        txtWebOficial.setText(equipo.getWebOficial());
                        txtFacebook.setText(equipo.getFacebook());
                        txtTwitter.setText(equipo.getTwitter());
                        txtInstagram.setText(equipo.getInstagram());
                        txtPatrocinador.setText(equipo.getPatrocinadorPrincipal());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el equipo con el ID: " + idEquipo, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // Acción para actualizar un equipo
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idEquipoTexto = txtIdEquipo.getText();
                if (idEquipoTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID del equipo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idEquipo;
                try {
                    idEquipo = Integer.parseInt(idEquipoTexto);
                    EquipoChampions equipo = new EquipoChampions(
                            idEquipo,
                            txtNombre.getText(),
                            txtPais.getText(),
                            txtCiudad.getText(),
                            txtEstadio.getText(),
                            Integer.parseInt(txtFundacion.getText()),
                            txtEntrenador.getText(),
                            txtWebOficial.getText(),
                            txtFacebook.getText(),
                            txtTwitter.getText(),
                            txtInstagram.getText(),
                            txtPatrocinador.getText()
                    );
                    boolean resultado = equipoService.actualizarEquipo(equipo);
                    JOptionPane.showMessageDialog(null, resultado ? "Equipo actualizado correctamente." : "Error al actualizar el equipo.");
                } catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // Acción para eliminar un equipo
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idEquipoTexto = txtIdEquipo.getText();
                if (idEquipoTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID del equipo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idEquipo;
                try {
                    idEquipo = Integer.parseInt(idEquipoTexto);
                    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar el equipo con el ID: " + idEquipo + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        boolean resultado = equipoService.eliminarEquipo(idEquipo);
                        JOptionPane.showMessageDialog(null, resultado ? "Equipo eliminado correctamente." : "Error al eliminar el equipo.");

                        // Limpiar campos después de la eliminación
                        if (resultado) {
                            txtIdEquipo.setText("");
                            txtNombre.setText("");
                            txtPais.setText("");
                            txtCiudad.setText("");
                            txtEstadio.setText("");
                            txtFundacion.setText("");
                            txtEntrenador.setText("");
                            txtWebOficial.setText("");
                            txtFacebook.setText("");
                            txtTwitter.setText("");
                            txtInstagram.setText("");
                            txtPatrocinador.setText("");
                        }
                    }
                } catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Ejercicio3Form();
    }
}
