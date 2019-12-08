package Game;
import Entities.*;
import Utilities.*;
import VFX.Background;
import VFX.Wallpaper;
import edu.utc.game.*;
import edu.utc.game.Math.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.*;

public class MainGame extends Game implements Scene {
    public static MainGame game;

    public static void main(String[] args) {
        SceneManager.run();
    }

    public static final float GRAVITY = 9.8f;
    private ArrayList<Cell> cellCache;
    private GameObject square;
    private boolean gotClick;
    private Reticle marker;
    private Texture kirby;
    private Texture egg;
    private Texture samus;
    private Texture bowser;
    private GameBG background;
    private Player player;
    private SoundClip boom;
    private Text clickDisplay;
    private Text matchesDisplay;
    public long timePassed;
    public long miniTimer;
    public long clickCount;
    public SoundClip backgroundMusic;
    public List<Cell> cells;
    public int matches;



    public void setRandom(List<Cell> inputA)
    {
        ArrayList<Vector2f> grid = new ArrayList<>();
        //1st row
        grid.add(new Vector2f(250, 50));
        grid.add(new Vector2f(350, 50));
        grid.add(new Vector2f(450, 50));
        grid.add(new Vector2f(550, 50));
        grid.add(new Vector2f(650, 50));
        grid.add(new Vector2f(750, 50));
        grid.add(new Vector2f(850, 50));
        grid.add(new Vector2f(150, 50));

        //2nd row
        grid.add(new Vector2f(150, 150));
        grid.add(new Vector2f(250, 150));
        grid.add(new Vector2f(350, 150));
        grid.add(new Vector2f(450, 150));
        grid.add(new Vector2f(550, 150));
        grid.add(new Vector2f(650, 150));
        grid.add(new Vector2f(750, 150));
        grid.add(new Vector2f(850, 150));

        //3rd row
        grid.add(new Vector2f(150, 250));
        grid.add(new Vector2f(250, 250));
        grid.add(new Vector2f(350, 250));
        grid.add(new Vector2f(450, 250));
        grid.add(new Vector2f(550, 250));
        grid.add(new Vector2f(650, 250));
        grid.add(new Vector2f(750, 250));
        grid.add(new Vector2f(850, 250));

        //4th row
        grid.add(new Vector2f(150, 350));
        grid.add(new Vector2f(250, 350));
        grid.add(new Vector2f(350, 350));
        grid.add(new Vector2f(450, 350));
        grid.add(new Vector2f(550, 350));
        grid.add(new Vector2f(650, 350));
        grid.add(new Vector2f(750, 350));
        grid.add(new Vector2f(850, 350));

        //5th row
        grid.add(new Vector2f(150, 450));
        grid.add(new Vector2f(250, 450));
        grid.add(new Vector2f(350, 450));
        grid.add(new Vector2f(450, 450));
        grid.add(new Vector2f(550, 450));
        grid.add(new Vector2f(650, 450));
        grid.add(new Vector2f(750, 450));
        grid.add(new Vector2f(850, 450));

        //6th row
        grid.add(new Vector2f(150, 550));
        grid.add(new Vector2f(250, 550));
        grid.add(new Vector2f(350, 550));
        grid.add(new Vector2f(450, 550));
        grid.add(new Vector2f(550, 550));
        grid.add(new Vector2f(650, 550));
        grid.add(new Vector2f(750, 550));
        grid.add(new Vector2f(850, 550));
        Collections.shuffle(grid);


        for(int i = 0; i <= 47; i++)
        {
            if( i < 12)
            {
                Cell bow = new Cell(bowser, grid.get(i));
                inputA.add(bow);
            }
            else if(i >= 12 && i < 24)
            {
                Cell kir = new Cell(kirby, grid.get(i));
                inputA.add(kir);
            }
            else if(i >= 24 && i < 36)
            {
                Cell eg = new Cell(egg, grid.get(i));
                inputA.add(eg);
            }
            else if(i >= 36)
            {
                Cell sam = new Cell(samus, grid.get(i));
                inputA.add(sam);
            }
        }


    }
    public void reset() {
        clickCount = 0;
        backgroundMusic = new SoundClip("tridentkeep");
        backgroundMusic.loop();
        SoundManager.add(backgroundMusic);
        setRandom(cells);
    }

    public MainGame() {


        initUI(1100,720,"SceneHW");

        cellCache = new ArrayList<>();
        matches = 0;
        GL11.glClearColor(.35f, .22f, .69f, 0f);
        gotClick = false;
        player = new Player(new Vector2f(Game.ui.getWidth()/8f, Game.ui.getHeight()/1.5f));
        marker = new Reticle();
        background = new GameBG();
        kirby = new Texture("res/Textures/kirby.png");
        egg = new Texture("res/Textures/Egg.png");
        bowser = new Texture("res/Textures/Bowser.png");
        samus = new Texture("res/Textures/Samus.png");
        boom = new SoundClip("boom");
        backgroundMusic = new SoundClip("tridentkeep");
        backgroundMusic.loop();
        SoundManager.add(backgroundMusic);
        timePassed = 0;

        clickCount = 0;
        clickDisplay = new Text(40, Game.ui.getHeight() - 50, 30, 30, String.valueOf(clickCount));
        matchesDisplay = new Text(40, Game.ui.getHeight() - 70, 30, 30, String.valueOf(matches));
        Game.ui.enableMouseCursor(false);
        Game.ui.showMouseCursor(false);
        cells = new java.util.LinkedList<Cell>();
        setRandom(cells);
    }


    @Override
    public String getName() {
        return "Main";
    }

    public void resetGame(){

        matches = 0;
        clickCount = 0;
        timePassed = 0;
        miniTimer = 0;
        cells = new java.util.LinkedList<Cell>();
        //setRandom(cells);

    }

    @Override
    public void onMouseEvent(int button, int action, int mods) {
        if (button==0 && action== GLFW.GLFW_PRESS)
        {
            Vector2f lastClick = new Vector2f(Game.ui.getMouseLocation().x, Game.ui.getMouseLocation().y);
            gotClick = true;
        }
        if(clickCount >= 10 || matches == cells.size() / 2)
        {
            SceneManager.victory();
        }
    }

    @Override
    public void onKeyEvent(int key, int scancode, int action, int mods) {
        if (action == org.lwjgl.glfw.GLFW.GLFW_PRESS && key == GLFW.GLFW_KEY_BACKSPACE) {

            SceneManager.pause();
        }

    }

    public void time(int delta)
    {
        if(timePassed > 9750)
        {
            for(Cell c: this.cells)
            {
                c.isSelected = false;
            }
        }
        else {
            for (Cell c : this.cells) {
                c.selected();
            }
        }
    }

    public void manageCache(int delta) {
        if (cellCache.size() == 2) {
            boolean match = false;
            miniTimer += delta;
            if (cellCache.get(0).getTexture() == cellCache.get(1).getTexture()) {
                //match found
                cellCache.get(0).deactivate();
                cellCache.get(0).deactivate();
                cellCache.remove(0);
                cellCache.remove(0);
                System.out.println("Match Found");
                match = true;
            } else {

                if (miniTimer >= 1250) {
                    cellCache.get(0).deselect();
                    cellCache.get(1).deselect();
                    cellCache.remove(0);
                    cellCache.remove(0);
                    miniTimer = 0;
                }
            }
            if (match) {
                matches++;
                match = false;
            }


        }
    }



    public Scene drawFrame(int delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        Vector2f coordinates = new Vector2f(Game.ui.getMouseLocation().x, Game.ui.getMouseLocation().y);
        background.draw();
        manageCache(delta);


        marker.setLocation(coordinates);
        timePassed += delta;
        if(timePassed < 10000)
        {
            time(delta);
        }


        for(GameObject c: cells)
        {
            c.draw();
        }

        for(GameObject c: cells)
        {
            c.update(delta);
        }



        if (gotClick) {
            for(Cell c: cells)
            {

                if(marker.intersects(c))
                {
                    if(!c.isSelected)
                    {

                        c.selected();
                        cellCache.add(c);
                    }
                    else
                    {

                    }
                    break;
                }
            }
            boom.play();
            clickCount++;
        }

        /* Update */
        updateUI();
        timePassed += delta;

        player.update(delta);
        /* Draw */
        drawUI();

        marker.draw();

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

        clickDisplay = new Text(40,Game.ui.getHeight() - 50, 30, 30, "Clicks: " + String.valueOf(clickCount));
        matchesDisplay = new Text(40, Game.ui.getHeight() - 70, 30, 30, "Matches: " + String.valueOf(matches));

    }

    private void drawUI() {
        clickDisplay.draw();
        matchesDisplay.draw();
    }
}
