package br.com.mfs.model;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

	private Integer linhas;
	private Integer colunas;
	private Integer minas;

	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(Integer linhas, Integer colunas, Integer minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}

	public void abrir(int linha, int coluna) {
		try {

			campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
					.ifPresent(c -> c.abrir());

		} catch (Exception explosao) {
			// FIXME Ajustar a Implmentação metodo abrir
			campos.forEach(c -> c.setAberto(true));
			throw explosao;
		}
	}

	public void marcar(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(c -> c.alternarMarcao());
	}

	private void gerarCampos() {
		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				campos.add(new Campo(linha, coluna));
			}
		}

	}

	private void associarVizinhos() {
		for (Campo campo01 : campos) {
			for (Campo campo02 : campos) {
				campo01.adicionarVizinho(campo02);
			}
		}

	}

	private void sortearMinas() {
		Long minasArmadas = 1L;
		do {
			minasArmadas = campos.stream().filter(x -> x.isMinado()).count();
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
		} while (minasArmadas < minas);
	}

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}

	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}

}
