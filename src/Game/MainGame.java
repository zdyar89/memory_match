package Game;
import Entities.*;
import Utilities.*;
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
        grid.add(new Vector2f(350, 350));
        grid.add(new Vector2f(450, 350));
        grid.add(new Vector2f(550, 350));
        grid.add( new Vector2f(650, 350));
        grid.add(new Vector2f(750, 350));
        grid.add( new Vector2f(850, 350));
        Collections.shuffle(grid);

        Cell test1 = new Cell(bowser, grid.get(0));
        inputA.add(test1);
        Cell test2 = new Cell(kirby, grid.get(1));
        inputA.add(test2);
        Cell test3 = new Cell(kirby, grid.get(2));
        inputA.add(test3);
        Cell test4 = new Cell(kirby, grid.get(3));
        inputA.add(test4);
        Cell test5 = new Cell(egg, grid.get(4));
        inputA.add(test5);
        Cell test6 = new Cell(samus, grid.get(5));
        inputA.add(test6);


    }
    public void reset() {
        clickCount = 0;
        backgroundMusic = new SoundClip("tridentkeep");
        backgroundMusic.loop();
        SoundManager.add(backgroundMusic);
        //setRandom(cells, );
    }

    public MainGame() {


        initUI(1280,720,"SceneHW");
        cellCache = new ArrayList<>();
        matches = 0;
        GL11.glClearColor(.9f, .9f, .9f, 0f);
        gotClick = false;
        player = new Player(new Vector2f(Game.ui.getWidth()/8f, Game.ui.getHeight()/1.5f));
        marker = new Reticle();
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
        /*Cell test1 = new Cell(bowser, new Vector2f(350, 350));
        cells.add(test1);
        Cell test2 = new Cell(kirby, new Vector2f(450, 350));
        cells.add(test2);
        Cell test3 = new Cell(kirby, new Vector2f(550, 350));
        cells.add(test3);
        Cell test4 = new Cell(kirby, new Vector2f(650, 350));
        cells.add(test4);
        Cell test5 = new Cell(egg, new Vector2f(750, 350));
        cells.add(test5);
        Cell test6 = new Cell(samus, new Vector2f(850, 350));
        cells.add(test6);*/
        //setRandomizer(cells, grid);


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
        for(Cell c: this.cells)
        {
            c.deselect();
        }

    }

    @Override
    public void onMouseEvent(int button, int action, int mods) {
        if (button==0 && action== GLFW.GLFW_PRESS)
        {
            Vector2f lastClick = new Vector2f(Game.ui.getMouseLocation().x, Game.ui.getMouseLocation().y);
            gotClick = true;
        }
        if(clickCount >= 5 || matches == cells.size() / 2)
        {
            SceneManager.victory();
            System.out.println("Here");
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
        if(timePassed > 6000)
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
                //cellCache = new ArrayList<>();
                System.out.println("Match Found");
                match = true;
            } else {

                if (miniTimer >= 3000) {
                    cellCache.get(0).deselect();
                    cellCache.get(1).deselect();
                    cellCache.remove(0);
                    cellCache.remove(0);
                    miniTimer = 0;
                }
            }
            if (match) {
                matches++;
                clickCount = 0;
            }

            match = false;
        }
    }



    public Scene drawFrame(int delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        Vector2f coordinates = new Vector2f(Game.ui.getMouseLocation().x, Game.ui.getMouseLocation().y);

        manageCache(delta);

        marker.setLocation(coordinates);
        timePassed += delta;
        if(timePassed < 7000)
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

        clickDisplay = new Text(40,Game.ui.getHeight() - 50, 30, 30, String.valueOf(clickCount));
        matchesDisplay = new Text(40, Game.ui.getHeight() - 70, 30, 30, String.valueOf(matches));

    }

    private void drawUI() {
        clickDisplay.draw();
        matchesDisplay.draw();
    }
}
