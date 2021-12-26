package package01;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Mechanics {
	
	game m_game;
	
	JLabel[] charArr = new JLabel[90];
	
	Random r = new Random();
	
	public Mechanics(game g) {
		m_game = g;
	}
	
	public void askForName() {
		
	}
	
	public void chooseRandomSentence() {
		try {
			Scanner scanner = new Scanner(new File("Practice.txt"));
			scanner.nextLine();
			scanner.nextLine();
			scanner.nextLine();
			scanner.nextLine();
			scanner.nextLine();
			int lineCount = 0;
			while(scanner.hasNextLine()) {
				lineCount++;
				scanner.nextLine();
			}
			Scanner fileScanner = new Scanner(new File("Practice.txt"));
			int x = r.nextInt(lineCount)+5;			
			for(int i = 0; i < x; i++) {
				fileScanner.nextLine();
			}
			Constants.originalSentence = Constants.sentence = fileScanner.nextLine();
		} 
		catch (Exception e) {
			//originalSentence = sentence = "Hello, please type this sentence out. I don't know when this stops displaying, but I hope it continues working.";
			Constants.originalSentence = Constants.sentence = "Hello, please type this sentence out. Something got messed up with your Practice.txt file (maybe you deleted it), so this is the only sentence you can practice rn. Maybe download again?";
		}
	}
	
	public void displaySentence() {
		for(int i = 0; i < charArr.length; i++) {
			if(Constants.sentence.length() < 90 && Constants.sentence.length() == Constants.cursorInt) {
				Constants.sentence = null;
				Constants.gameModeEnabled = false;
				Constants.timer.stop();
				showResults();
			}
			else if(i < Constants.sentence.length()) charArr[i].setText(""+Constants.sentence.charAt(i));
			else charArr[i].setText("");
		}
	}

	public int countWords(String s) {
		Scanner scanner = new Scanner(s);
		int count = 0;
		while(scanner.hasNext()) {
			scanner.next();
			count++;
		}
		return count;
	}
	
	public void keyTyped(char choice) {
		if(Constants.firstCharacter == true) {
			Constants.timer.start();
			Constants.firstCharacter = false;
		}
		if(Constants.cursorInt == charArr.length-1) {
			Constants.sentence = Constants.sentence.substring(charArr.length);
			Constants.cursorInt = 0;
		}
		else if(Constants.sentence != null && choice != Constants.sentence.charAt(Constants.cursorInt)) {
			Constants.currentColor = Color.red;
			Constants.strikes++;
		}
		else if(Constants.cursorInt < Constants.sentence.length() && choice == Constants.sentence.charAt(Constants.cursorInt)) {
			Constants.currentColor = Color.white;
			Constants.cursorInt++;
		}
	}
	
	public void showResults() {
		m_game.m_ui.window.removeKeyListener(m_game.m_kHandler);
		
		int mistakes = Constants.strikes;
		double accuracy = ((double) Constants.originalSentence.length()*100/(Constants.originalSentence.length()+Constants.strikes));
		int wpm = (int) (countWords(Constants.originalSentence)*60/Constants.seconds);
		
		m_game.m_ui.resultsArea.setText("Test Completed! Nice Job :D\nMistakes: "  + mistakes + "\nAccuracy: " + accuracy + "%\nWPM: " + wpm);
		saveResults(new Date().toString() + " " + wpm + " " + accuracy + " " + mistakes);
	
	}

	public void simpleTimer() {
		Constants.timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
					Constants.seconds++;				
			}
		});	
	}

	//type 1- first, type 2- new
	public void createNewSave(int type) {	
		//read save file list and add to array
		try {
			//count lines
			int count = 1;
			if(type == 1) count = 0;
			Scanner reader = new Scanner(new File("Data/saves.txt"));
			while(reader.hasNextLine()) {
				String s = reader.nextLine();
				if(!s.equals("")) count++;
			}
			reader.close();
			//adds to array
			Constants.list = new String[count];
			if(!m_game.m_ui.jtf.getText().equals("") && type == 2) Constants.list[0] = m_game.m_ui.jtf.getText() + ".txt";
			else if(type == 2) Constants.list[0] = "Empty";
			count = 1;
			if(type == 1) count = 0;
			Scanner reader2 = new Scanner(new File("Data/saves.txt"));
			while(reader2.hasNextLine()) {
				String s = reader2.nextLine();
				if(contains(Constants.list, s) == false) Constants.list[count] = s;
				count++;
			}
			reader2.close();
			//print save file list to save file
			PrintStream saveFile = new PrintStream(new File("Data/saves.txt"));
			for(int i = 0; i < Constants.list.length-1; i++) {
				if(Constants.list[i] != null) saveFile.println(Constants.list[i]);
			}
			saveFile.print(Constants.list[Constants.list.length-1]);
			saveFile.close();
		} catch(Exception e) {}		
		
		
		//look to see if player file exists
		
		File file = new File("Data/"+Constants.currentPlayer+".txt");
		if(type == 2 && !file.exists()) {
			try {
				PrintStream ps = new PrintStream(file);
				System.out.println("TEST");
				ps.close();
			}catch(Exception e) {}
		}
	}
	
	public boolean contains(String arr[], String s) {
		boolean x = false;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != null && arr[i].equals(s)) {
				x = true;
			}
		}
		return x;
	}

	public void loadSave(String currentPlayer) {
		if(currentPlayer.contains(".txt")) {
			Constants.currentPlayer = currentPlayer.substring(0, currentPlayer.length()-4);
			m_game.m_ui.currentPlayerName.setText(Constants.currentPlayer);
			System.out.println("Current Player: " + Constants.currentPlayer);
			m_game.m_ui.backButton.doClick();
		}
	}
	
	public void updateLoadScreen(int x) {
		try {
			for(int i = 0; i < 5; i++) {
			m_game.m_ui.saveButtons[i].setText(Constants.list[x*5+i]);
			}
		}
		catch(Exception e) {}
	}

	public void saveResults(String combo) {
		try{
			System.out.println("Test1");
			//Count Lines
			Scanner scanner = new Scanner(new File("Data/"+Constants.currentPlayer+".txt"));
			int count = 0;
			while(scanner.hasNextLine()) {
				String s = scanner.nextLine();
				if(!s.equals("")) count++;
			}
			scanner.close();
			System.out.println("Test2");
			//add lines to array
			String[] list = new String[count+1];
			System.out.println("Test2.5 length" + list.length);
			Scanner scanner2 = new Scanner(new File("Data/"+Constants.currentPlayer+".txt"));
			count = 0;
			while(scanner2.hasNextLine()) {
				String s = scanner2.nextLine();
				if(!s.equals("")) list[count] = s;
				count++;
			}
			scanner2.close();
			System.out.println("Test 2.9");
			list[count] = combo;
			System.out.println("Test3 List: " + list[count]);
			//print to file
			PrintStream ps = new PrintStream(new File("Data/"+Constants.currentPlayer+".txt"));		
			for(int i = 0; i < list.length-1; i++) {
				ps.println(list[i]);
			}
			ps.print(list[list.length-1]);
			ps.close();
			System.out.println("Test4");
		} catch(Exception e) {}
		
		
		//DATE WPM ACCURACY MISTAKES
	}
	
	public void updateResults() {
		//On [DATE] at [TIME], [NAME] achieved [WPM] words per minute, at [ACCURACY]% accuracy, and [MISTAKES] mistakes.
	}
}