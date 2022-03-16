package br.com.aula.minado.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.aula.minado.excecao.ExplosaoException;

public class Campo {

	// declarando os atributos classe campo
	private final int linha;
	private final int coluna;

	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;

	// Criando lista de vizinhos do tipo campo generics
	private List<Campo> vizinhos = new ArrayList<>();

	// Contrutores
	 public Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	// logica adicionar vizinho
	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;

		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;

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

	// Implementação do campo não aberto
	void alternarMarcacao() {
		if (!aberto) {
			marcado = !marcado;
		}
	}

	// se campo ñ abri e ñ marcado
	public boolean abrir() {
		if (!aberto && !marcado) {
			aberto = true;
			// me retorna uma excecao
			if (minado) {
				throw new ExplosaoException();

			}
			if (vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		} else {

		}
		return false;
	}

	// criando outro metodo
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	void minar() {
		minado = true;
	}
	
	public void setMinado(boolean minado) {
		this.minado = minado;
	}
	
	public boolean isMarcado() {
		return marcado;
	}

	public boolean isMinado() {
		return minado;
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public List<Campo> getVizinhos() {
		return vizinhos;
	}

	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}
	
	 
	
	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
		
	}
	@Override
	public String toString() {
		if (marcado) {
			return "X";
		}else if (aberto && minado) {
			return "*";
		}else if (aberto && minasNaVizinhanca() > 0) {
			return Long.toString(minasNaVizinhanca());
		}else if(aberto) {
			return" ";
		}else {
			return "?";
		}
	}
	
}












