package br.com.thiago;

import javax.swing.JLabel;

/**
 * 
 * @author Thiago Guimaraes
 * @date 27/04/2020
 */
public class Principal {
	
	private Tabuleiro tabuleiro = new Tabuleiro();
	private Tabuleiro [] solucoes = new Tabuleiro[32];
	private int [] direcoes = Tabuleiro.getDirecoes();
	private Tela tela;
	
	public Principal() {
		
        for (int i = 0; i < solucoes.length; i++) {
        	solucoes[i] = new Tabuleiro();
        }
        
        tela = new Tela();
		tela.getView().setText(tabuleiro.toString());
	}
	
	public static void main(String[] args) throws Exception {

		Principal principal = new Principal();
		
		long t1 = System.currentTimeMillis();
		if (principal.procuraSolucao(1, principal.tela)) {
			System.out.println("Solution found in " + (System.currentTimeMillis() - t1) + " ms");
			
			JLabel tempo = principal.getTela().getTempo();
			tempo.setText(tempo.getText() + (System.currentTimeMillis() - t1) + " ms");

		} else {
			System.out.println("No solution found!?");
		}
		
		for (Tabuleiro tabuleiro : principal.solucoes) {
			principal.tela.getView().setText(tabuleiro.toString());
			Thread.sleep(1000);
		}
	}
	
	public boolean procuraSolucao(int jogada, Tela tela) throws InterruptedException {
		
		for (int x = 0; jogada <= 31 && x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				
				for (int direction : direcoes) {
					
					if (tabuleiro.movimentar(x, y, direction)) {
						
						this.copyBoard(tabuleiro, solucoes[jogada]);
						
//						tela.getLabel().setText(tabuleiro.toString());
						
						if ( !(jogada >= 31 && tabuleiro.estaOcupado(3, 3)) ) {
							
							if (procuraSolucao(jogada + 1, tela) ) {
								return true;
							} else {
								tabuleiro.voltaMovimento(x, y, direction);
							}
							
						} else {
							return true;
						}
					}
				}
			}
		}

		return false;
	}
	
	private void copyBoard(Tabuleiro original, Tabuleiro destino) {
        for (int x = 0; x < 7; x++) {
	       for (int y = 0; y < 7; y++) {
	    	   destino.getPosicoes()[x][y] = original.getPosicoes()[x][y];
           }
        }
	}

	public Tela getTela() {
		return tela;
	}

	public void setTela(Tela tela) {
		this.tela = tela;
	}
}
