package br.com.mfs.view;

import java.awt.GridLayout;


import javax.swing.JPanel;

import br.com.mfs.model.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {
  
	public PainelTabuleiro(Tabuleiro tabuleiro) {
		setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));
		int total = tabuleiro.getLinhas() * tabuleiro.getColunas();
		
		tabuleiro.paraCada(c -> add(new BotaoCampo(c)));
		tabuleiro.registrarObservador(e -> {
			//TODO mostrar resultado pro usuario
		});
		
	}
	
	
}
