package App;

import java.io.IOException;

import com.senac.SimpleJava.Console;
import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Image;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.Sprite;
import com.senac.SimpleJava.Graphics.events.KeyboardAction;

public class Arkanoid extends GraphicApplication {

	private Bloco blocos[] = new Bloco[78];	
	private Paddle paddle;
	private Ball ball;
	private int deltaY = -1;
	private int deltaX = 1;
	private Image imagem;
	private Color[] cores = { new Color(146,145,153),new Color(97,255,0),new Color(192,164,157),new Color(89,135,158),new Color(224,227,0),new Color(200,0,5) };
	
	@Override
	protected void draw(Canvas canvas) {
		canvas.clear();
		canvas.drawImage(imagem,0,0);
		for (int i = 0; i < blocos.length; i++) {
			blocos[i].draw(canvas);
		}
		ball.draw(canvas);
		canvas.putText(0, 0, 10, "Lifes :");
		canvas.putText(30, 0, 10, ""+ball.getLifes());
		paddle.draw(canvas);
	}

	@Override
	protected void setup() {
		this.setFramesPerSecond(60);
		this.setResolution(Resolution.MSX);
		
		try {
			imagem = new Image("images/bg3.jpg");
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		
		ball = new Ball();
		ball.setPosition(130,180);
		
		paddle = new Paddle();
		paddle.setPosition(100,185);
		
		int k = 0;
		int posBlocox = 15;
		int posBlocoy = 0;
		for (int i = 0; i <= 5; i++){
			for (int j = 0; j < 13; j++) {
				blocos[k] = new Bloco(cores[i]);
				blocos[k].setPosition(posBlocoy,posBlocox);
				k++;
				posBlocoy = posBlocoy + 20;
			}
			posBlocoy = 0;
			posBlocox = posBlocox + 12;
		}		
		bindKeyPressed("LEFT", new KeyboardAction() {
			@Override			
			public void handleEvent() {
				if(paddle.PosxLimiteMinimo())
					paddle.setPosition(0,185);					
				else
					paddle.move(-5, 0);				
			}
		});
		bindKeyPressed("RIGHT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				if(paddle.PosxLimiteMaximo((getResolution().width-20)))
					paddle.setPosition((getResolution().width-20),185);					
				else
					paddle.move(5, 0);
			}
		});
	}

	@Override
	protected void loop() {
		//Testando os limites do eixo X e Y.
		Point pos = ball.getPosition();
		if (testeLimite(pos.y,0,getResolution().height)) {
			deltaY *= -1;
		}
		if (testeLimite(pos.x,0,getResolution().width)) {
			deltaX *= -1;
		}
		if(ball.isDead(getResolution().height))
			ball.DieInsect();
			
		int bateu = 0;
		for (int i = 0; i < blocos.length; i++) {
			if (blocos[i].bateu(ball)) {
				Console.println("Bateu:" + i);
				bateu++;
			}
		}
		if(bateu>0)
			deltaY *= -1;
		
		if (paddle.bateu(ball)) {
			Console.println("Bateu no paddle");
			deltaY *= -1;
		}
		
		ball.move(deltaX, deltaY);
		
		redraw();	
		
		
	}
	
	private boolean testeLimite(double pos, int min, int max) {
		if(pos > max || pos < min) {
			return true;
		} else {
			return false;
		}
	}
	
}
