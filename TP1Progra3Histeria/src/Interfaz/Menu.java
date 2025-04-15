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


    private void mostrarSeleccionDificultad() {
        String[] opcionesDificultad = { "facil", "normal", "dificil" };
        String seleccion = (String) JOptionPane.showInputDialog(
                frameMenu, 
                "Selecciona la dificultad", 
                "Dificultad", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                opcionesDificultad, 
                opcionesDificultad[0]);

        if (seleccion != null && !seleccion.isEmpty()) {
            abrirJuego(seleccion);
        }
    }


    private void abrirJuego(String dificultad) {
        Interfaz interfazJuego = new Interfaz(dificultad);
        interfazJuego.getFrmHisteria().setVisible(true);
        frameMenu.setVisible(false);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menu menu = new Menu(); 
                    menu.mostrarMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
