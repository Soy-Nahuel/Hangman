/*
* CREATED BY NAHUEL TELLECHEA FREIRE
*/

package data;

import main.Menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Data extends JFrame{
	
	private JTextArea txtAreaData;
	
	public Data() {
		setTitle("Data | Hangman");
		setSize(502, 403);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		components();
		setVisible(true);
	}
	
	private void components() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JLabel lblTitle = new JLabel("Game Data");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(167, 10, 153, 36);
		lblTitle.setFont(new Font("Gadugi", Font.ITALIC, 26));
		getContentPane().add(lblTitle);
		
		txtAreaData = new JTextArea();
		txtAreaData.setFont(new Font("Ink Free", Font.PLAIN, 16));
		txtAreaData.setEditable(false);
		txtAreaData.setBounds(10, 71, 207, 285);
		getContentPane().add(txtAreaData);
		
		JButton btnDelete = new JButton("Delete data");
		btnDelete.setBounds(308, 112, 136, 29);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		delete(btnDelete);
		getContentPane().add(btnDelete);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReturn.setBounds(308, 284, 136, 29);
		goBack(btnReturn);
		getContentPane().add(btnReturn);
		information();
	}
	
	private void information() {
		FileManagement management = new FileManagement();
		final String[] dataLines = management.getData().split("\n");
		
		if(dataLines.length>2) {
			final String phrase = "Number of victories: "+ dataLines[0]
					  +"\nNumber of defeats: "+ dataLines[1]
					  +"\nNumber of times played: "+ dataLines[2];
			txtAreaData.setText(phrase);
		}else {
			txtAreaData.setText("There is no data yet");
		}
	}
	
	private void delete(JButton btnDelete) {
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	FileManagement management = new FileManagement();
            	management.deleteData();
            	information();
            }
        });
    }
	
	private void goBack(JButton btnReturn) {
		btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Menu menu = new Menu();
            	dispose();
            }
        });
    }
}