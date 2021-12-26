package package01;

public class Update implements Runnable{

	game m_game;
	Thread gameThread;


	public Update(game g) {
		m_game = g;
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}


	@Override
	public void run() {

		double drawInterval = 1000000000/Constants.fps;
		double nextDrawTime = System.nanoTime() + drawInterval;


		while(gameThread != null) {
			update();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000000;

				if(remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);

				nextDrawTime += drawInterval;
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		try {
			if(Constants.gameModeEnabled == true) {
			//System.out.println("Seconds " + Constants.seconds);
			m_game.m_ui.window.requestFocus();
			m_game.m_ui.updateCursor(Constants.currentColor);
			m_game.m_mechanics.displaySentence();	
			}
		}
		catch(Exception e) {
			
		}	
	}
}
