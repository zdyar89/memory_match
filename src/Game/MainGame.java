package Game;

import Entities.Player;
import Entities.*;
import Utilities.SoundClip;
import Utilities.SoundManager;
import edu.utc.game.*;
import edu.utc.game.Math.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class MainGame extends Game implements Scene {
    public static MainGame game;

    public static void main(String[] args) {
        SceneManager.run();
    }

    public static final float GRAVITY = 9.8f;
    private boolean gotClick;
    //private Reticle marker;
    private Cell cell;
    private Grid grid;
    private Texture img;
    private Player player;
    private SoundClip boom;
    private Text clickDisplay;
    public long timePassed;
    public long clickCount;
    public SoundClip backgroundMusic;
    public List<Cell> cells;

    public void reset() {
        clickCount = 0;
        backgroundMusic = new SoundClip("tridentkeep");
        backgroundMusic.loop();
        SoundManager.add(backgroundMusic);
    }

    public MainGame() {
        initUI(1280,720,"SceneHW");
        GL11.glClearColor(.9f, .9f, .9f, 0f);
        gotClick = false;
        player = new Player(new Vector2f(Game.ui.getWidth()/8f, Game.ui.getHeight()/1.5f));
        //marker = new Reticle();
        img = new Texture("res/Textures/kirby.png");
        boom = new SoundClip("boom");
        backgroundMusic = new SoundClip("tridentkeep");
        backgroundMusic.loop();
        SoundManager.add(backgroundMusic);
        timePassed = 0;
        clickCount = 0;
        clickDisplay = new Text(40, Game.ui.getHeight() - 50, 30, 30, String.valueOf(clickCount));
        Game.ui.enableMouseCursor(true);
        Game.ui.showMouseCursor(true);
        cells = new java.util.LinkedList<Cell>();
    }

    @Override
    public String getName() {
        return "Main";
    }

    @Override
    public void onMouseEvent(int button, int action, int mods) {
        if (button==0 && action== GLFW.GLFW_PRESS)
        {
            Vector2f lastClick = new Vector2f(Game.ui.getMouseLocation().x, Game.ui.getMouseLocation().y);
            gotClick = true;
        }
        if(clickCount >= 10)
        {
            SceneManager.end();
        }
    }

    @Override
    public void onKeyEvent(int key, int scancode, int action, int mods) {
        if (action == org.lwjgl.glfw.GLFW.GLFW_PRESS && key == GLFW.GLFW_KEY_BACKSPACE) {

            SceneManager.pause();
        }

    }


    public Scene drawFrame(int delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        Vector2f coordinates = new Vector2f(Game.ui.getMouseLocation().x, Game.ui.getMouseLocation().y);

        Cell test1 = new Cell;
        test1.setLocation(350, 350);
        test1.setColor(.33, .21, .75);
        cells.add(test1);
        
        //not sure we need the draw loop
        for(GameObject c: cells)
        {
            
        }
        
        if (gotClick) {
            boom.play();
            clickCount++;
        }

        /* Update */
        updateUI();
        //marker.setLocation(coordinates);
        player.update(delta);
        
        timePassed += delta;

        /* Draw */
        drawUI();
        player.draw();
        


        gotClick = false;
        return this;
    }

    private <T extends GameObject> void update(List<T> gameObjects, int delta) {
        for (GameObject go : gameObjects) {
            go.update(delta);
        }
    }

    private <T extends GameObject> void draw(List<T> gameObjects) {
        for (GameObject go : gameObjects) {
            go.draw();
        }
    }

    private <T extends GameObject> void deactivate(List<T> objects) {
        objects.removeIf(o -> !o.isActive());
    }

    private void updateUI() {

        clickDisplay = new Text(40,Game.ui.getHeight() - 50, 30, 30, String.valueOf(clickCount));
    }

    private void drawUI() {
        clickDisplay.draw();
    }
}
