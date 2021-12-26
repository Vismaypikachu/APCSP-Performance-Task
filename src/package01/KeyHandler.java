package package01;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	game m_game;
	
	public KeyHandler(game g) {
		m_game = g;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		char choice = e.getKeyChar();
		System.out.println("Key was typed: " + choice);
		m_game.m_mechanics.keyTyped(choice);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
