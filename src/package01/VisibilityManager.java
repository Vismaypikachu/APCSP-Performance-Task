package package01;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;

public class VisibilityManager {

	game m_game;

	public VisibilityManager(game g) {
		m_game = g;
	}

	public void showTitleScreen() {
		m_game.m_ui.titleNamePanel.setVisible(true);
		m_game.m_ui.startButtonPanel.setVisible(true);
		m_game.m_ui.inputPanel.setVisible(true);
		m_game.m_ui.backButtonPanel.setVisible(false);
	}

	public void hideTitleScreen() {
		m_game.m_ui.titleNamePanel.setVisible(false);
		m_game.m_ui.startButtonPanel.setVisible(false);
		m_game.m_ui.inputPanel.setVisible(false);
	}

	public void hideGameScreen() {
		Constants.gameModeEnabled = false;
		m_game.m_ui.typingAreaPanel.setVisible(false);
		m_game.m_ui.resultsAreaPanel.setVisible(false);
		m_game.m_ui.backButtonPanel.setVisible(false);
	}

	public void showLoadScreen() {
		hideTitleScreen();
		m_game.m_ui.loadPanel.setVisible(true);
		m_game.m_ui.navigationPanel.setVisible(true);
		m_game.m_ui.backButtonPanel.setVisible(true);
	}
	
	public void hideLoadScreen() {
		m_game.m_ui.loadPanel.setVisible(false);
		m_game.m_ui.navigationPanel.setVisible(false);
	}
}
