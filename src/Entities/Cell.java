package Entities;

import edu.utc.game.Game;
import edu.utc.game.GameObject;
import edu.utc.game.Math.Vector2f;
import edu.utc.game.Texture;
import org.lwjgl.opengl.GL11;

import java.sql.SQLOutput;

public class Cell extends GameObject {

    private Texture img;
    private Vector2f pos;
    private int x;
    private int y;
    public boolean isSelected;


    public Cell(Texture img, Vector2f pos){
        this.img = img;
        this.pos = pos;
        this.hitbox.setBounds((int) pos.x, (int) pos.y, 75, 75);

        this.isSelected = false;
    }

    public Texture getTexture()
    {
        return this.img;
    }

    public Vector2f getVector()
    {
        return this.pos;
    }
    
    public void setLocation(int x, int y)
    {
        //not sure if needed
       // this.hitbox.setBounds(x,y,64,64);   
    }

    public void update(int delta)
    {
       
      //handle this in main game, compare Texture images to find matches

    }

    public void selected()
    {
        isSelected = true;
    }

    public void reset(){
        super.setColor(.33f, .22f, .77f);
        super.draw();
    }

    public void draw(){
        //may need to change later


        //might need this later
        // GL11.glLoadIdentity();


       if(isSelected)
        {
            img.draw(this);
           // System.out.println("here");
        }
        else
        {
            super.setColor(.33f, .22f, .77f);
            super.draw();
           // System.out.println("here");

        }

    }
}
