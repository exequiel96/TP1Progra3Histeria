package negocio;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class logic {
	
	private int intentos;
	private Color color;
	private JButton botonResaltado;
	private int ayudas;
	private String dificultad;
	private int cuadrosPrendidos;
	private int recordFacil;
	private int recordNormal;
	private int recordDificil;
	
	public logic (String dificultadElegida) {
		this.intentos = 1;
		
		randomizarColor();
		this.dificultad=dificultadElegida;
		if (dificultadElegida.equals("facil")) {
            this.ayudas=2;
        } else if (dificultadElegida.equals("normal")) {
        	this.ayudas=4;
        } else if (dificultadElegida.equals("dificil")) {
        	this.ayudas=6;
       
        }
		this.cuadrosPrendidos = 0;
		this.recordFacil = leerRecordDesdeArchivo("recordfacil.txt");
        this.recordNormal = leerRecordDesdeArchivo("recordnormal.txt");
        this.recordDificil = leerRecordDesdeArchivo("recorddificil.txt");
	}
	
	private int leerRecordDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea = br.readLine();
            return Integer.parseInt(linea);
        } catch (Exception e) {
            System.err.println("Error al leer " + nombreArchivo + ": " + e.getMessage());
            return 0; // valor por defecto si hay error
        }
    }
	
	public void escucharBoton(JButton jButton, JButton[][] botones, int fila, int col, JLabel intentos, JLabel record) {
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarIntentos(intentos);
				JButton botonAyuda=getBotonResaltado();
				if(botonAyuda != null) {
					botonAyuda.setBorder(UIManager.getBorder("Button.border"));
				}
				Color colorElegido = getColor();
				if(esValido(jButton, botones, fila, col, colorElegido)) {
					jButton.setBackground(colorElegido);
					jButton.setEnabled(false);
					actualizarBotonesPrendidos();
				}
				randomizarColor();
				consultarVictoria(botones, intentos, record);
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
	private void randomizarColor() {
		Random random = new Random();
		int r=random.nextInt(6);
		if(r==0) {
			this.color=Color.BLACK;
		}
		if(r==1) {
			this.color=Color.BLUE;
		}
		if(r==2) {
			this.color=Color.RED;
		}
		if(r==3) {
			this.color=Color.YELLOW;
		}
		if(r==4) {
			this.color=Color.GREEN;
		}
		if(r==5) {
			this.color=Color.PINK;
		}
		
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
		
		if(this.dificultad.equals("facil")) {
			this.ayudas=2;
		}
		if(this.dificultad.equals("normal")) {
			this.ayudas=4;
		}
		if(this.dificultad.equals("dificil")) {
			this.ayudas=6;
		}
		JButton botonAyuda=getBotonResaltado();
		if(botonAyuda != null) {
			botonAyuda.setBorder(UIManager.getBorder("Button.border"));
		}
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

	public void consultarVictoria(JButton[][] botones, JLabel intentos, JLabel record) {
		int dificultad=botones[0].length*botones[0].length;
		if(this.cuadrosPrendidos == dificultad) {
			if(dificultad==25) {
				if(this.recordFacil<dificultad || Integer.parseInt(intentos.getText()) < this.recordFacil) {
					this.recordFacil=Integer.parseInt(intentos.getText());
					escribirRecordEnArchivo("recordfacil.txt",intentos.getText());
					record.setText(intentos.getText());
				}
			}
			else if(dificultad==36) {
				if(this.recordNormal<dificultad || Integer.parseInt(intentos.getText()) < this.recordNormal) {
					this.recordNormal=Integer.parseInt(intentos.getText());
					escribirRecordEnArchivo("recordnormal.txt",intentos.getText());
					record.setText(intentos.getText());
				}
			}
			else if(dificultad==49) {
				if(this.recordDificil<dificultad || Integer.parseInt(intentos.getText()) < this.recordDificil) {
					this.recordDificil=Integer.parseInt(intentos.getText());
					escribirRecordEnArchivo("recorddificil.txt",intentos.getText());
					record.setText(intentos.getText());
				}
			}
			mostrarVictoria(botones, intentos);	
		}
	}
	

	public void mostrarVictoria(JButton[][] botones, JLabel intentos) {		
		JOptionPane.showMessageDialog(null, "Has ganado el juego!", "Victoria", JOptionPane.INFORMATION_MESSAGE);
		apagarBotones(botones);
		intentos.setText(Integer.toString(0));
	}
	public int obtenerRecord(String dificultad) {
		if (dificultad.equals("facil")) {
            return this.recordFacil; 
        } else if (dificultad.equals("normal")) {
        	return this.recordNormal; 
        } 
        return this.recordDificil; 
        
	}
	private void escribirRecordEnArchivo(String nombreArchivo, String contenido) {
	    try (FileWriter fw = new FileWriter(nombreArchivo, false)) { 
	        fw.write(contenido);
	    } catch (Exception e) {
	        System.err.println("Error al escribir en " + nombreArchivo + ": " + e.getMessage());
	    }
	}
	public void sugerencia(JButton[][] botones, JButton sugerenciaBtn) {
		if(this.ayudas>0) {
			this.ayudas--;
			sugerenciaBtn.setText("Ayuda ("+this.ayudas+")");
			for(int i = 0; i < botones[0].length; i++) {
				for(int j = 0; j < botones[0].length; j++) {
					if (botones[i][j].getBackground().equals(Color.WHITE)) {
						if (comprobarVecinos(botones, i, j)) {
						    botones[i][j].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
						    this.botonResaltado=botones[i][j];
						    return;
						}
					}
				}
			}
		}
		return;
		
	}

	private boolean comprobarVecinos(JButton[][] botones, int i, int j) {
	    Color color = getColor(); 
	    int filas = botones.length;
	    int columnas = botones[0].length;

	    if (i + 1 < filas && botones[i + 1][j] != null && botones[i + 1][j].getBackground().equals(color)) {
	        return false;
	    }
	    if (j + 1 < columnas && botones[i][j + 1] != null && botones[i][j + 1].getBackground().equals(color)) {
	        return false;
	    }
	    if (i - 1 >= 0 && botones[i - 1][j] != null && botones[i - 1][j].getBackground().equals(color)) {
	        return false;
	    }
	    if (j - 1 >= 0 && botones[i][j - 1] != null && botones[i][j - 1].getBackground().equals(color)) {
	        return false;
	    }

	    return true;
	}
	public int getAyudas() {
		return this.ayudas;
	}
	private Color getColor() {
		return this.color;
	}
	private JButton getBotonResaltado() {
		return this.botonResaltado;
	}

	public int configurarDificultad(String dificultadElegida) {
		if (dificultadElegida.equals("facil")) {
            return 5;  
        } else if (dificultadElegida.equals("normal")) {
            return 6;  
        } else {
            return 7; 
        }
	}



	
	
	

	


	

	
	
	
}
