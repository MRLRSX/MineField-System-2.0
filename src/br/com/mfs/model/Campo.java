package br.com.mfs.model;

import java.util.ArrayList;
import java.util.List;

import br.com.mfs.model.enums.CampoEvento;

public class Campo {

	private final int linha;
	private final int coluna;

	private boolean minado = false;
	private boolean aberto = false;
	private boolean marcado = false;

	private List<Campo> vizinhos = new ArrayList<>();
    private List<CampoObservador> observadores = new ArrayList<>();
    
	public Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public void registrarObservador(CampoObservador observador) {
		observadores.add(observador);
	}
	
	public void notificarObservador(CampoEvento evento) {
		observadores.stream().forEach(p -> p.eventOcorreu(this, evento));
	}
	
	public boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.getLinha();
		boolean colunaDiferente = coluna != vizinho.getColuna();
		boolean diagonal = linhaDiferente && colunaDiferente;

		int deltaLinha = Math.abs(linha - vizinho.getLinha());
		int deltaColuna = Math.abs(coluna - vizinho.getColuna());

		int deltaGeral = deltaLinha + deltaColuna;

		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}

	}
    public void alternarMarcao() {
    	if(!aberto) {
    		marcado = !marcado;
    		notificarObservador(CampoEvento.MARCAR);
    	}else {
    		notificarObservador(CampoEvento.DESMARCAR);
    	}
    }
	public boolean abrir() {
		
		if(!aberto && !marcado) {
			aberto = true;
			
			if(minado) {
				notificarObservador(CampoEvento.EXPLODIR);
				return true;
			}
			setAberto(true);
		
			if(vizinhacaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		}else {
			return false;			
		}
		
	}
    
    public boolean vizinhacaSegura() {
    	return vizinhos.stream().noneMatch(v -> v.minado);
    }
    
    public boolean objetivoAlcancado() {
    	boolean desvendado = !minado && aberto;
    	boolean protegido = minado && marcado;
    	return desvendado || protegido;
    }
    
    public long minasVizinhaca() {
    	return vizinhos.stream().filter(v -> v.minado).count();
    }
    
    public void reiniciar() {
    	aberto = false;
    	minado = false;
    	marcado = false;
    	
    }
	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public boolean isMarcado() {
		return marcado;
	}
	
	public boolean isAberto() {
		return aberto;
	}
	protected void setAberto(boolean aberto) {
		this.aberto = aberto;
		notificarObservador(CampoEvento.ABRIR);
	}
	public boolean isMinado() {
		return minado;
	}

	public void minar() {
		minado = true;
	}
    

}
