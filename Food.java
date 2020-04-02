import java.util.Random;

public class Food{
	
	private Position pos;
	private int radius;

	private Random rand;
	private SnakeGame boss;

	public Food(){
		pos = new Position();
		rand = new Random();
		boss = new SnakeGame();
		spawn();
	}

	public void spawn(){
		this.pos.x = rand.nextInt(boss.WIDTH-20);
		this.pos.y = rand.nextInt(boss.HEIGHT-120);
		this.radius = 20;
	}

	public int getX(){
		return this.pos.x;
	}
	public int getY(){
		return this.pos.y;
	}
	public Position Pos(){
		return pos;
	}
	public int getRadius(){
		return this.radius;
	}
}