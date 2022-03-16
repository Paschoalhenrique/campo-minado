package br.com.aula.minado;

import br.com.aula.minado.modelo.Tabuleiro;
import br.com.aula.minado.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {

		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);
		new TabuleiroConsole(tabuleiro);
		
		
		
		
		
	}
}
