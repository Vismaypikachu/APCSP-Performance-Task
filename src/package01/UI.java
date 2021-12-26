package package01;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UI {
	game m_game;

	JFrame window;
	Container con;
	GraphicsDevice gDevice;

	Font titleFont, normalFont;


	JPanel titleNamePanel, startButtonPanel, typingAreaPanel, resultsAreaPanel, backButtonPanel, inputPanel, loadPanel, navigationPanel;
	JLabel titleLabel, currentPlayer, currentPlayerName, navigationLabel;
	JButton startButton, backButton, submitButton, loadButton, pgUpButton, pgDownButton;
	JLabel titleNameLabel;
	JTextArea resultsArea;
	JTextField jtf;
	
	JButton[] saveButtons = new JButton[5];
	
	InputHandler inHandler = new InputHandler();

	public UI(game g) {
		m_game = g;
	}

	public void initializeUI() {
		GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gDevice = gEnvironment.getDefaultScreenDevice();
		//setup
		window = new JFrame();
		window.setSize(Constants.currentScreenWidth, Constants.currentScreenHeight);
		//window.setSize(1070, 712);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setResizable(false);
		con = window.getContentPane();		

		createFont();
		createUIComponent();
		
		window.setVisible(true);
	}

	public void createFont() {
		titleFont = new Font("Ever After", Font.PLAIN, 63);
		normalFont = new Font("Times New Roman", Font.PLAIN, 22);
	}

	public void createUIComponent() {
		//create title screen
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, 100, 600, 120);
		titleNamePanel.setBackground(Color.black);
		titleNamePanel.setLayout(new GridLayout(2,1));

		titleLabel = new JLabel("Typing Test (APCSP P Task)", JLabel.CENTER);
		titleLabel.setForeground(Color.white);
		titleLabel.setFont(titleFont);

		titleNameLabel = new JLabel("By Vismay Patel (v. 1.3)", JLabel.CENTER);
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(normalFont);

		titleNamePanel.setVisible(true);
		
		//input panel
		
		inputPanel = new JPanel();
		inputPanel.setBounds(100, 300, 600, 100);
		inputPanel.setBackground(Color.black);
		inputPanel.setLayout(new GridLayout(2,2));

		jtf = new JTextField();
		jtf.setFont(normalFont);
		inputPanel.add(jtf);
		
		submitButton = new JButton("New Save (your name)");
		submitButton.setFont(normalFont);
		submitButton.setBackground(Color.black);
		submitButton.setForeground(Color.white);
		submitButton.setFocusable(false);
		submitButton.setFocusPainted(false);
		submitButton.addActionListener(inHandler);
		inputPanel.add(submitButton);
		
		currentPlayer = new JLabel("Current Player: ", JLabel.RIGHT);
		currentPlayer.setFont(normalFont);
		currentPlayer.setBackground(Color.black);
		currentPlayer.setForeground(Color.white);
		
		currentPlayerName = new JLabel(""+Constants.currentPlayer, JLabel.LEFT);
		currentPlayerName.setFont(normalFont);
		currentPlayerName.setBackground(Color.black);
		currentPlayerName.setForeground(Color.white);
		
		inputPanel.add(currentPlayer);
		inputPanel.add(currentPlayerName);
		
		con.add(inputPanel);

		//start button
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 400, 200, 100);
		startButtonPanel.setBackground(Color.black);
		startButtonPanel.setLayout(new GridLayout(2,1));

		startButton = new JButton("START");
		startButton.setForeground(Color.white);
		startButton.setBackground(Color.black);
		startButton.setFont(normalFont);
		startButton.setFocusPainted(false);
		startButton.addActionListener(m_game.m_cHandler);
		startButton.setActionCommand("start");
		
		loadButton = new JButton("LOAD");
		loadButton.setForeground(Color.white);
		loadButton.setBackground(Color.black);
		loadButton.setFont(normalFont);
		loadButton.setFocusPainted(false);
		loadButton.addActionListener(m_game.m_cHandler);
		loadButton.setActionCommand("load");

		startButtonPanel.setVisible(true);

		titleNamePanel.add(titleLabel);
		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);	
		startButtonPanel.add(loadButton);
		con.add(titleNamePanel);
		con.add(startButtonPanel);
	}

	public void createGameScreen() {
		window.addKeyListener(m_game.m_kHandler);
		
		typingAreaPanel = new JPanel();
		typingAreaPanel.setBounds(100, 100, 600, 200);
		typingAreaPanel.setLayout(new GridLayout(3,30));
		typingAreaPanel.setBackground(Color.black);
		
		for(int i = 0; i < m_game.m_mechanics.charArr.length; i++) {
			m_game.m_mechanics.charArr[i] = new JLabel("A");
			m_game.m_mechanics.charArr[i].setBackground(Color.black);
			m_game.m_mechanics.charArr[i].setForeground(Color.white);
			m_game.m_mechanics.charArr[i].setOpaque(true);
			m_game.m_mechanics.charArr[i].setFont(normalFont);
			typingAreaPanel.add(m_game.m_mechanics.charArr[i]);
		}
		
		con.add(typingAreaPanel);
		
		resultsAreaPanel = new JPanel();
		resultsAreaPanel.setBounds(100, 350, 600, 150);
		resultsAreaPanel.setBackground(Color.black);
		
		resultsArea = new JTextArea("Test in progress, please type the letter in white to continue. Accuracy is based on how many characters are typed correctly.");
		resultsArea.setBounds(100, 350, 600, 150);
		resultsArea.setBackground(Color.black);
		resultsArea.setForeground(Color.white);
		resultsArea.setLineWrap(true);
		resultsArea.setWrapStyleWord(true);
		resultsArea.setEditable(false);
		resultsArea.setFont(normalFont);
		
		resultsAreaPanel.add(resultsArea);
		
		backButtonPanel = new JPanel();
		backButtonPanel.setBounds(350, 500, 100, 100);
		backButtonPanel.setBackground(Color.black);
		
		backButton = new JButton("BACK");
		backButton.setForeground(Color.white);
		backButton.setBackground(Color.black);
		backButton.setFont(normalFont);
		backButton.setFocusPainted(false);
		backButton.addActionListener(m_game.m_cHandler);
		backButton.setActionCommand("back");
		
		backButtonPanel.add(backButton);
		
		con.add(resultsAreaPanel);
		con.add(backButtonPanel);
	}

	public void updateCursor(Color color) {
		for(int i = 0; i < m_game.m_mechanics.charArr.length; i++) {
			m_game.m_mechanics.charArr[i].setBackground(Color.black);
			m_game.m_mechanics.charArr[i].setForeground(Color.white);
		}
		m_game.m_mechanics.charArr[Constants.cursorInt].setBackground(color);
		m_game.m_mechanics.charArr[Constants.cursorInt].setForeground(Color.black);
	}

	public void createLoadScreen() {
		//int saves = howManySaves();
		loadPanel = new JPanel();
		loadPanel.setBounds(240, 100, 300, 300);
		loadPanel.setBackground(Color.black);
		loadPanel.setLayout(new GridLayout(5,1));
		
		//initialize first list
		jtf.setText(null);
		m_game.m_mechanics.createNewSave(1);
				
		for(int i = 0; i < saveButtons.length; i++) {
			if(i < Constants.list.length) saveButtons[i] = new JButton(Constants.list[i]);
			else saveButtons[i] = new JButton("Empty");
			saveButtons[i].setBackground(Color.black);
			saveButtons[i].setForeground(Color.white);
			saveButtons[i].setFont(normalFont);
			saveButtons[i].setFocusable(false);
			saveButtons[i].setFocusPainted(false);
			saveButtons[i].addActionListener(m_game.m_cHandler);
			saveButtons[i].setActionCommand(""+i);		
			
			loadPanel.add(saveButtons[i]);
		}
		
		con.add(loadPanel);
		
		navigationPanel = new JPanel();	
		navigationPanel.setBounds(540, 100, 70, 300);
		navigationPanel.setBackground(Color.black);
		navigationPanel.setLayout(new GridLayout(3,1));
		
		con.add(navigationPanel);
		
		navigationLabel = new JLabel("Scroll", JLabel.CENTER);
		navigationLabel.setForeground(Color.white);
		navigationLabel.setBackground(Color.black);
		navigationLabel.setFont(normalFont);
		navigationPanel.add(navigationLabel);
		
		pgUpButton = new JButton("/\\");
		pgUpButton.setForeground(Color.white);
		pgUpButton.setBackground(Color.black);
		pgUpButton.setFont(normalFont);
		pgUpButton.setFocusable(false);
		pgUpButton.setFocusPainted(false);
		pgUpButton.addActionListener(m_game.m_cHandler);
		pgUpButton.setActionCommand("up");
		navigationPanel.add(pgUpButton);
		
		pgDownButton = new JButton("\\/");
		pgDownButton.setForeground(Color.white);
		pgDownButton.setBackground(Color.black);
		pgDownButton.setFont(normalFont);
		pgDownButton.setFocusable(false);
		pgDownButton.setFocusPainted(false);
		pgDownButton.addActionListener(m_game.m_cHandler);
		pgDownButton.setActionCommand("down");
		navigationPanel.add(pgDownButton);
		
		backButtonPanel = new JPanel();
		backButtonPanel.setBounds(350, 450, 100, 100);
		backButtonPanel.setBackground(Color.black);
		
		backButton = new JButton("BACK");
		backButton.setForeground(Color.white);
		backButton.setBackground(Color.black);
		backButton.setFont(normalFont);
		backButton.setFocusPainted(false);
		backButton.addActionListener(m_game.m_cHandler);
		backButton.setActionCommand("back2");
		backButtonPanel.add(backButton);
		
		con.add(backButtonPanel);
	}
	
	public int howManySaves() {
		int x = 0;
		try {
			Scanner scanner = new Scanner(new File("Data/saves.txt"));
			while(scanner.hasNextLine()) {
				String s = scanner.nextLine();
				if(!s.equals("")) x++;
			}
		}
		catch(Exception e) {}
		return x;
	}
	
	public class InputHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			Constants.currentPlayer = jtf.getText();
			m_game.m_mechanics.createNewSave(2);
			currentPlayerName.setText(Constants.currentPlayer);
		}
	}
}
