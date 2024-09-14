package umg.progra2;

import javax.swing.*;
import java.awt.*;

class Ejercicio2Form extends JFrame {
    public Ejercicio2Form() {
        setTitle("Formulario Ejercicio 2");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Gestión de Usuarios");
        add(label);

        setVisible(true);
    }
}