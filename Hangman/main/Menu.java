/*
* CREATED BY NAHUEL TELLECHEA FREIRE
*/

package main;

import data.Data;
import data.FileManagement;
import game.PlayHangman;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
	
	String word;
	
	public Menu() {
		setTitle("Menu | Hangman");
		setSize(389, 304);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		components();
		setVisible(true);
	}
	
	private void components() {
		FileManagement management = new FileManagement();
		management.fileFolderExistence();
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JLabel lblTitle = new JLabel("Hangman");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Gadugi", Font.ITALIC, 26));
		lblTitle.setBounds(126, 10, 120, 35);
		getContentPane().add(lblTitle);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPlay.setBounds(126, 97, 116, 21);
		play(btnPlay);
		getContentPane().add(btnPlay);
		
		JButton btnData = new JButton("View data");
		btnData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnData.setBounds(126, 153, 116, 21);
		viewData(btnData);
		getContentPane().add(btnData);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(126, 213, 116, 21);
		exit(btnExit);
		getContentPane().add(btnExit);
	}
	
	private void requestWord() {
	    while (word == null || word.trim().isEmpty() || word.trim().split("\\s+").length != 1) {
	        word = JOptionPane.showInputDialog("Please enter a single word:");
	        
	        if (word == null) {
	            JOptionPane.showMessageDialog(null, "No word entered. Exiting input.");
	            break; 
	        } else if (word.trim().split("\\s+").length != 1) {
	            JOptionPane.showMessageDialog(null, "Please enter a single word:");
	        }
	    }
	}
	
	private void play(JButton btnPlay) {
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	requestWord();
            	if(verifyWord(word)) {
            	
            	PlayHangman play = new PlayHangman(word);
            	dispose();
            	}
            }
        });
    }
	
	private boolean verifyWord(String word) {
		return word != null;
	}
	
	private void viewData(JButton btnData) {
        btnData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Data data = new Data();
            	dispose();
            }
        });
    }
	
	private void exit(JButton btnExit) {
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int answer = JOptionPane.showConfirmDialog(null, "Do you wish to continue?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            	if(answer == JOptionPane.YES_OPTION) System.exit(0);
            }
        });
    }
}