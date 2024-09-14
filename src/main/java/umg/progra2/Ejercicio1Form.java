package umg.progra2;

import javax.swing.*;
import java.awt.*;

class Ejercicio1Form extends JFrame {
    public Ejercicio1Form() {
        setTitle("Formulario Ejercicio 1");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Este es el formulario del Ejercicio 1.");
        add(label);

        setVisible(true);
    }
}
