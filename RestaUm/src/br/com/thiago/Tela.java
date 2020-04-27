package br.com.thiago;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 * 
 * @author Thiago Guimaraes
 * @date 27/04/2020
 */
public class Tela extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel view;
	private JLabel autor;
	private JLabel tempo;

	public Tela() {

		setSize(500, 500);
		setTitle("Resta Um ");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.montaTela();
	}
	
	public void montaTela() {
		
		view = new JLabel();
		view.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		view.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 34));
		view.setHorizontalAlignment(JLabel.CENTER);
		getContentPane().add(view);
		
		view.setText("OI");
		
		autor = new JLabel("2020 Thiago Guimarães");
		getContentPane().add(autor, BorderLayout.SOUTH);
		
		tempo = new JLabel("Tempo para achar a solução: ");
		getContentPane().add(tempo, BorderLayout.NORTH);
		view.setVisible(true);
	}

	public JLabel getView() {
		return view;
	}
	public JLabel getAutor() {
		return autor;
	}
	public JLabel getTempo() {
		return tempo;
	}
}

