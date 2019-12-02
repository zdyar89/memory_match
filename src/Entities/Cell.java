package Entities;

import edu.utc.game.Game;
import edu.utc.game.GameObject;
import edu.utc.game.Texture;
import org.lwjgl.opengl.GL11;

public class Cell extends GameObject {

    private Texture img;
    private int x;
    private int y;
    public boolean isSelected;


    public Cell(Texture img){
        this.img = img;

        //this.hitbox.setSize(64, 64);

        this.isSelected = false;
    }
    
    public void setLocation(int x, int y)
    {
        this.hitbox.setBounds(x,y,64,64);   
    }

    public void update(int delta)
    {
        //f(Game.ui.getMouseLocation().x == this.getHitbox().x && Game.ui.getMouseLocation().y == this.getHitbox().y && Game.ui.mouseButtonIsPressed(0) ){
           // this.isSelected = true;
        //}

    }

    /*public void draw(){
        //may need to change later


        //might need this later
        // GL11.glLoadIdentity();

        img.draw(this);

    }*/
}
