package br.com.mfs.view;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.mfs.model.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {
  
	public PainelTabuleiro(Tabuleiro tabuleiro) {
		setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));
		
		
		tabuleiro.paraCada(c -> add(new BotaoCampo(c)));
		tabuleiro.registrarObservador(e -> {
			SwingUtilities.invokeLater(() ->{				
				if(e == true) {
					JOptionPane.showMessageDialog(this, "VOCÊ GANHOU !!!");
				}else{
					JOptionPane.showMessageDialog(this, "PERDEU !!!");				
				}
				tabuleiro.reiniciar();
			});
		});
		
	}
	
	
}
