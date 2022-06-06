package jogodavelha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JogoDaVelha {
	
	static ArrayList<Integer> jogador = new ArrayList<Integer>();
	static ArrayList<Integer> computador = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		
		char [][] tabuleiro = {{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '}};
		
		imprimeTabuleiro(tabuleiro);
			
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Escolha sua posição de 1 a 9: ");
			int posJogador = scan.nextInt();	
			while(jogador.contains(posJogador) || computador.contains(posJogador)) {
				System.out.println("Posição já ocupada, escolha outra posição.");
				posJogador = scan.nextInt();
			}
			
			jogada(tabuleiro, posJogador, "jogador");
			
			String resultado = vencedor();
			if(resultado.length() > 0) {
				System.out.println(resultado);
				break;
			}
			
			Random rand = new Random();
			int posComputador = rand.nextInt(9) + 1;
			while(jogador.contains(posComputador) || computador.contains(posComputador)) {
				posComputador = rand.nextInt(9) + 1;
			}
			
			jogada(tabuleiro, posComputador, "computador");
				
			imprimeTabuleiro(tabuleiro);
			
			resultado = vencedor();
			if(resultado.length() > 0) {
				System.out.println(resultado);
				break;
				
						
			}
			
		}
				
	}
		
	public static void imprimeTabuleiro(char[][] tabuleiro) {
		
		for(char[] linha : tabuleiro) {
			for(char t : linha) {
				System.out.print(t);
			}
			System.out.println();		
		}
	}
	
	public static void jogada(char[][] tabuleiro, int posicao, String usuario) {
		
		char simbolo = ' ';
		
		if(usuario.equals("jogador")) {
			simbolo = 'X';
			jogador.add(posicao);
		} else if(usuario.equals("computador")) {
			simbolo = 'O';
			computador.add(posicao);
		}
		
		switch(posicao) {
			case 1:
				tabuleiro[0][0] = simbolo;
				break;
			case 2:
				tabuleiro[0][2] = simbolo;
				break;
			case 3:
				tabuleiro[0][4] = simbolo;
				break;
			case 4:
				tabuleiro[2][0] = simbolo;
				break;
			case 5:
				tabuleiro[2][2] = simbolo;
				break;
			case 6:
				tabuleiro[2][4] = simbolo;
				break;
			case 7:
				tabuleiro[4][0] = simbolo;
				break;
			case 8:
				tabuleiro[4][2] = simbolo;
				break;
			case 9:
				tabuleiro[4][4] = simbolo;
				break;
			default:
				break;
		}
	}
	
	public static String vencedor() {
		
		List linha1 = Arrays.asList(1, 2, 3);
		List linha2 = Arrays.asList(4, 5, 6);
		List linha3 = Arrays.asList(7, 8, 9);
		List coluna1 = Arrays.asList(1, 4, 7);
		List coluna2 = Arrays.asList(2, 5, 8);
		List coluna3 = Arrays.asList(3, 6, 9);
		List diagonal1 = Arrays.asList(1, 5, 9);
		List diagonal2 = Arrays.asList(7, 5, 3);
		
		List<List> checaVencedor = new ArrayList<List>();
		checaVencedor.add(linha1);
		checaVencedor.add(linha2);
		checaVencedor.add(linha3);
		checaVencedor.add(coluna1);
		checaVencedor.add(coluna2);
		checaVencedor.add(coluna3);
		checaVencedor.add(diagonal1);
		checaVencedor.add(diagonal2);
		
		for(List l : checaVencedor) {
			if(jogador.containsAll(l)) {
				return "Parabens, voce ganhou!!";
			}else if(computador.containsAll(l)) {
				return "Computador venceu!!";
			} else if (jogador.size() + computador.size() == 9) {
				return "Empate!!";
			}
		}
				
		return "";
	}
}