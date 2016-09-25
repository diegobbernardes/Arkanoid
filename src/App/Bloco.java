package App;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Bloco extends Sprite {

	private boolean alive = true;
	private int life = 1;
	
	public Bloco(Color cor) {	
		super(18, 10, cor);
	}
	public Bloco(Color cor,int life) {		
		super(18, 10, cor);
		this.life = life;
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
		
		life--;
		if(life<=0)
			alive = false;
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		if (alive)
			super.draw(canvas);
	}
	public boolean isAlive() {		
		return alive;
	}
	public void Die() {
		alive = false;
		life=0;
		
	}
}
