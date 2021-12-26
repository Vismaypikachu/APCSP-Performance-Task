package package01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class game {
	
	ChoiceHandler m_cHandler = new ChoiceHandler();
	KeyHandler m_kHandler = new KeyHandler(this);
	
	UI m_ui = new UI(this);
	VisibilityManager m_vm = new VisibilityManager(this);
	Mechanics m_mechanics = new Mechanics(this);
	Update m_update = new Update(this);
	
	public static void main(String[] args) {
		new game();
	}

	public game() {
		m_ui.initializeUI();
	}
	
	public class ChoiceHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String yourChoice = event.getActionCommand();
			switch(yourChoice) {
				case "start": Constants.resetConstants(); try{m_ui.window.removeKeyListener(m_kHandler);}catch(Exception e) {} m_mechanics.chooseRandomSentence(); m_vm.hideTitleScreen(); Constants.gameModeEnabled = true; m_ui.createGameScreen(); m_update.startGameThread(); m_mechanics.simpleTimer(); break;
				case "load": m_ui.createLoadScreen(); m_vm.showLoadScreen(); break;
				case "back": m_vm.hideGameScreen(); m_vm.showTitleScreen(); break;
				case "back2": m_vm.hideLoadScreen(); m_vm.showTitleScreen(); break;
				case "0": m_mechanics.loadSave(m_ui.saveButtons[0].getText()); break;
				case "1": m_mechanics.loadSave(m_ui.saveButtons[1].getText()); break;
				case "2": m_mechanics.loadSave(m_ui.saveButtons[2].getText()); break;
				case "3": m_mechanics.loadSave(m_ui.saveButtons[3].getText()); break;
				case "4": m_mechanics.loadSave(m_ui.saveButtons[4].getText()); break;
				case "up": if(Constants.frame < (Constants.list.length)/5+1) { Constants.frame++; m_mechanics.updateLoadScreen(Constants.frame);} break;
				case "down": if(Constants.frame > 0) { Constants.frame--; m_mechanics.updateLoadScreen(Constants.frame);} break;
			}
		}
	}
}