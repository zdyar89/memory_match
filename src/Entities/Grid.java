package Entities;

import edu.utc.game.*;


public class Grid extends GameObject {

    private GameBoard board;

    public Grid()
    {
       this.board = board;
       this.hitbox.setSize(100, 100);
       this.hitbox.setLocation(Game.ui.getWidth() / 2, Game.ui.getHeight() /2);
       this.setColor(1, 0, 0);
    }

    public void update(int delta){

        float speed = 0.01f;
        //need reticle click event
        if(Game.ui.mouseButtonIsPressed(0))
        {
            //something something
        }

    }




}
