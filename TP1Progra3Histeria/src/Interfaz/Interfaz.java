package Interfaz;


import java.awt.EventQueue;
import negocio.logic;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Interfaz {

	private JFrame frmHisteria;
	private JButton [][] botones = new JButton [5][5];
	private JTextField intentos;
	private logic logic = new logic();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frmHisteria.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
		
		intentos = new JTextField();
		intentos.setBounds(69, 47, 22, 20);
		panelIzq.add(intentos);
		intentos.setText(Integer.toString(logic.getIntentos()));
		intentos.setColumns(10);
		
		JTextArea areaTextGanaste = new JTextArea();
		areaTextGanaste.setBounds(25, 126, 116, 240);
		panelIzq.add(areaTextGanaste);
		
		JPanel panelDer = new JPanel();
		panelDer.setBounds(167, 0, 467, 410);
		frmHisteria.getContentPane().add(panelDer);
		panelDer.setLayout(new GridLayout(5,5));
		
		
		//agrego los botones a la grilla
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				JButton boton = new JButton();
				boton.setBackground(Color.WHITE);
				botones[i][j] = boton;
				panelDer.add(boton);
				logic.escucharBoton(botones[i][j], botones, i, j);
			}
		}
		
		

		
		
		

		
	}
}
