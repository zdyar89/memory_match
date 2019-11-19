package VFX;

import edu.utc.game.*;

public class Wallpaper extends GameObject  {

	public Texture img;

	public Wallpaper(int x, int y, int width, int height) {

		//this.img = new Texture

		this.hitbox.setBounds(x, y, width, height);
	}

	public void setWidth(int x) {
		this.hitbox.x = x;
	}
}
