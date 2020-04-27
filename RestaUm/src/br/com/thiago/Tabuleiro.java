package br.com.thiago;

/**
 * 
 * @author Thiago Guimaraes
 * @date 27/04/2020
 */
public class Tabuleiro {

	private static final String OCUPADA = "T";
	private static final String VAZIA = "O";
	private static final String INVALIDA = " ";

	private static final int DIREITA = 0;
	private static final int SOBE = 1;
	private static final int ESQUERDA = 2;
	private static final int DESCE = 3;
	
	private static int[] direcoes = { DIREITA, SOBE, ESQUERDA, DESCE };

	private String[][] posicoes = new String[7][7];

	public Tabuleiro() {

		for (int i = 0; i < posicoes.length; i++) {
			for (int j = 0; j < posicoes.length; j++) {
				posicoes[i][j] = "T";
			}
		}
		posicoes[3][3] = VAZIA;

		posicoes[0][0] = INVALIDA;
		posicoes[0][1] = INVALIDA;
		posicoes[1][0] = INVALIDA;
		posicoes[1][1] = INVALIDA;

		posicoes[5][5] = INVALIDA;
		posicoes[5][6] = INVALIDA;
		posicoes[6][5] = INVALIDA;
		posicoes[6][6] = INVALIDA;

		posicoes[5][0] = INVALIDA;
		posicoes[5][1] = INVALIDA;
		posicoes[6][0] = INVALIDA;
		posicoes[6][1] = INVALIDA;

		posicoes[0][5] = INVALIDA;
		posicoes[0][6] = INVALIDA;
		posicoes[1][5] = INVALIDA;
		posicoes[1][6] = INVALIDA;
	}
	
	private int proximoX(int x, int direcao) {

		int newX = x;
		
		if(direcao == DIREITA) {
			newX += 2;
		}else if(direcao == ESQUERDA) {
			newX -= 2;
		}
		
		return newX;
	}

	private int proximoY(int y, int direcao) {
		int newY = y;

		if(direcao == SOBE) {
			newY -= 2;
		}else if(direcao == DESCE) {
			newY += 2;
		}
		
		return newY;
	}

	private boolean validaMovimento(int xOrigem, int yOrigem, int xDestino, int yDestino) {

		if (xOrigem < 0 || xOrigem >= posicoes.length)
			return false;

		if (yOrigem < 0 || yOrigem >= posicoes[xOrigem].length)
			return false;

		if (xDestino < 0 || xDestino >= posicoes.length)
			return false;

		if (yDestino < 0 || yDestino >= posicoes[xDestino].length)
			return false;

		if (posicoes[xOrigem][yOrigem] == INVALIDA)
			return false;

		if (posicoes[xDestino][yDestino] == INVALIDA)
			return false;

		if (posicoes[xDestino][yDestino] != VAZIA)
			return false;

		if (posicoes[xOrigem][yOrigem] != OCUPADA)
			return false;

		if (posicoes[(xOrigem + xDestino) / 2][(yOrigem + yDestino) / 2] != OCUPADA)
			return false;

		return true;
	}
	

	public void voltaMovimento(int x, int y, int direcao) {
		int novoX = proximoX(x, direcao);
		int novoY = proximoY(y, direcao);

		posicoes[novoX][novoY] = VAZIA; 
		posicoes[x][y] = OCUPADA; 
		posicoes[(x + novoX) / 2][(y + novoY) / 2] = OCUPADA;
	}

	public boolean movimentar(int x, int y, int direcao) {

		int novoX = proximoX(x, direcao);
		int novoY = proximoY(y, direcao);

		if (validaMovimento(x, y, novoX, novoY)) {

			posicoes[novoX][novoY] = OCUPADA;
			posicoes[x][y] = VAZIA;
			posicoes[(x + novoX) / 2][(y + novoY) / 2] = VAZIA;

			return true;
		}

		return false;
	}

	public static int[] getDirecoes() {
		return direcoes;
	}

	public String[][] getPosicoes() {
		return posicoes;
	}

	public boolean estaOcupado(int x, int y) {
		return posicoes[x][y] == OCUPADA;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("<html>")
		  .append("<table style='background-color: black;'>");
		
		for (int i = 0; i < posicoes.length; i++) {
			sb.append("<tr>");
			
			for (int j = 0; j < posicoes.length; j++) {
				
				if(posicoes[j][i].equals(OCUPADA)) {
					sb.append("<td style='background-color: blue; width:30px; height:10px;'>").append("</td>");
					
				}else if(posicoes[j][i].equals(VAZIA)) {
					sb.append("<td style='background-color: green; width:30px; height:10px;'>").append("</td>");
					
				} else if(posicoes[j][i].equals(INVALIDA)) {
					sb.append("<td style='background-color: white; width:30px; height:10px;'>").append("</td>");
				}
			}
			sb.append("</tr>");
		}
		sb.append("</table></html>");
		
		return sb.toString();
	}
}
