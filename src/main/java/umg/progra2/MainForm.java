package umg.progra2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JButton btnEjercicio1, btnEjercicio2, btnEjercicio3;
    private JPanel panel1;

    public MainForm() {
        setTitle("Formulario Principal");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        

        // Añadir los botones al formulario principal
        add(btnEjercicio1);
        add(btnEjercicio2);
        add(btnEjercicio3);

        // Acción del botón Ejercicio 1
        btnEjercicio1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir el formulario de Ejercicio 1
                new Ejercicio1Form();
            }
        });

        // Acción del botón Ejercicio 2
        btnEjercicio2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir el formulario de Ejercicio 2
                new Ejercicio2Form();
            }
        });

        // Acción del botón Ejercicio 3
        btnEjercicio3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir el formulario de Ejercicio 3
                new Ejercicio3Form();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainForm();
    }
}


