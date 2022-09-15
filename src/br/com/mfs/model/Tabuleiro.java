package br.com.mfs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import br.com.mfs.model.enums.CampoEvento;

public class Tabuleiro implements CampoObservador {

	private Integer linhas;
	private Integer colunas;
	private Integer minas;

	private final List<Campo> campos = new ArrayList<>();
	private final List<Consumer<Boolean>> observadores = new ArrayList<>();

	public Tabuleiro(Integer linhas, Integer colunas, Integer minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}
 
	public void paraCada(Consumer<Campo> funcao) {
		campos.forEach(funcao);
	}
	public void registrarObservador(Consumer<Boolean> observador) {
		observadores.add(observador);
	}

	public void notificarObservador(Boolean resultado) {
		observadores.stream().forEach(p -> p.accept(resultado));
	}

	public void abrir(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(c -> c.abrir());

	}

	private void mostrarMinas() {
		campos.stream().filter(c -> c.isMinado()).forEach(c -> c.setAberto(true));
	}

	public void marcar(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(c -> c.alternarMarcao());
	}

	private void gerarCampos() {
		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				Campo campo = new Campo(linha, coluna);
				campo.registrarObservador(this);
				campos.add(campo);
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

	@Override
	public void eventOcorreu(Campo campo, CampoEvento campoEvento) {
		if (campoEvento == CampoEvento.EXPLODIR) {
			mostrarMinas();
			notificarObservador(false);
		} else if (objetivoAlcancado()) {
			notificarObservador(true);
		}

	}

	public Integer getLinhas() {
		return linhas;
	}

	public Integer getColunas() {
		return colunas;
	}
	

}
