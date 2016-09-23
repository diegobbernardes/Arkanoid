package App;

import com.senac.SimpleJava.Console;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Paddle extends Sprite {
	
	public Paddle(){
		super(40,3, Color.BLUE);
	}	
	public boolean bateu(Ball ball){
		
		Point pos=ball.getPosition();
		int raio=ball.getRaio();
		
		Rect rect=getBounds();
		int top = rect.y;
		int bottom = rect.y + rect.height;		
		int left = rect.x;
		int right = rect.x + rect.width;
		if (pos.x-raio > right) {
			return false;
		}
		if(pos.x+raio < left) {
			return false;
		}
		if(pos.y+raio < top) {
			return false;
		}
		if(pos.y-raio > bottom) {
			return false;
		}
		
		return true;
	}
	public boolean PosxLimiteMaximo(int width) {
		Point pos = this.getPosition();
		if(pos.x > width)
			return true;
		return false;
	}
	public boolean PosxLimiteMinimo(){
		Point pos = this.getPosition();
		if(pos.x < 0)
			return true;
		return false;
	}
}
