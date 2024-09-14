package umg.progra2;

import javax.swing.*;
import java.awt.*;

class Ejercicio3Form extends JFrame {
    public Ejercicio3Form() {
        setTitle("Formulario Ejercicio 3");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Este es el formulario del Ejercicio 3.");
        add(label);

        setVisible(true);
    }
}
