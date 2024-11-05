package co.edu.unbosque.view;

import javax.swing.JOptionPane;

public class View {

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }


    public int getInput(String message) {
        int input;
        try {
            input = Integer.parseInt(JOptionPane.showInputDialog(message));
        } catch (NumberFormatException e) {
            showMessage("Entrada no válida. Debe ser un número entero.");
            input = getInput(message);
        }
        return input;
    }
}
