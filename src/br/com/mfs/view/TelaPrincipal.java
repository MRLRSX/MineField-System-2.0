package br.com.mfs.view;

import java.awt.Font;


import javax.swing.JFrame;

import br.com.mfs.model.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	public TelaPrincipal() {
		Tabuleiro tabuleiro = new Tabuleiro(16, 30, 2);
		
		add(new PainelTabuleiro(tabuleiro));
		
		setFont(new Font("System", Font.PLAIN, 14));
		setTitle("MINEFIELD");

		setSize(690, 438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	
	}

	public static void main(String[] args) {
       new TelaPrincipal();
	}
}
