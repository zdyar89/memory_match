package Game;

import Entities.Player;
import Entities.*;
import Utilities.SoundClip;
import Utilities.SoundManager;
import edu.utc.game.*;
import edu.utc.game.Math.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

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
    public long clickCount;
    public SoundClip backgroundMusic;
    public List<Cell> cells;
    public int matches;

    public void reset() {
        clickCount = 0;
        backgroundMusic = new SoundClip("tridentkeep");
        backgroundMusic.loop();
        SoundManager.add(backgroundMusic);
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
        Cell test1 = new Cell(bowser, new Vector2f(350f, 350f));
        cells.add(test1);

        Cell test2 = new Cell(kirby, new Vector2f(450, 350));
        cells.add(test2);
        Cell test3 = new Cell(kirby, new Vector2f(550, 350));
        cells.add(test3);
    }

    public void createGrid(){
     
        /*for(int i = 0; i <=7 ; i++)
        {
            for(int j = 0; j <=7; j++)
            {
                
            }
            */
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
        if(clickCount >= 5 || matches == cells.size() / 2)
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



        marker.setLocation(coordinates);

        while(timePassed <= 10)
        {
            for(Cell c: cells)
            {
                c.isSelected = true;
            }
        }

        if(timePassed >= 10)
        {
            for(Cell c: cells)
            {
                c.isSelected = false;
            }
        }
        //not sure we need the draw loop
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
        if(cellCache.size() == 2)
        {
            boolean match = false;

            if (cellCache.get(0).getTexture() == cellCache.get(1).getTexture()) {
                //match found
                cellCache.get(0).deactivate();
                cellCache.get(0).deactivate();
                cellCache.remove(0);
                cellCache.remove(0);
                //cellCache = new ArrayList<>();
                System.out.println("Match Found");
                match = true;
            }
            else
            {
                cellCache.get(0).reset();
                cellCache.get(1).reset();
                cellCache.remove(0);
                cellCache.remove(0);
            }
            if(match) {
                matches++;
                clickCount = 0;
            };
            match = false;
        }


        /* Update */
        updateUI();
        //marker.setLocation(coordinates);
        player.update(delta);
        
        timePassed += delta;

        /* Draw */
        drawUI();
        player.draw();
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
