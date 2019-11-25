package Entities;

import edu.utc.game.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import sun.font.GlyphLayout;


public class Grid extends GameObject {

    //private GameBoard board;
    private Cell[] grid;
    private int width;
    private int height;
    private Egg egg;
    private Kirby kirby;
    private Bowser bowser;
    private Samus samus;
    public Grid()
    {

       this.hitbox.setSize(64, 64);
    }



    @Override
    public void draw()
    {

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
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

class Cell extends GameObject {

    private Texture img;
    private int size = 50;

    public Cell(float r, float g, float b){
        this.hitbox.setSize(size, size);
        this.setColor(r, g, b);
    }
}
