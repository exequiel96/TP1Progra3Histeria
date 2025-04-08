package negocio;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class logic {
	
	private int intentos;
	private int cuadrosPrendidos;
	
	public logic () {
		this.intentos = 1;
		this.cuadrosPrendidos = 0;
	}

	public void escucharBoton(JButton jButton, JButton[][] botones, int fila, int col, JTextField intentos) {
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
						actualizarBotonesPrendidos();
					}
				}
				if(r == 1) {
					Color color = Color.BLUE;
					if(esValido(jButton, botones, fila, col, color) ) {
						jButton.setBackground(Color.blue);
						actualizarBotonesPrendidos();
					}
				}
				if(r == 2) {
					Color color = Color.RED;
					if(esValido(jButton, botones, fila, col, color)) {
						jButton.setBackground(Color.red);
						actualizarBotonesPrendidos();
					}
				}
				if(r == 3) {
					Color color = Color.YELLOW;
					if(esValido(jButton, botones, fila, col, color)) {
						jButton.setBackground(color);
						actualizarBotonesPrendidos();
					}
				}
				if(r == 4) {
					Color color = Color.GREEN;
					if(esValido(jButton, botones, fila, col, color)) {
						jButton.setBackground(Color.GREEN);
						actualizarBotonesPrendidos();
					}
				}
				if(r == 5) {
					Color color = Color.PINK;
					if(esValido(jButton, botones, fila, col, color)) {
						jButton.setBackground(Color.PINK);
						actualizarBotonesPrendidos();
					}
				}
				consultarVictoria(botones);
			}
		});
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
		int vecinosNoBlancos = 0;
		for(JButton b : botonesVecinos) {
			if(!b.getBackground().equals(Color.WHITE))
				vecinosNoBlancos++;
			b.setBackground(Color.WHITE);
		}
		this.cuadrosPrendidos -= (vecinosNoBlancos);
	}

	public void apagarBotones(JButton[][] botones) {
		this.intentos = 1;
		this.cuadrosPrendidos = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				botones[i][j].setBackground(Color.WHITE);
			}
		}
	}
	
	private void actualizarIntentos(JTextField intentose) {
		intentose.setText(Integer.toString(this.intentos++));
	}

	protected void actualizarBotonesPrendidos() {
		this.cuadrosPrendidos++;
	}

	public void consultarVictoria(JButton[][] botones) {
		if(this.cuadrosPrendidos == 25)
			mostrarVictoria(botones);	
	}
	

	public void mostrarVictoria(JButton[][] botones) {		
		JOptionPane.showMessageDialog(null, "Has ganado el juego!", "Ganador", JOptionPane.INFORMATION_MESSAGE);
		apagarBotones(botones);
	}



	
	
	

	


	

	
	
	
}
