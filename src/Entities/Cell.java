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

        this.hitbox.setSize(64, 64);

        this.isSelected = false;
    }

    public void update(int delta)
    {
        if(Game.ui.getMouseLocation().x == this.getHitbox().x && Game.ui.getMouseLocation().y == this.getHitbox().y && Game.ui.mouseButtonIsPressed(0) ){
            this.isSelected = true;
        }

    }

    public void draw(){
        //may need to change later


        //might need this later
        // GL11.glLoadIdentity();

            GL11.glColor3f(.25f, .75f, .5f);
            //shifts everything down
            //GL11.glTranslatef(64, 64, 0);

            GL11.glBegin(GL11.GL_QUADS);
            {
                GL11.glVertex2f(0, 0);
                GL11.glVertex2f(0, 64);
                GL11.glVertex2f(64, 64);
                GL11.glVertex2f(64, 0);
            }
            GL11.glEnd();

    }
}