package br.com.msf.application;

import javax.swing.table.TableColumn;

import br.com.mfs.model.Tabuleiro;

public class Program {
	public static void main(String[] args) {
      Tabuleiro tabuleiro = new Tabuleiro(3, 3, 9);
      tabuleiro.registrarObservador( e -> {
    	  if(e == true) {
    		  System.out.println("GG");
    	  }else {
    		  System.out.println("perdeu");
    	  }
      });
      tabuleiro.marcar(0, 0);
      tabuleiro.marcar(0, 1);
      tabuleiro.marcar(0, 2);
      tabuleiro.marcar(1, 0);
      tabuleiro.marcar(1, 1);
      tabuleiro.marcar(1, 2);
      tabuleiro.marcar(2, 0);
      tabuleiro.marcar(2, 1);
      //tabuleiro.marcar(2, 2);
      tabuleiro.abrir(2, 2);
	}
}
