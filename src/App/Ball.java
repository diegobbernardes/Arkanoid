package App;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Sprite;

public class Ball extends Sprite {

	public Ball() {
		super(5,5,Color.BLACK);
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
	
}
