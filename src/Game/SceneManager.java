package Game;

import VFX.Background;
import edu.utc.game.Game;
import edu.utc.game.Scene;
import edu.utc.game.Text;
import edu.utc.game.SimpleMenu;
import edu.utc.game.Texture;

import static Game.MainGame.game;
import static Game.MainGame.main;


class SceneManager {

	private int clickCount;

	public void setClickCount(int input)
	{
		clickCount = input;
	}
	static void run() {
		game = new MainGame();
		game.registerGlobalCallbacks();
		SimpleMenu mainMenu = new SimpleMenu("mMenu");
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Welcome to Memory Match!", 1, 0, 0, 1, 1, 1), null);
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

		SimpleMenu mainMenu = new SimpleMenu("mMenu");
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Welcome to Character Match!", 1, 0, 0, 1, 1, 1), null);
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 40, 20, 20, "Launch Game", 1, 0, 0, 1, 1, 1), game);
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		mainMenu.select(0);
		game.resetGame();


		SimpleMenu victory = new SimpleMenu("Victory");
		//victory.addItem(new Background(new Texture("")));
		victory.addItem(new SimpleMenu.SelectableText(20, 20, 20 ,20, "Congratulations! You Completed All Matches!", 1, 0, 0, 1, 1, 1), game);
		victory.addItem(new SimpleMenu.SelectableText(20, 50, 20 ,20, "Clicks: " + String.valueOf(game.clickCount), 1, 0, 0, 1, 1, 1), game);


		victory.addItem(new SimpleMenu.SelectableText(20, 80, 20, 20, "Return To Main Menu", 1, 1, 0, 1, 1, 1), mainMenu);
		victory.addItem(new SimpleMenu.SelectableText(20, 110, 20 ,20, "Exit", 1, 0, 0, 1, 1, 1), null);
		victory.select(0);
		changeScene(victory);
	}


}