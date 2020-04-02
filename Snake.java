import java.util.ArrayList;

public class Snake{
	private Position pos;
	private int radius;
	private int size;
	public ArrayList<Position> tail = new ArrayList<Position>();

	public Position prev;

	public Snake(int _x, int _y,int _r){
		pos = new Position(_x,_y);
		setRadius(_r);
		size = 0;

		prev = new Position();
	}

	public void update_tail(){
    	Position prev2 = new Position();
    	prev2.copy(prev);
    	for(int i = 0; i< tail.size(); i++){
    		prev2.copy(tail.get(i));
    		tail.get(i).copy(prev);
    		prev.copy(prev2);
    	}
    	//tail.add(prev);

    	//if(tail.size() > size){
    	//	tail.remove(0);
    	//}
	}

	public int xPos(){
		return this.pos.x;
	}
	public int yPos(){
		return this.pos.y;
	}
	public int getRadius(){
		return this.radius;
	}

	public int getSize(){
		return this.size;
	}

	public Position Pos(){
		return pos;
	}

	public void grow(){
		size++;
	}

	public void setX(int _x){
		this.pos.x = _x;
	}
	public void setY(int _y){
		this.pos.y = _y;
	}
	public void setRadius(int _r){
		this.radius = _r;
	}

	public void left(){
		prev.copy(pos);
		this.pos.x = (this.pos.x-this.radius);
	}
	public void right(){
		prev.copy(pos);
		this.pos.x = (this.pos.x+this.radius);
	}
	public void up(){
		prev.copy(pos);
		this.pos.y = (this.pos.y-this.radius);
	}
	public void down(){
		prev.copy(pos);
		this.pos.y = (this.pos.y+this.radius);
	}

	public boolean eat(Food food){
		
		if(pos.distance(food.Pos()) <= radius)
			return true;
		else return false;
	}
}