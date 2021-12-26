package package01;

import java.awt.Color;

import javax.swing.Timer;

public class Constants {
	public static int currentScreenWidth = 800;
	public static int currentScreenHeight = 600;
	public static int fps = 60;
	public static Timer timer;
	
	public static String originalSentence, sentence;
	
	public static int cursorInt = 0;
	public static int strikes = 0;
	public static int seconds = 0;
	public static boolean gameModeEnabled = false;
	public static boolean firstCharacter = true;
	public static Color currentColor = Color.white;
	
	//save stuffs
	public static int frame = 0;
	public static String currentPlayer = "";
	public static String[] list;
	
	public static void resetConstants() {
		gameModeEnabled = false;
		originalSentence = "";
		sentence = "";
		cursorInt = 0;
		seconds = 0;
		strikes = 0;
		firstCharacter = true;
		currentColor = Color.white;
	}
}
