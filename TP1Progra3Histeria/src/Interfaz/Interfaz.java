package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import negocio.logic;

public class Interfaz {

    private JFrame frmHisteria;
    private JButton[][] botones; // Cambiamos la declaración para hacerlo dinámico
    private JLabel intentos;
    private logic logic = new logic();
    private String dificultad;

    public JFrame getFrmHisteria() {
        return frmHisteria;
    }

    public Interfaz(String dificultad) {
        this.dificultad = dificultad;  // Guardamos la dificultad seleccionada
        initialize();
    }

    private void initialize() {
        frmHisteria = new JFrame();
        frmHisteria.setTitle("Histeria");
        frmHisteria.setBounds(100, 100, 650, 449);
        frmHisteria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmHisteria.getContentPane().setLayout(null);

        JPanel panelIzq = new JPanel();
        panelIzq.setBounds(0, 0, 167, 410);
        frmHisteria.getContentPane().add(panelIzq);
        panelIzq.setLayout(null);

        JLabel labelIntentos = new JLabel("Intentos");
        labelIntentos.setBounds(59, 22, 46, 14);
        panelIzq.add(labelIntentos);

        intentos = new JLabel();
        intentos.setBounds(58, 47, 46, 20);
        panelIzq.add(intentos);

        JPanel panelDer = new JPanel();
        panelDer.setBounds(167, 0, 467, 410);
        frmHisteria.getContentPane().add(panelDer);

        // Configurar el tamaño de la matriz de botones según la dificultad
        configurarDificultad();

        // Ajustar el GridLayout según el tamaño de la matriz de botones
        panelDer.setLayout(new GridLayout(botones.length, botones[0].length));

        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[i].length; j++) {
                JButton boton = new JButton();
                boton.setBackground(Color.WHITE);
                botones[i][j] = boton;
                panelDer.add(boton);
                logic.escucharBoton(botones[i][j], botones, i, j, intentos);
            }
        }

        JButton reinicioBtn = new JButton("Reiniciar");
        reinicioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.apagarBotones(botones);
                intentos.setText(Integer.toString(0));
            }
        });
        reinicioBtn.setBounds(35, 377, 89, 23);
        panelIzq.add(reinicioBtn);
    }

    private void configurarDificultad() {
        int size = 0;

        if (dificultad.equals("Fácil")) {
            size = 5;  
        } else if (dificultad.equals("Normal")) {
            size = 6;  
        } else if (dificultad.equals("Difícil")) {
            size = 7; 
        }

        botones = new JButton[size][size];
    }
}
