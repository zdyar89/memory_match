package Entities;

import edu.utc.game.Game;
import edu.utc.game.GameObject;
import edu.utc.game.Texture;
import org.lwjgl.opengl.GL11;

public class Cell extends GameObject {

    private Texture img;
    private Vector2f pos;
    private int x;
    private int y;
    public boolean isSelected;


    public Cell(Texture img, Vector2f pos){
        this.img = img;
        this.pos = pos;
        this.hitbox.setBounds((int) pos.x, (int) pos.y, 30, 30);

        this.isSelected = false;
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

    /*public void draw(){
        //may need to change later


        //might need this later
        // GL11.glLoadIdentity();
        
        if(!isSelected)
        {
            super.setColor(.33f, .22f, .77f);
            super.draw();
        }
        else
        {
        img.draw(this);
        }

    }*/
}
