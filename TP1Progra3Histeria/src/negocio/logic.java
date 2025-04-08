package negocio;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class logic {
	
	private int intentos;
	private int cuadrosPrendidos;
	
	public logic () {
		this.intentos = 1;
		this.cuadrosPrendidos = 0;
	}

	public void escucharBoton(JButton jButton, JButton[][] botones, int fila, int col, JLabel intentos) {
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarIntentos(intentos);
				Random random = new Random();
				int r = random.nextInt(6);	
				if(r == 0) {
					Color color = Color.BLACK;
					if(esValido(jButton, botones, fila, col, color)) {
						jButton.setBackground(Color.black);
						jButton.setEnabled(false);
						actualizarBotonesPrendidos();
					}
				}
				if(r == 1) {
					Color color = Color.BLUE;
					if(esValido(jButton, botones, fila, col, color) ) {
						jButton.setBackground(Color.blue);
						jButton.setEnabled(false);
						actualizarBotonesPrendidos();
					}
				}
				if(r == 2) {
					Color color = Color.RED;
					if(esValido(jButton, botones, fila, col, color)) {
						jButton.setBackground(Color.red);
						jButton.setEnabled(false);
						actualizarBotonesPrendidos();
					}
				}
				if(r == 3) {
					Color color = Color.YELLOW;
					if(esValido(jButton, botones, fila, col, color)) {
						jButton.setBackground(color);
						jButton.setEnabled(false);
						actualizarBotonesPrendidos();
					}
				}
				if(r == 4) {
					Color color = Color.GREEN;
					if(esValido(jButton, botones, fila, col, color)) {
						jButton.setBackground(Color.GREEN);
						jButton.setEnabled(false);
						actualizarBotonesPrendidos();
					}
				}
				if(r == 5) {
					Color color = Color.PINK;
					if(esValido(jButton, botones, fila, col, color)) {
						jButton.setBackground(Color.PINK);
						jButton.setEnabled(false);
						actualizarBotonesPrendidos();
					}
				}
				consultarVictoria(botones, intentos);
			}
		});
	}

	private boolean esValido(JButton jButton, JButton[][] botones, int fila, int col, Color color) {
		ArrayList<JButton> botonesVecinos = new ArrayList<JButton>();
		if((fila >= 1 && fila <= botones[0].length-2) && (col >= 1 && col <= botones[0].length-2)) {
			botonesVecinos.add(botones[fila - 1][col]);
			botonesVecinos.add(botones[fila+1][col]);
			botonesVecinos.add(botones[fila][col+1]);
			botonesVecinos.add(botones[fila][col -1]);		
		}
		if((fila == 0 || fila == botones[0].length-1) && (col >= 1 && col <= botones[0].length-2)) {
			if(fila == 0) {
				botonesVecinos.add(botones[fila][col -1]);
				botonesVecinos.add(botones[fila][col +1]);
				botonesVecinos.add(botones[fila + 1][col]);
			}
			else {
				botonesVecinos.add(botones[fila][col -1]);
				botonesVecinos.add(botones[fila][col +1]);
				botonesVecinos.add(botones[fila - 1][col]);
			}
		}
		if((fila >= 1 && fila <= botones[0].length-2) && (col == 0 || col == botones[0].length-1)) {
			if(col == 0) {
				botonesVecinos.add(botones[fila - 1][col]);
				botonesVecinos.add(botones[fila][col + 1]);
				botonesVecinos.add(botones[fila + 1][col]);
			}
			else {
				botonesVecinos.add(botones[fila - 1][col]);
				botonesVecinos.add(botones[fila][col - 1]);
				botonesVecinos.add(botones[fila + 1][col]);
			}
		}
		if((fila == 0 && col == 0) || (fila == botones[0].length-1 && col == botones[0].length-1)) {
			if(fila == botones[0].length-1) {
				botonesVecinos.add(botones[fila - 1][col]);
				botonesVecinos.add(botones[fila][col - 1]);
			}
			else {
				botonesVecinos.add(botones[fila + 1][col]);
				botonesVecinos.add(botones[fila][col + 1]);
			}
		}
		if((fila == botones[0].length-1 && col == 0) || (fila == 0 && col == botones[0].length-1)) {
			if(fila == 0) {
				botonesVecinos.add(botones[fila][col - 1]);
				botonesVecinos.add(botones[fila + 1][col]);
			}
			else {
				botonesVecinos.add(botones[fila][col + 1]);
				botonesVecinos.add(botones[fila - 1][col]);
			}
		}
		return checkVecinos(jButton, botonesVecinos, color);
	}

	private boolean checkVecinos(JButton jButton, ArrayList<JButton> botonesVecinos, Color color) {
		for(JButton b : botonesVecinos) {
			if(color.equals(b.getBackground())){
				apagarColores(botonesVecinos, jButton);
				return false;
			}
		}
		return true;
	}

	private void apagarColores(ArrayList<JButton> botonesVecinos, JButton jButton) {
		jButton.setBackground(Color.WHITE);
		jButton.setEnabled(true);
		int vecinosNoBlancos = 0;
		for(JButton b : botonesVecinos) {
			if(!b.getBackground().equals(Color.WHITE))
				vecinosNoBlancos++;
			b.setBackground(Color.WHITE);
			b.setEnabled(true);
		}
		this.cuadrosPrendidos -= (vecinosNoBlancos);
	}

	public void apagarBotones(JButton[][] botones) {
		this.intentos = 1;
		this.cuadrosPrendidos = 0;
		for(int i = 0; i < botones[0].length; i++) {
			for(int j = 0; j < botones[0].length; j++) {
				botones[i][j].setBackground(Color.WHITE);
				botones[i][j].setEnabled(true);
			}
		}
	}
	
	private void actualizarIntentos(JLabel intentose) {
		intentose.setText(Integer.toString(this.intentos++));
	}

	protected void actualizarBotonesPrendidos() {
		this.cuadrosPrendidos++;
	}

	public void consultarVictoria(JButton[][] botones, JLabel intentos) {
		if(this.cuadrosPrendidos == 25)
			mostrarVictoria(botones, intentos);	
	}
	

	public void mostrarVictoria(JButton[][] botones, JLabel intentos) {		
		JOptionPane.showMessageDialog(null, "Has ganado el juego!", "Victoria", JOptionPane.INFORMATION_MESSAGE);
		String nombre = JOptionPane.showInputDialog(null, "Ingresa tu nombre:");
		apagarBotones(botones);
		intentos.setText(Integer.toString(0));
	}



	
	
	

	


	

	
	
	
}
