package negocio;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JTextField;

import Interfaz.Interfaz;

public class logic {

	private int intentos;
	
	public logic () {
		this.intentos = 0;
	}

	public void escucharBoton(JButton jButton, JButton[][] botones, int fila, int col) {
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contarIntentos();
				Random random = new Random();
				int r = random.nextInt(6);	
				if(r == 0) {
					Color color = Color.BLACK;
					if(esValido(jButton, botones, fila, col, color))
						jButton.setBackground(Color.black);
				}
				if(r == 1) {
					Color color = Color.BLUE;
					if(esValido(jButton, botones, fila, col, color) )
						jButton.setBackground(Color.blue);
				}
				if(r == 2) {
					Color color = Color.RED;
					if(esValido(jButton, botones, fila, col, color))
						jButton.setBackground(Color.red);
				}
				if(r == 3) {
					Color color = Color.YELLOW;
					if(esValido(jButton, botones, fila, col, color))
						jButton.setBackground(color);
				}
				if(r == 4) {
					Color color = Color.GREEN;
					if(esValido(jButton, botones, fila, col, color))
						jButton.setBackground(Color.GREEN);
				}
				if(r == 5) {
					Color color = Color.PINK;
					if(esValido(jButton, botones, fila, col, color))
						jButton.setBackground(Color.PINK);
				}
				
			}

				
		});
	}

	

	private void contarIntentos() {
		this.intentos++;
	}

	private boolean esValido(JButton jButton, JButton[][] botones, int fila, int col, Color color) {
		ArrayList<JButton> botonesVecinos = new ArrayList<JButton>();
		if((fila >= 1 && fila <= 3) && (col >= 1 && col <= 3)) {
			botonesVecinos.add(botones[fila - 1][col]);
			botonesVecinos.add(botones[fila+1][col]);
			botonesVecinos.add(botones[fila][col+1]);
			botonesVecinos.add(botones[fila][col -1]);		
		}
		if((fila == 0 || fila == 4) && (col >= 1 && col <= 3)) {
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
		if((fila >= 1 && fila <= 3) && (col == 0 || col == 4)) {
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
		if((fila == 0 && col == 0) || (fila == 4 && col == 4)) {
			if(fila == 4) {
				botonesVecinos.add(botones[fila - 1][col]);
				botonesVecinos.add(botones[fila][col - 1]);
			}
			else {
				botonesVecinos.add(botones[fila + 1][col]);
				botonesVecinos.add(botones[fila][col + 1]);
			}
		}
		if((fila == 4 && col == 0) || (fila == 0 && col == 4)) {
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
		for(JButton b : botonesVecinos) {
			b.setBackground(Color.WHITE);
		}
	}

	public int getIntentos() {
		return intentos;
	}

	public void actualizarIntentos(JTextField intentos) {
		intentos.setText(Integer.toString(this.intentos));
	}



	
	
	

	


	

	
	
	
}
