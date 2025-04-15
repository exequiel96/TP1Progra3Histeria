package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import negocio.logic;

public class Interfaz {

    private JFrame frmHisteria;
    private int tamanioMatriz;
    private JButton[][] botones;
    private JLabel intentos;
    private JLabel record;
    private logic logic;
    private String dificultad;

    public JFrame getFrmHisteria() {
        return frmHisteria;
    }

    public Interfaz(String dificultad) {
        this.dificultad = dificultad;
        this.logic=new logic(dificultad);
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
        
        
        record = new JLabel(""+ logic.obtenerRecord(dificultad));
        record.setBounds(58, 100, 46, 20);
        panelIzq.add(record);
        
        JLabel labelRecord = new JLabel("Record");
        labelRecord.setBounds(59, 80, 46, 14);
        panelIzq.add(labelRecord);
        
        intentos = new JLabel();
        intentos.setBounds(58, 47, 46, 20);
        panelIzq.add(intentos);
        
        JPanel panelDer = new JPanel();
        panelDer.setBounds(167, 0, 467, 410);
        frmHisteria.getContentPane().add(panelDer);

        // Configurar el tamaño de la matriz de botones según la dificultad
        this.tamanioMatriz=logic.configurarDificultad(this.dificultad);
        botones = new JButton[this.tamanioMatriz][this.tamanioMatriz];

        // Ajustar el GridLayout según el tamaño de la matriz de botones
        panelDer.setLayout(new GridLayout(botones.length, botones[0].length));

        JButton sugerenciaBtn = new JButton("Ayuda ("+ logic.getAyudas()+")");
        sugerenciaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.sugerencia(botones, sugerenciaBtn);
            }
        });
        sugerenciaBtn.setBounds(35, 320, 89, 23);
        panelIzq.add(sugerenciaBtn);

        JButton reinicioBtn = new JButton("Reiniciar");
        reinicioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.apagarBotones(botones);
                sugerenciaBtn.setText("Ayuda ("+ logic.getAyudas()+")");
                intentos.setText(Integer.toString(0));
            }
        });
        reinicioBtn.setBounds(35, 350, 89, 23);
        panelIzq.add(reinicioBtn);
        
        JButton menuBtn = new JButton("Menu");
        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.apagarBotones(botones);
                sugerenciaBtn.setText("Ayuda ("+ logic.getAyudas()+")");
                intentos.setText(Integer.toString(0));
                Menu menuPrincipal=new Menu();
                menuPrincipal.mostrarMenu();
                frmHisteria.setVisible(false);
            }
        });
        menuBtn.setBounds(35, 380, 89, 23);
        panelIzq.add(menuBtn);
        
        
        
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[i].length; j++) {
                JButton boton = new JButton();
                boton.setBackground(Color.WHITE);
                botones[i][j] = boton;
                panelDer.add(boton);
                logic.escucharBoton(botones[i][j], botones, i, j, intentos, record);
            }
        }
    }

}
