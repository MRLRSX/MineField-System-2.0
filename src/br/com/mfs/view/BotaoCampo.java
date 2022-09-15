package br.com.mfs.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.mfs.model.Campo;
import br.com.mfs.model.CampoObservador;
import br.com.mfs.model.enums.CampoEvento;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener{

	private Campo campo;
	private final Color BG_PADRAO = new Color(184, 184, 184);
	private final Color BG_MARCADO = new Color(8, 179, 247);
	private final Color BG_EXPLOSÃO = new Color(189, 66, 68);
	private final Color BG_TEXTO_VERDE = new Color(0, 100, 0);
	
	
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		addMouseListener(this);
		campo.registrarObservador(this);
	}

	@Override
	public void eventOcorreu(Campo campo, CampoEvento campoEvento) {
		switch (campoEvento) {

		case ABRIR:
			aplicarEstiloAbrir();
			break;
		case MARCAR:
			aplicarEstiloMarcar();
			break;
		case EXPLODIR:
			aplicarEstiloExplodir();
			break;
		default:
			aplicarEstiloDefault();
			break;
		}
	}

	private void aplicarEstiloDefault() {
		// TODO Auto-generated method stub
		
	}


	private void aplicarEstiloExplodir() {
		// TODO Auto-generated method stub

	}

	private void aplicarEstiloMarcar() {
		// TODO Auto-generated method stub

	}

	private void aplicarEstiloAbrir() {
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createLineBorder(BG_PADRAO));
		
		switch((int) campo.minasVizinhaca()) {
		case 1:
			setForeground(BG_TEXTO_VERDE);
			break;
		case 2: 
			setForeground(Color.BLUE);
			break;
		case 3: 
			setForeground(Color.YELLOW);
			break;
		case 4:
		case 5: 
		case 6:
			setForeground(Color.RED);
			break;
		default:
			setForeground(Color.PINK);
		}
		String valor = !campo.vizinhacaSegura() ?  campo.minasVizinhaca() + "" : "";
		setText(valor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	 if(e.getButton() == 1) {
		 campo.abrir();
	 }	else {
		 campo.alternarMarcao();
	 }
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
