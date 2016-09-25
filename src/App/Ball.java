package App;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Sprite;

public class Ball extends Sprite {
	private int life;
	public Ball() {
		super(5,5,Color.BLACK);
		life = 5;
	}
	
	public void resetLife(){
		life = 5;
	}
	
	public int getLifes(){
		return life;
	}
	
	public void LoseLife(){
		life--;
	}
	
	public int getRaio() {
		return getWidth()/2;
	}

	@Override
	public Point getPosition() {
		Point pos = super.getPosition();
		int r = getRaio();
		return new Point(pos.x + r, pos.y + r);
	}
	
	public boolean isDead(int height) {
		Point pos = getPosition();
		if(pos.y > height-2)
			return true;
		return false;
	}
	
}
