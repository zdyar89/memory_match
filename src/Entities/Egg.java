package Entities;

import edu.utc.game.*;

public class Egg extends GameObject {

    private Texture img;
    private int xPos;
    private int yPos;

    public Egg(){
        this.img = new Texture("res/Textures/Egg.png");
    }
}
