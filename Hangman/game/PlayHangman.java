/*
* CREATED BY NAHUEL TELLECHEA FREIRE
*/

package game;

import data.FileManagement;
import main.Menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayHangman extends JFrame {

	private HangmanPanel panelGame;
    private String word;
    private String phrase;
    private char[] guessedLetters;
    private JTextArea txtLetter;
    private final int incorrectAttempts = 6;
    private int limb = -1;
    private int victory = 0;
    private int defeat = 0;
    private int timesPlayed = 0;

    public PlayHangman(String word) {
        setTitle("Game | Hangman");
        setSize(645, 396);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.word = word;
        components();
        setVisible(true);
    }

    private void components() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        panelGame = new HangmanPanel();
        panelGame.setBounds(190, 5, 250, 180);
        panelGame.setPreferredSize(new Dimension(250, 180));
        panel.add(panelGame);
        panelGame.setLayout(null);

        guessedLetters = initializeGuessedLetters(word.length());

        txtLetter = new JTextArea(new String(guessedLetters)); 
        txtLetter.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtLetter.setBounds(10, 212, 600, 101);
        txtLetter.setLineWrap(true);
        txtLetter.setWrapStyleWord(true);
        txtLetter.setEditable(false);
        txtLetter.setOpaque(false);
        panel.add(txtLetter);

        JButton btnReturn = new JButton("Return");
        btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnReturn.setBounds(481, 156, 102, 29);
        goBack(btnReturn);
        panel.add(btnReturn);

        JButton btnGiveUp = new JButton("Give up");
        btnGiveUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnGiveUp.setBounds(481, 24, 102, 29);
        giveUp(btnGiveUp);
        panel.add(btnGiveUp);

        JTextField txtWord = new JTextField();
        txtWord.setBounds(20, 323, 140, 26);
        panel.add(txtWord);
        txtWord.setColumns(10);

        JButton btnSend = new JButton("Send");
        btnSend.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSend.setBounds(278, 323, 102, 29);
        input(btnSend, txtWord);
        panel.add(btnSend);
        
        addEnterKeyListener(txtWord, btnSend);
        saveData();
        getContentPane().add(panel);
    }
    
    private void addEnterKeyListener(JTextField txtWord, JButton btnSend) {
        txtWord.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnSend.doClick();
                }
            }
        });
    }

    private char[] initializeGuessedLetters(int length) {
        char[] underscores = new char[length * 2 - 1]; 
        for (int i = 0; i < length; i++) {
            underscores[i * 2] = '_';
            if (i < length - 1) {
                underscores[i * 2 + 1] = ' '; 
            }
        }
        return underscores;
    }

    public void updateGuessedLetters(char letter, int position) {
        if (position >= 0 && position < word.length()) {
            guessedLetters[position * 2] = letter; 
            txtLetter.setText(new String(guessedLetters));
        }
    }

    private void input(JButton btnSend, JTextField txtWord) {
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phrase = txtWord.getText().trim().toLowerCase(); 
                txtWord.setText("");

                if (phrase.length() == 1) {
                    char letter = phrase.charAt(0);
                    boolean correctGuess = false;

                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == letter) { 
                            updateGuessedLetters(letter, i); 
                            correctGuess = true;
                        }
                    }

                    if (!correctGuess) {
                        limb++;
                        panelGame.setLimb(limb);
                    }

                } else { 
                    if (phrase.equals(word)) {
                        victory++;
                        timesPlayed++;
                        storeData();
                        txtLetter.setText(word);
                        JOptionPane.showMessageDialog(null, "Congratulations! You guessed the word: " + word + "!");
                        Menu menu = new Menu();
                		dispose();
                    } else if (phrase.length() > word.length()) { 
                        limb++;
                        panelGame.setLimb(limb);
                    } else { 
                        boolean foundLetters = false; 
                        for (int i = 0; i < phrase.length(); i++) {
                            char currentChar = phrase.charAt(i);
                            for (int j = 0; j < word.length(); j++) {
                                if (currentChar == word.charAt(j)) {
                                    updateGuessedLetters(currentChar, j);
                                    foundLetters = true;
                                }
                            }
                        }
                        if (!foundLetters) { 
                            limb++;
                            panelGame.setLimb(limb);
                        }
                    }
                }

                if (limb >= incorrectAttempts) {
                    defeat++;
                    timesPlayed++;
                    storeData();
                    txtLetter.setText(word);
                    JOptionPane.showMessageDialog(null, "Game Over! You've used all attempts. The word was: " + word);
                    Menu menu = new Menu();
            		dispose();
                }

                if (!new String(guessedLetters).contains("_")) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You guessed the word: " + word + "!");
                    Menu menu = new Menu();
                    dispose();
                }
            }
        });
    }

    
    private void giveUp(JButton btnGiveUp) {
        btnGiveUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(null, 
                        "¿Do you wish to continue?",
                        "Warning", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                
                if (answer == JOptionPane.YES_OPTION) {
                    defeat++; 
                    timesPlayed++; 
                    storeData(); 
                    txtLetter.setText(word);
                    JOptionPane.showMessageDialog(null, "Game Over! The word was: " + word);
                    Menu menu = new Menu();
                    dispose();
                }
            }
        });
    }

    private void goBack(JButton btnReturn) {
    	btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int answer = JOptionPane.showConfirmDialog(null, 
                        "¿Do you wish to continue??",
                        "Warning", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
            	
            	if(answer == JOptionPane.YES_OPTION) {
            		Menu menu = new Menu();
            		dispose();
            	}
            }
        });
    }
    
    private void saveData() {
    	FileManagement management = new FileManagement();
		final String[] dataLines = management.getData().split("\n");
		
		if(dataLines.length>2) {
			victory = Integer.parseInt(dataLines[0]);
			defeat = Integer.parseInt(dataLines[1]);
			timesPlayed = Integer.parseInt(dataLines[2]);
		}
    }

    private void storeData() {
    	FileManagement management = new FileManagement();
    	management.writeData(victory, defeat, timesPlayed);
    }

}