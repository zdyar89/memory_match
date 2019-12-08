package Game;

import edu.utc.game.Game;
import edu.utc.game.GameObject;
import edu.utc.game.Text;
import edu.utc.game.Texture;

public class GameBG extends GameObject {

    private Texture img;

    public GameBG(){
        this.img = new Texture("res/Textures/menu.png");
        this.hitbox.setSize(1100, 720);
    }

    public void draw(){

        img.draw(this);

    }

}
