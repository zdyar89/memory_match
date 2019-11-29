package Entities;

import edu.utc.game.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;




public class Grid extends GameObject {


    private Cell[] grid;

    private Egg egg;
    private Kirby kirby;
    private Bowser bowser;
    private Samus samus;
    private boolean isSelected;
    public Grid()
    {
        this.isSelected = false;
       this.hitbox.setSize(64, 64);
    }

    public void update(int delta)
    {
       int x = Game.ui.getMouseLocation().x;
       int y = Game.ui.getMouseLocation().y;
        if(this.getHitbox().getLocation().x == x && this.getHitbox().getLocation().y == y && Game.ui.mouseButtonIsPressed(0) ){
            this.isSelected = true;
        }

    }
    public void draw(){
        //may need to change later


        //might need this later
        // GL11.glLoadIdentity();
        if(!isSelected) {
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
        else
        {
            this.getHitbox().setLocation(30, 30);
            kirby.getHitbox().setLocation(30,  30);
            kirby.draw();
        }
    }

}


