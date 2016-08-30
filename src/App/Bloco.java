package App;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Bloco extends Sprite {

	private boolean alive = true;

	public Bloco(Color cor) {
		super(18, 10, cor);
	}

	public boolean bateu(Ball ball){
		// Se o bloco nas esta vivo, nao pode bater...
		if (!alive)
			return false;
		
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
		
		alive = false;
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		if (alive)
			super.draw(canvas);
	}
}
