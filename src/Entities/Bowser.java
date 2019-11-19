package Entities;

import edu.utc.game.Game;
import edu.utc.game.GameObject;
import edu.utc.game.Math.Vector2f;
import edu.utc.game.Texture;
import org.lwjgl.glfw.GLFW;

public class Bowser extends GameObject{

    private Texture img;
    private int xPos;
    private int yPos;

    public Bowser(){
        this.img = new Texture("res/Textures/Bowser.png");
    }
}
