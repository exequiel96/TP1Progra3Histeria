package Interfaz;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class Menu {

    private JFrame frameMenu;

    public Menu() {
        frameMenu = new JFrame();
        frameMenu.setTitle("Menú Principal");
        frameMenu.setBounds(100, 100, 400, 300);
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMenu.getContentPane().setLayout(null);

        JButton btnJugar = new JButton("Jugar");
        btnJugar.setBounds(150, 100, 100, 30);
        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarSeleccionDificultad();
            }
        });
        frameMenu.getContentPane().add(btnJugar);
    }

    public void mostrarMenu() {
        frameMenu.setVisible(true);
    }

    // Método para mostrar las opciones de dificultad
    private void mostrarSeleccionDificultad() {
        String[] opcionesDificultad = { "Fácil", "Normal", "Difícil" };
        String seleccion = (String) JOptionPane.showInputDialog(
                frameMenu, 
                "Selecciona la dificultad", 
                "Dificultad", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                opcionesDificultad, 
                opcionesDificultad[1]);

        if (seleccion != null && !seleccion.isEmpty()) {
            abrirJuego(seleccion);
        }
    }

    // Abre el juego con la dificultad seleccionada
    private void abrirJuego(String dificultad) {
        // Abre la interfaz de juego pasando la dificultad seleccionada
        Interfaz interfazJuego = new Interfaz(dificultad);
        interfazJuego.getFrmHisteria().setVisible(true);
        frameMenu.setVisible(false);  // Cierra el menú
    }

    // Método principal (main) para iniciar la aplicación con el menú
    public static void main(String[] args) {
        // Crear el objeto del menú y mostrarlo
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menu menu = new Menu();  // Crear una instancia del menú
                    menu.mostrarMenu();  // Mostrar el menú
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
