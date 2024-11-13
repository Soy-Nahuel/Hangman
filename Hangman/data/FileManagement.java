/*
* CREATED BY NAHUEL TELLECHEA FREIRE
*/

package data;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileManagement {
	
	public FileManagement() {}
	
	public void fileFolderExistence() {
		File file  = new File("data.txt");
		File directory = new File("gameData");
		
		if(!directory.exists()) {
			File path = new File("."+ File.separator+ "gameData");
			path.mkdir();
		}
		
		if(!file.exists()) {
			if(directory.isDirectory()) {
				File path = new File("."+ File.separator+ "gameData"+ File.separator+ "data.txt");
				try {
					path.createNewFile();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Oops! Something went wrong while creating the file. Try closing the application and opening it again", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public void writeData(int victory, int defeat, int timesPlayed) {
		final String pathFile = "."+ File.separator+ "gameData"+ File.separator+ "data.txt";
		final String phrase = victory+ "\n"+ defeat+ "\n"+ timesPlayed;
		
		FileWriter writing = null;
		
		try {
			writing = new FileWriter(pathFile);
			
			for(int  i=0; i<phrase.length(); i++) {
				writing.write(phrase.charAt(i));
			}
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Oops! Something went wrong while saving the progress. Try closing the application and opening it again", "ERROR", JOptionPane.ERROR_MESSAGE);
		}finally {
			try {
				writing.close();
			} catch (IOException e) {}
		}
	}
	
	public String getData() {
	    FileReader input = null;
	    BufferedReader myBuffer = null;
	    StringBuilder data = new StringBuilder();  
	    String line = "";

	    try {
	        input = new FileReader("." + File.separator + "gameData" + File.separator + "data.txt");
	        myBuffer = new BufferedReader(input);
	        
	        while ((line = myBuffer.readLine()) != null) {  
	            data.append(line).append("\n"); 
	        }
	    } catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Oops! Something went wrong while retrieving the progress. Try closing the application and opening it again", "ERROR", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        try {
	            if (myBuffer != null) myBuffer.close();
	            if (input != null) input.close();
	        } catch (IOException e) {}
	    }
	    
	    return data.toString();  
	}
	
	public boolean deleteData() {
		File path = new File("."+ File.separator+ "gameData"+ File.separator+ "data.txt");
		return (path.exists() && path.isFile()) && path.delete();
	}
}