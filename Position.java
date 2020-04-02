class Position{

	public int x;
	public int y;

	public Position(){

	}

	public Position(int _x, int _y){
		this.x = _x;
		this.y = _y;
	}

	public double distance(Position pos){
		int x1,x2,y1,y2;
		x1 = this.x;
		x2 = pos.x;
		y1 = this.y;
		y2 = pos.y;

		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
	public void copy(Position p){
		this.x = p.x;
		this.y = p.y;
	}
}