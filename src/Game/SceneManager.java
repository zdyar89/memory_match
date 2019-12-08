package Game;

import VFX.Background;
import edu.utc.game.Scene;
import edu.utc.game.SimpleMenu;

import static Game.MainGame.game;
import static Game.MainGame.main;


class SceneManager {

	static void run() {
		game = new MainGame();
		game.registerGlobalCallbacks();
		SimpleMenu mainMenu = new SimpleMenu("mMenu");
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Welcome to Character Match!", 1, 0, 0, 1, 1, 1), null);
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 40, 20, 20, "Launch Game", 1, 0, 0, 1, 1, 1), game);
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		mainMenu.select(0);
		game.setScene(mainMenu);
		game.gameLoop();
	}

	static void changeScene(Scene scene) {
		game.setScene(scene);
	}

	static void pause() {


		SimpleMenu pauseMenu = new SimpleMenu("pMenu");
		pauseMenu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Continue", 1, 0, 0, 1, 1, 1), game);
		pauseMenu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit Game", 0, 0, 1, 1, 1, 1), null);
		pauseMenu.addItem(new SimpleMenu.SelectableText(20, 140, 20, 20, String.valueOf(game.clickCount), 1, 1, 0, 1, 1, 1), game);
		pauseMenu.select(0);
		changeScene(pauseMenu);


	}

	static void victory()
	{

		//add logic in the end method to pick which scene should be used if the player wins or loses
		SimpleMenu gameOver = new SimpleMenu("GameOver");
		gameOver.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Game Over!", 1, 0, 0, 1, 1, 1), game);
		gameOver.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "You completed only X matches", 1, 1, 0, 1, 1, 1), game);
		//insert number of matches
		gameOver.addItem(new SimpleMenu.SelectableText(20, 100, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		gameOver.select(0);
		//changeScene(gameOver);

		SimpleMenu mainMenu = new SimpleMenu("mMenu");
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Welcome to Character Match!", 1, 0, 0, 1, 1, 1), null);
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 40, 20, 20, "Launch Game", 1, 0, 0, 1, 1, 1), game);
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		mainMenu.select(0);
		game.resetGame();


		SimpleMenu victory = new SimpleMenu("Victory");
		victory.addItem(new SimpleMenu.SelectableText(20, 20, 20 ,20, "Congratulations! You Win!", 1, 0, 0, 1, 1, 1), game);
		victory.addItem(new SimpleMenu.SelectableText(20, 50, 20, 20, "Return to Main Menu", 1, 1, 0, 1, 1, 1), mainMenu);
		victory.addItem(new SimpleMenu.SelectableText(20, 80, 20 ,20, "Exit", 1, 0, 0, 1, 1, 1), null);
		victory.select(0);

		changeScene(victory);
	}


}